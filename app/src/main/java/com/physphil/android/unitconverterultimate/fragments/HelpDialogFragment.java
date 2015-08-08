package com.physphil.android.unitconverterultimate.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

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
                .setPositiveButton(R.string.dialog_btn_got_it, null)
                .create();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView) view.findViewById(android.R.id.message);
        tv.setAutoLinkMask(Linkify.ALL);
    }
}
