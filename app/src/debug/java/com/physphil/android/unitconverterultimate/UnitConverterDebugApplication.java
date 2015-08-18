package com.physphil.android.unitconverterultimate;

import com.facebook.stetho.Stetho;

/**
 * Debug application class, used to initialize Stetho
 * Created by pshadlyn on 8/18/2015.
 */
public class UnitConverterDebugApplication extends UnitConverterApplication
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
