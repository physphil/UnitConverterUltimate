/*
 * Copyright 2016 Phil Shadlyn
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

package com.physphil.android.unitconverterultimate.presenters;

import android.content.Context;

import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.ConversionState;

/**
 * View methods to be implemented for Conversion Fragment
 * Created by Phizz on 16-09-10.
 */
public interface ConversionView {
    Context getContext();
    void showUnitsList(Conversion conversion);
    void showProgressCircle();
    void showLoadingError(int message);
    void restoreConversionState(final ConversionState state);
    void showResult(double result);
    void updateCurrencyConversion();
    void showToast(int message);
    void showToastError(int message);
}
