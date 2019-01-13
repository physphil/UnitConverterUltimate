/*
 * Copyright 2018 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Singleton to access the Currency API
 */
public class CurrencyApi {

    private static final String BASE_URL = "https://www.ecb.europa.eu/stats/eurofxref/";
    private static CurrencyApi mInstance;

    private CurrencyService mService;

    private CurrencyApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mService = retrofit.create(CurrencyService.class);
    }

    public static CurrencyApi getInstance() {
        if (mInstance == null) {
            mInstance = new CurrencyApi();
        }

        return mInstance;
    }

    public CurrencyService getService() {
        return mService;
    }
}
