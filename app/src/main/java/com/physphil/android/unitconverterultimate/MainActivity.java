/*
 * Copyright 2015 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.physphil.android.unitconverterultimate.api.FixerApi;
import com.physphil.android.unitconverterultimate.api.FixerService;
import com.physphil.android.unitconverterultimate.api.models.CurrencyResponse;
import com.physphil.android.unitconverterultimate.fragments.ConversionFragment;
import com.physphil.android.unitconverterultimate.fragments.HelpDialogFragment;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.util.Conversions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Main activity
 * Created by Phizz on 15-07-28.
 */
public class MainActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private DrawerLayout mDrawerLayout;
    private Conversions mConversions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        Preferences.getInstance(this).getPreferences().registerOnSharedPreferenceChangeListener(this);
        mConversions = Conversions.getInstance();

        setContentView(R.layout.activity_main);
        setupToolbar();
        setToolbarHomeNavigation(true);
        if(getSupportActionBar() != null) getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        int conversion = Preferences.getInstance(this).getLastConversion();
        setToolbarTitle(mConversions.getById(conversion).getLabelResource());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset){}

            @Override
            public void onDrawerOpened(View drawerView)
            {
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView){}

            @Override
            public void onDrawerStateChanged(int newState){}
        });
        setupDrawer(conversion);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ConversionFragment.newInstance(conversion))
                    .commit();
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        // Show help dialog if never seen before
        if(Preferences.getInstance(this).showHelp())
        {
            HelpDialogFragment.newInstance().show(getSupportFragmentManager(), HelpDialogFragment.TAG);
        }

        // // FIXME: 16-07-26 retrofit2 tests
        FixerApi.getInstance().getService()
                .getLatestRates("CAD")
                .enqueue(new Callback<CurrencyResponse>()
        {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response)
            {
                Preferences.getInstance(MainActivity.this).saveLatestCurrency(response.body());
                Conversions.getInstance().getCurrencyConversions(MainActivity.this);
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t)
            {
                Log.d("PS", "in failed response");
            }
        });
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
                        int conversion = getConversionFromDrawer(menuItem.getItemId());
                        setToolbarTitle(mConversions.getById(conversion).getLabelResource());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, ConversionFragment.newInstance(conversion))
                                .commit();
                        return true;
                }
            }
        });
    }

    @Conversion.id
    private int getConversionFromDrawer(int itemId)
    {
        switch(itemId)
        {
            case R.id.drawer_area:
                return Conversion.AREA;

            case R.id.drawer_cooking:
                return Conversion.COOKING;

            case R.id.drawer_currency:
                return Conversion.CURRENCY;

            case R.id.drawer_storage:
                return Conversion.STORAGE;

            case R.id.drawer_energy:
                return Conversion.ENERGY;

            case R.id.drawer_fuel:
                return Conversion.FUEL;

            case R.id.drawer_length:
                return Conversion.LENGTH;

            case R.id.drawer_mass:
                return Conversion.MASS;

            case R.id.drawer_power:
                return Conversion.POWER;

            case R.id.drawer_pressure:
                return Conversion.PRESSURE;

            case R.id.drawer_speed:
                return Conversion.SPEED;

            case R.id.drawer_temperature:
                return Conversion.TEMPERATURE;

            case R.id.drawer_time:
                return Conversion.TIME;

            case R.id.drawer_torque:
                return Conversion.TORQUE;

            case R.id.drawer_volume:
                return Conversion.VOLUME;
        }

        return Conversion.AREA;
    }

    private void hideKeyboard()
    {
        View v = getCurrentFocus();
        if (v != null)
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
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