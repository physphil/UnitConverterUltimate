/*
 * Copyright 2015 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.PreferencesActivity;
import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.data.DataAccess;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.ConversionState;
import com.physphil.android.unitconverterultimate.models.Unit;
import com.physphil.android.unitconverterultimate.presenters.ConversionPresenter;
import com.physphil.android.unitconverterultimate.util.Conversions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Base fragment to display units to convert
 * Created by Phizz on 15-07-28.
 */
public final class ConversionFragment extends Fragment implements ConversionPresenter.ConversionView,
        SharedPreferences.OnSharedPreferenceChangeListener,
        RadioGroup.OnCheckedChangeListener
{
    private static final String ARGS_CONVERSION_ID = "conversion_id";

    private ConversionPresenter mConversionPresenter;
    private RadioGroup mGrpFrom, mGrpTo;
    private EditText mTxtValue, mTxtResult;
    private int mConversionId;
    private double mResult;
    private Preferences mPrefs;
    private ConversionState mState;

    /**
     * Create a new ConversionFragment to display
     *
     * @param id id of the conversion it will handle
     * @return new ConversionFragment instance
     */
    public static ConversionFragment newInstance(@Conversion.id int id)
    {
        ConversionFragment f = new ConversionFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_CONVERSION_ID, id);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        Preferences.getInstance(activity).getPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Preferences.getInstance(getActivity()).getPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mConversionId = getArguments().getInt(ARGS_CONVERSION_ID, Conversion.AREA);
        mConversionPresenter = new ConversionPresenter(this);
        mPrefs = Preferences.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_conversion, container, false);

        mTxtValue = (EditText) v.findViewById(R.id.header_value_from);
        if (savedInstanceState == null)
        {
            String value = mPrefs.getLastValue();
            if (mConversionId != Conversion.TEMPERATURE)
            {
                // adjust value if it was negative coming from temperature conversion
                value = value.replace("-", "");
                value = value.replace("+", "");
            }
            mTxtValue.setText(value);
            mTxtValue.setSelection(mTxtValue.getText().length());
        }

        // Only allow negative values for temperature
        if (mConversionId == Conversion.TEMPERATURE)
        {
            mTxtValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        }
        else
        {
            mTxtValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        mTxtResult = (EditText) v.findViewById(R.id.header_value_to);
        mTxtResult.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                // Copy text to clipboard
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Conversion Result", ((EditText) v).getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity(), R.string.toast_copied_clipboard, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mGrpFrom = (RadioGroup) v.findViewById(R.id.radio_group_from);
        mGrpTo = (RadioGroup) v.findViewById(R.id.radio_group_to);
        addUnits();

        ObservableScrollView scrollView = (ObservableScrollView) v.findViewById(R.id.list_conversion);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.attachToScrollView(scrollView);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                swapUnits();
            }
        });

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mTxtValue.addTextChangedListener(mTextWatcher);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mTxtValue.removeTextChangedListener(mTextWatcher);
        mPrefs.setLastValue(mTxtValue.getText().toString());
        mPrefs.setLastConversion(mConversionId);
        DataAccess.getInstance(getActivity()).saveConversionState(mState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState)
    {
        super.onViewStateRestored(savedInstanceState);
        new LoadConversionStateTask().execute();
    }

    /**
     * Set radio buttons to their saved state (if any)
     */
    private void restoreConversionState()
    {
        if (mState.getFromId() < 0 || mState.getToId() < 0)
        {
            // Empty initial state, set state from default checked buttons
            mState.setFromId(mGrpFrom.getCheckedRadioButtonId());
            mState.setToId(mGrpTo.getCheckedRadioButtonId());
        }
        else
        {
            // Overwrite default checked state with last saved state
            mGrpFrom.check(mState.getFromId());
            mGrpTo.check(mState.getToId());
        }

        mGrpFrom.setOnCheckedChangeListener(this);
        mGrpTo.setOnCheckedChangeListener(this);
    }

    /**
     * Convert value from one unit to another
     */
    private void convert()
    {
        // If input isn't a number yet then set to 0
        String input = mTxtValue.getText().toString();
        double value = isNumeric(input) ? Double.parseDouble(input) : 0;

        switch (mConversionId)
        {
            case Conversion.TEMPERATURE:
                mConversionPresenter.convertTemperatureValue(value, getCheckedUnit(mGrpFrom), getCheckedUnit(mGrpTo));
                break;

            case Conversion.FUEL:
                mConversionPresenter.convertFuelValue(value, getCheckedUnit(mGrpFrom), getCheckedUnit(mGrpTo));
                break;

            default:
                mConversionPresenter.convert(value, getCheckedUnit(mGrpFrom), getCheckedUnit(mGrpTo));
                break;
        }
    }

    /**
     * Get the Unit associated with the checked button in a radio group
     *
     * @param group RadioGroup which contains the button
     * @return Unit associated with checked button
     */
    private Unit getCheckedUnit(RadioGroup group)
    {
        int index = group.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) group.findViewById(index);
        return (Unit) btn.getTag();
    }

    /**
     * Add units to From and To radio groups
     */
    private void addUnits()
    {
        Conversion c = Conversions.getInstance().getById(mConversionId);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        lp.bottomMargin = getResources().getDimensionPixelSize(R.dimen.margin_view_small);
        lp.topMargin = getResources().getDimensionPixelSize(R.dimen.margin_view_small);

        for (int i = 0; i < c.getUnits().size(); i++)
        {
            Unit u = c.getUnits().get(i);
            boolean fromChecked = false;
            boolean toChecked = false;

            // Set default checked state. Will be overridden later on if there is a saved state to restore
            if (i == 0)
            {
                fromChecked = true;
            }
            else if (i == 1)
            {
                toChecked = true;
            }

            mGrpFrom.addView(getRadioButton(u, fromChecked), lp);
            mGrpTo.addView(getRadioButton(u, toChecked), lp);
        }
    }

    /**
     * Create Radio Button to display in group
     *
     * @param u       unit which the button represents
     * @param checked if the button is checked or not
     * @return RadioButton to display
     */
    private RadioButton getRadioButton(Unit u, boolean checked)
    {
        RadioButton btn = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.unit_radio_button, null);
        btn.setId(u.getId());
        btn.setTag(u);
        btn.setText(u.getLabelResource());
        btn.setChecked(checked);
        return btn;
    }

    /**
     * Checks to see if a string contains a numeric value
     *
     * @param number string input
     * @return if the input is a numeric value
     */
    private boolean isNumeric(String number)
    {
        try
        {
            double d = Double.parseDouble(number);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * Get DecimalFormat used to format result
     *
     * @return DecimalFormat
     */
    private DecimalFormat getDecimalFormat()
    {
        DecimalFormat formatter = new DecimalFormat();

        //Set maximum number of decimal places
        formatter.setMaximumFractionDigits(mPrefs.getNumberDecimals());

        //Set group and decimal separators
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setDecimalSeparator(mPrefs.getDecimalSeparator().charAt(0));

        String groupSeparator = mPrefs.getGroupSeparator();
        boolean isSeparatorUsed = !groupSeparator.equals(getString(R.string.group_separator_none));
        formatter.setGroupingUsed(isSeparatorUsed);
        if (isSeparatorUsed)
        {
            symbols.setGroupingSeparator(groupSeparator.charAt(0));
        }

        formatter.setDecimalFormatSymbols(symbols);
        return formatter;
    }

    /**
     * Swap units between From and To
     */
    private void swapUnits()
    {
        int fromId = mGrpFrom.getCheckedRadioButtonId();
        int toId = mGrpTo.getCheckedRadioButtonId();

        // Check individual button to avoid calling OnCheckChanged listener 6 times
        // RadioGroup.check(id) actually fires OnCheckChanged 3 times per each button
        ((RadioButton) mGrpFrom.findViewById(toId)).setChecked(true);
        ((RadioButton) mGrpTo.findViewById(fromId)).setChecked(true);
    }

    private TextWatcher mTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            convert();
        }
    };

    @Override
    public void showResult(double result)
    {
        mResult = result;
        mTxtResult.setText(getDecimalFormat().format(result));
    }

    // Radio Group checked change listener
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch (group.getId())
        {
            case R.id.radio_group_from:
                mState.setFromId(checkedId);
                break;

            case R.id.radio_group_to:
                mState.setToId(checkedId);
                break;
        }

        convert();
    }

    // Change in shared preferences
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if (key.equals(Preferences.PREFS_NUMBER_OF_DECIMALS) ||
                key.equals(Preferences.PREFS_DECIMAL_SEPARATOR) ||
                key.equals(Preferences.PREFS_GROUP_SEPARATOR))
        {
            mTxtResult.setText(getDecimalFormat().format(mResult));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_conversion_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_clear:
                mTxtValue.setText("");
                return true;

            case R.id.menu_help:
                HelpDialogFragment.newInstance().show(getChildFragmentManager(), HelpDialogFragment.TAG);
                return true;

            case R.id.menu_settings:
                PreferencesActivity.start(getActivity());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * AsyncTask to load ConversionState for given fragment from database
     */
    private final class LoadConversionStateTask extends AsyncTask<Void, Void, ConversionState>
    {
        @Override
        protected ConversionState doInBackground(Void... params)
        {
            // This is okay as DataAccess uses application context
            return DataAccess.getInstance(getActivity()).getConversionState(mConversionId);
        }

        @Override
        protected void onPostExecute(ConversionState conversionState)
        {
            // This is okay as fragment instance is retained across config change
            mState = conversionState;
            restoreConversionState();
            convert();
        }
    }
}