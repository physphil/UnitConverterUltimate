package com.physphil.android.unitconverterultimate;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class DonateActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DonateFragment())
                    .commit();
        }
    }

}
