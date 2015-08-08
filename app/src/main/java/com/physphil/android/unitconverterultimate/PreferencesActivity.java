package com.physphil.android.unitconverterultimate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.physphil.android.unitconverterultimate.fragments.PreferencesFragment;

/**
 * Activity to host preferences fragment
 * Created by Phizz on 15-08-03.
 */
public class PreferencesActivity extends BaseActivity
{
    /**
     * Start PreferencesActivity to host an instance of PreferencesFragment
     * @param context context
     */
    public static void start(Context context)
    {
        Intent i = new Intent(context, PreferencesActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host);
        setupToolbar();
        setToolbarHomeNavigation(true);

        if(savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, PreferencesFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
