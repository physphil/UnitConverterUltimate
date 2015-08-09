package com.physphil.android.unitconverterultimate.fragments;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.R;

/**
 * Dialog fragment to display help text to user
 * Created by Phizz on 15-08-08.
 */
public class HelpDialogFragment extends DialogFragment
{
    public static final String TAG = HelpDialogFragment.class.getName();

    public static HelpDialogFragment newInstance()
    {
        return new HelpDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title_help)
                .setMessage(R.string.dialog_message_help)
                .setPositiveButton(R.string.dialog_btn_got_it, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Preferences.getInstance(getActivity()).setShowHelp(false);
                    }
                })
                .setNeutralButton(R.string.dialog_btn_view_source, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            Uri uri = Uri.parse("https://github.com/physphil/UnitConverterUltimate-Studio");
                            Intent i = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(i);
                        }
                        catch (ActivityNotFoundException ex)
                        {
                            Toast.makeText(getActivity(), R.string.toast_error_no_browser, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create();
    }
}
