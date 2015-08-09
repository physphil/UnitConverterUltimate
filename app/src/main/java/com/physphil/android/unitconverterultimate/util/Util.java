package com.physphil.android.unitconverterultimate.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.R;


public class Util
{
    public static void rateApp(Context context)
    {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent i = new Intent(Intent.ACTION_VIEW, uri);

        try
        {
            context.startActivity(i);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(context, R.string.toast_error_google_play, Toast.LENGTH_SHORT).show();
        }
    }
}
