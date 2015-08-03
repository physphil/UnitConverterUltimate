package com.physphil.android.unitconverterultimate;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Base Activity class for application
 * Created by Phizz on 15-08-03.
 */
public class BaseActivity extends AppCompatActivity
{
    /**
     * Set backward navigation in toolbar
     * @param upAsHome if back button should close activity
     */
    public void setToolbarHomeNavigation(boolean upAsHome)
    {
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
