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

package com.physphil.android.unitconverterultimate.ui;

/**
 * Interface to handle item clicks in recycler view
 * Created by pshadlyn on 4/24/2015.
 */
public interface RecyclerViewItemClickListener {

    /**
     * Called when an item is clicked in the recycler view list
     *
     * @param item     the selected item
     * @param position the position of the selected item in the list
     */
    void onListItemClicked(Object item, int position);
}
