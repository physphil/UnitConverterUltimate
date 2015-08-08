package com.physphil.android.unitconverterultimate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.fragments.ConversionFragment;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.util.Conversions;

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
        setupToolbar();
        setToolbarHomeNavigation(true);
        if(getSupportActionBar() != null) getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        int conversion = Preferences.getInstance(this).getLastConversion();
        setToolbarTitle(Conversions.getInstance().getConversionTitle(conversion));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setupDrawer(conversion);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ConversionFragment.newInstance(conversion))
                    .commit();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Preferences.getInstance(this).getPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Setup navigation drawer
     * @param state index of item to be selected initially
     */
    private void setupDrawer(int state)
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        navigationView.getMenu().getItem(state).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId())
                {
                    case R.id.drawer_settings:
                        PreferencesActivity.start(MainActivity.this);
                        return true;

                    default:
                        menuItem.setChecked(true);
                        setToolbarTitle(Conversions.getInstance().getConversionTitle(menuItem.getOrder()));
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, ConversionFragment.newInstance(menuItem.getOrder()))
                                .commit();
                        return true;
                }
            }
        });
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
