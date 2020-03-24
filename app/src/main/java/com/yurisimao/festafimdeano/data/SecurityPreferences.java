package com.yurisimao.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(final Context mContext) {
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE);
    }

    public void storeString(final String key, final String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoredString(final String key) {
        return this.mSharedPreferences.getString(key, "");
    }

}
