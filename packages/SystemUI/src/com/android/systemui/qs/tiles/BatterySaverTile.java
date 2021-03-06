/*
 * Copyright (C) 2014 The Android Open Source Project
 * Copyright (C) 2013-2015 The CyanogenMod Project
 * Copyright (C) 2015 The Euphoria-OS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.android.systemui.R;
import com.android.systemui.qs.QSTile;
import com.android.internal.logging.MetricsLogger;

/** Quick settings tile: Battery saver **/
public class BatterySaverTile extends QSTile<QSTile.BooleanState> {
    private final PowerManager mPowerMan;

    public BatterySaverTile(Host host) {
        super(host);
        mPowerMan = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
    }

    @Override
    protected BooleanState newTileState() {
        return new BooleanState();
    }

    @Override
    public void setListening(boolean listening) {
    }

    @Override
    public void handleClick() {
        mPowerMan.setPowerSaveMode(!mPowerMan.isPowerSaveMode());
        refreshState();
    }

    @Override
    public int getMetricsCategory() {
        return MetricsLogger.DISPLAY;
    }

    @Override
    protected void handleSecondaryClick() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.android.settings",
            "com.android.settings.Settings$BatterySaverSettingsActivity");
        mHost.startActivityDismissingKeyguard(intent);
    }

    @Override
    public void handleLongClick() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.android.settings",
            "com.android.settings.Settings$BatterySaverSettingsActivity");
        mHost.startActivityDismissingKeyguard(intent);
    }

    @Override
    protected void handleUpdateState(BooleanState state, Object arg) {
        state.visible = true;
        state.label = mContext.getString(R.string.quick_settings_battery_saver);
        state.icon = ResourceIcon
                .get(mPowerMan.isPowerSaveMode() ? R.drawable.ic_qs_battery_saver_on
                        : R.drawable.ic_qs_battery_saver_off);
    }
}
