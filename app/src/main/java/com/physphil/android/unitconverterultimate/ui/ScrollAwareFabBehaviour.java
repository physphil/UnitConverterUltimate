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

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Behaviour to hide/show FAB on scroll
 * Created by Phizz on 16-09-11.
 */
public class ScrollAwareFabBehaviour extends FloatingActionButton.Behavior {

    public ScrollAwareFabBehaviour(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(
        @NonNull CoordinatorLayout coordinatorLayout,
        @NonNull FloatingActionButton child,
        @NonNull View directTargetChild,
        @NonNull View target,
        int axes,
        int type
    ) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL ||
            super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(
        @NonNull CoordinatorLayout coordinatorLayout,
        @NonNull FloatingActionButton child,
        @NonNull View target,
        int dxConsumed,
        int dyConsumed,
        int dxUnconsumed,
        int dyUnconsumed,
        int type
    ) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            child.hide(fabVisibilityChangedListener);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            child.show();
        }
    }

    private FloatingActionButton.OnVisibilityChangedListener fabVisibilityChangedListener = new FloatingActionButton.OnVisibilityChangedListener() {
        @Override
        public void onHidden(FloatingActionButton fab) {
            // Starting with support lib 25, hiding the FAB sets its visibility to View.GONE instead of INVISIBLE. This messes up the scrolling
            // https://stackoverflow.com/questions/41153619/floating-action-button-not-visible-on-scrolling-after-updating-google-support
            super.onHidden(fab);
            fab.setVisibility(View.INVISIBLE);
        }
    };
}
