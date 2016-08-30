/*
 * Copyright 2016 Phil Shadlyn
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

package com.physphil.android.unitconverterultimate.api.models;

/**
 * Response object sent from Fixer.io API
 * Created by Phizz on 16-07-26.
 */
public class CurrencyResponse
{
    private String base;
    private String date;
    private Rates rates;

    public String getBase()
    {
        return base;
    }

    public String getDate()
    {
        return date;
    }

    public Rates getRates()
    {
        return rates;
    }
}
