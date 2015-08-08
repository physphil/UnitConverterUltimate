package com.physphil.android.unitconverterultimate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.physphil.android.unitconverterultimate.fragments.ConversionFragment;

/**
 * Main activity
 * Created by Phizz on 15-07-28.
 */
public class MainActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false); // TODO move to Application class
        Preferences.getInstance(this).getPreferences().registerOnSharedPreferenceChangeListener(this);

        setContentView(R.layout.activity_main);
        setToolbarHomeNavigation(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ConversionFragment.newInstance(Preferences.getInstance(this).getLastConversion()))
                    .commit();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Preferences.getInstance(this).getPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if(key.equals(Preferences.PREFS_THEME))
        {
            recreate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
