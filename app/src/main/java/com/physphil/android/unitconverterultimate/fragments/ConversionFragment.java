package com.physphil.android.unitconverterultimate.fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
    public static ConversionFragment newInstance(int id)
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
        mConversionId = getArguments().getInt(ARGS_CONVERSION_ID, Conversions.AREA);
        mConversionPresenter = new ConversionPresenter(this);
        mPrefs = Preferences.getInstance(getActivity());
        mState = DataAccess.getInstance(getActivity()).getConversionState(mConversionId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_conversion, container, false);

        mTxtValue = (EditText) v.findViewById(R.id.header_value_from);
        if(savedInstanceState == null)  // TODO - handle rotation properly
        {
            mTxtValue.setText(mPrefs.getLastValue());
            mTxtValue.setSelection(mTxtValue.getText().length());
        }

        // Only allow negative values for temperature
        if (mConversionId == Conversions.TEMPERATURE)
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
                Snackbar.make(v, R.string.toast_copied_clipboard, Snackbar.LENGTH_SHORT).show();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        convert();
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

        // View hierarchy has been restored, set state of radio buttons
        mGrpFrom.check(mState.getFromId());
        mGrpFrom.setOnCheckedChangeListener(this);
        mGrpTo.check(mState.getToId());
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
            case Conversions.TEMPERATURE:
                mConversionPresenter.convertTemperatureValue(value, getCheckedUnit(mGrpFrom), getCheckedUnit(mGrpTo));
                break;

            case Conversions.FUEL:
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
            mGrpFrom.addView(getRadioButton(u), lp);
            mGrpTo.addView(getRadioButton(u), lp);
        }

        // Restore previously selected units
        mGrpFrom.check(mState.getFromId());
        mGrpTo.check(mState.getToId());
    }

    /**
     * Create Radio Button to display in group
     *
     * @param u unit which the button represents
     * @return RadioButton to display
     */
    private RadioButton getRadioButton(Unit u)
    {
        RadioButton btn = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.unit_radio_button, null);
        btn.setId(u.getId());
        btn.setTag(u);
        btn.setText(u.getLabelResource());
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

        // Swap selected units in groups
        mGrpFrom.check(toId);
        mGrpTo.check(fromId);
    }

    private TextWatcher mTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        switch(group.getId())
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
