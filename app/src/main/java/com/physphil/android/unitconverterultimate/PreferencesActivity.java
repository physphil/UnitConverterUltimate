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

package com.physphil.android.unitconverterultimate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.physphil.android.unitconverterultimate.fragments.PreferencesFragment;

/**
 * Activity to host preferences fragment
 * Created by Phizz on 15-08-03.
 */
public class PreferencesActivity extends BaseActivity {

    /**
     * Start PreferencesActivity to host an instance of PreferencesFragment
     *
     * @param context context
     */
    public static void start(Context context) {
        Intent i = new Intent(context, PreferencesActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host);
        setupToolbar();
        setToolbarHomeNavigation(true);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, PreferencesFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
