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

package com.physphil.android.unitconverterultimate

import android.content.Context
import android.content.Intent
import android.os.Bundle

class AcknowledgementsActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) =
            context.startActivity(
                Intent(context, AcknowledgementsActivity::class.java)
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acknowledgements)

        setupToolbar()
        setToolbarHomeNavigation(true)
    }
}