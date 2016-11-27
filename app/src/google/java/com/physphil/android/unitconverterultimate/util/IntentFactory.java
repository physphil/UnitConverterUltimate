package com.physphil.android.unitconverterultimate.util;

import android.content.Context;
import android.content.Intent;

import com.physphil.android.unitconverterultimate.DonateActivity;

/**
 * 'Google' build flavour specific implementation of IntentFactory
 * Created by Phizz on 2016-11-13.
 */

public class IntentFactory extends BaseIntentFactory
{
    /**
     * Get an Intent to start DonateActivity.  This is only included in the 'google' build flavour.
     * @param context context
     * @return an Intent to start DonateActivity
     */
    public static Intent getDonateIntent(final Context context)
    {
        return new Intent(context, DonateActivity.class);
    }
}
