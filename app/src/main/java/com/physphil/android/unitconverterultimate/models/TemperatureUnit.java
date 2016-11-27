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

package com.physphil.android.unitconverterultimate.models;

/**
 * Temperature Unit for conversion
 * Created by Phizz on 15-07-31.
 */
public final class TemperatureUnit extends Unit {

    /**
     * Create a new Temperature unit
     *
     * @param id            unit id
     * @param labelResource resource of string label
     */
    public TemperatureUnit(int id, int labelResource) {
        // Don't require conversion info, handled in code
        super(id, labelResource, 0.0, 0.0);
    }
}
