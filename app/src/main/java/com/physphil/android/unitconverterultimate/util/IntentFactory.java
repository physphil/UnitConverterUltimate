package com.physphil.android.unitconverterultimate.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.physphil.android.unitconverterultimate.R;

/**
 * Class to handle creation of commonly used intents
 * Created by Phizz on 15-08-09.
 */
public class IntentFactory
{
    private static final String EMAIL_ADDRESS = "physphil@gmail.com";
    private static final String GITHUB_REPO = "https://github.com/physphil/UnitConverterUltimate-Studio";

    /**
     * Get VIEW intent to open browser to view source on GitHub
     * @return intent to view source
     */
    public static Intent getViewSourceIntent()
    {
        Uri uri = Uri.parse(GITHUB_REPO);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /**
     * Get SEND intent to open email to request unit
     * @param subject subject line of email
     * @return intent to send email
     */
    public static Intent getRequestUnitIntent(String subject)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_ADDRESS});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        return i;
    }

    /**
     * Get VIEW intent to open Play Store to supplied package name
     * @param packageName package name to open
     * @return intent to open play store
     */
    public static Intent getOpenPlayStoreIntent(String packageName)
    {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
