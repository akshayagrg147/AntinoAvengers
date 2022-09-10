package com.antino.avengers.Others


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.antino.avengers.Utils.common.PREF_LOGGED_IN_USER
import com.antino.avengers.data.pojo.loginApi.response.LoginResponse
import com.google.gson.GsonBuilder

object PreferenceUtils {
    lateinit var mLocalPreferences: SharedPreferences
    lateinit var applicationContext: Context

    //Name of Shared Preference file
    private const val PREFERENCES_FILE_NAME = "com.bitla.ts"

    fun with(application: Application) {
        mLocalPreferences =
            application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
        applicationContext = application

    }
//    {START LOCAL PREFERENCE  SAVE}

    /**
     * Get data from mPreferenceUtil with key {key} & of type {obj}
     *
     * @param key          preference key
     * @param defautlValue default key for preference
     * @param <T>
     * @return
    </T> */
    fun <T> getPreference(key: String, defautlValue: T): T? {
        try {
            when (defautlValue) {
                is String -> {
                    return mLocalPreferences?.getString(key, defautlValue as String) as T
                }
                is Int -> {
                    return mLocalPreferences?.getInt(key, defautlValue as Int) as T
                }
                is Boolean -> {
                    return mLocalPreferences?.getBoolean(key, defautlValue as Boolean) as T
                }
                is Float -> {
                    return mLocalPreferences?.getFloat(key, defautlValue as Float) as T
                }
                is Long -> {
                    return mLocalPreferences?.getLong(key, defautlValue as Long) as T
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * Save data to mPreferenceUtil with key {key} & of type {obj}
     *
     * @param key
     * @param value
     * @param <T>
     * @return
    </T> */
    fun <T> setPreference(key: String, value: T) {
        try {
            val editor = mLocalPreferences?.edit()
            if (value is String) {
                editor?.putString(key, value as String)
            } else if (value is Int) {
                editor?.putInt(key, value as Int)
            } else if (value is Boolean) {
                editor?.putBoolean(key, value as Boolean)
            } else if (value is Float) {
                editor?.putFloat(key, value as Float)
            } else if (value is Long) {
                editor?.putLong(key, value as Long)
            }
            editor?.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> putObject(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        mLocalPreferences!!.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> getObject(key: String): T? {
        //We read JSON String which was saved.
        val value = mLocalPreferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type “T” is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    /**
     * clear key preference when required
     */
    fun removeKey(key: String) {
        mLocalPreferences.edit()?.remove(key)?.apply()
    }

    /**
     * clear preference when required
     */
    fun clearAllPreferences() {
        mLocalPreferences.edit()?.clear()?.apply()
    }

    fun putString(key: String, value: String?) {
        val edit = mLocalPreferences.edit()
        edit.putString(key, value)
        edit.commit()
    }

    fun getString(key: String): String? {
        return mLocalPreferences.getString(key, "")
    }

    fun removeString(key: String) {
        val edit = mLocalPreferences.edit()
        edit.remove(key)
        edit.apply()
    }

    fun hasKey(key: String): Boolean {
        return mLocalPreferences.contains(key)
    }

    fun getLogin(): LoginResponse? {
        return if (getObject<LoginResponse>(PREF_LOGGED_IN_USER) != null)
            getObject<LoginResponse>(PREF_LOGGED_IN_USER)
        else null
    }
}

