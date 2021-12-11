package kg.geektech.taskapp37;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private SharedPreferences preferences;
    private final String HaveText = "HaveText";
    private final String HaveImage = "HaveImage";

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState() {
        preferences.edit().putBoolean("IsShown", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("isShown", false);
    }

    public void saveEditText(String text) {
        preferences.edit().putString(HaveText, text).apply();
    }

    public String getEditText() {
        return preferences.getString(HaveText, "");
    }

    public void saveImage(Uri s) {
        preferences.edit().putString(HaveImage,s.toString()).apply();

    }

    public void clearIMAGEUri(){
        preferences.edit().remove(HaveImage).apply();
    }

    public String getImage(){
        return preferences.getString(HaveImage,"");
    }
}
