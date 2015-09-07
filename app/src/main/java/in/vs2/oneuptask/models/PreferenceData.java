package in.vs2.oneuptask.models;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceData {

	public static final String PREFS_SETTINGS = "OneUpTaskPreference";

	public static final String KEY_NAME = "Name";

	public static final String KEY_EMAIL = "Email";

	public static final String KEY_TOKEN = "Token";

	public static void setStringPrefs(String prefKey, Context context,
			String Value) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(prefKey, Value);
		editor.commit();
	}

	public static String getStringPrefs(String prefKey, Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		return settings.getString(prefKey, null);
	}

	public static void setBooleanPrefs(String prefKey, Context context,
			Boolean value) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		SharedPreferences.Editor editor = settings.edit();
	
		editor.putBoolean(prefKey, value);
		editor.commit();
	}

	public static boolean getBooleanPrefs(String prefKey, Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		return settings.getBoolean(prefKey, false);
	}
	
	public static boolean getEnableBooleanPrefs(String prefKey, Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		return settings.getBoolean(prefKey, true);
	}

	public static void setLongPref(String prefKey, Context context,
			long noOfResult) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS,0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(prefKey, noOfResult);
		editor.commit();
	}

	public static long getLongPref(String prefKey, Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		return settings.getLong(prefKey,0);
	}
	
	public static void setIntPref(String prefKey, Context context,
			int noOfResult) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS,0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(prefKey, noOfResult);
		editor.commit();
	}

	public static int getIntPref(String prefKey, Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		return settings.getInt(prefKey,0);
	}
	
	public static int getIntPref(String prefKey, Context context, int defValue) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_SETTINGS, 0);
		return settings.getInt(prefKey, defValue);
	}
	
	
	public static void clearPreference(Context context){
		  SharedPreferences settings = context.getSharedPreferences(PREFS_SETTINGS, 0);
	      SharedPreferences.Editor editor = settings.edit();
	      editor.clear();
	      editor.commit();
	}
	
	public static boolean userExist(Context context){
		String token = getStringPrefs(KEY_TOKEN, context);
		if (token != null && token.length() > 0) {
			return true;
		}else{
			return false;
		}
	}

	
}
