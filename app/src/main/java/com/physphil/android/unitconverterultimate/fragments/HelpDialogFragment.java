/*
 * Copyright 2016 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.fragments;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.util.IntentFactory;

/**
 * Dialog fragment to display help text to user
 * Created by Phizz on 15-08-08.
 */
public class HelpDialogFragment extends DialogFragment {

    public static final String TAG = HelpDialogFragment.class.getName();

    public static HelpDialogFragment newInstance() {
        return new HelpDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title_help)
                .setMessage(R.string.dialog_message_help)
                .setPositiveButton(R.string.dialog_btn_got_it, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Preferences.getInstance(getActivity()).setShowHelp(false);
                    }
                })
                .setNeutralButton(R.string.dialog_btn_view_source, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            startActivity(IntentFactory.getOpenUrlIntent(IntentFactory.GITHUB_REPO));
                        }
                        catch (ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(), R.string.toast_error_no_browser, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create();
    }
}
