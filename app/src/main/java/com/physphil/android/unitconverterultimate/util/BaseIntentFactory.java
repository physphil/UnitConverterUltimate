/*
 * Copyright 2017 Phil Shadlyn
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

package com.physphil.android.unitconverterultimate.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


/**
 * Class to handle creation of commonly used intents
 * Created by Phizz on 15-08-09.
 */
public abstract class BaseIntentFactory {

    public static final String GITHUB_REPO = "https://github.com/physphil/UnitConverterUltimate-Studio";
    private static final String EMAIL_ADDRESS = "physphil@gmail.com";

    /**
     * Get VIEW intent to open browser to view given url
     *
     * @return intent to view url
     */
    public static Intent getOpenUrlIntent(String url) {
        Uri uri = Uri.parse(url);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /**
     * Get SEND intent to open email to request unit
     *
     * @param subject subject line of email
     * @return intent to send email
     */
    public static Intent getRequestUnitIntent(String subject) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_ADDRESS});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        return i;
    }

    /**
     * Get VIEW intent to open Play Store to supplied package name
     *
     * @param packageName package name to open
     * @return intent to open play store
     */
    public static Intent getOpenPlayStoreIntent(String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static Intent getRestartAppIntent(final Context context) {
        final Intent i = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }
}
