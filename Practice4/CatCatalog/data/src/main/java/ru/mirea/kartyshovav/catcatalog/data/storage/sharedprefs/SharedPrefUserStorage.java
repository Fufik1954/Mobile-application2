package ru.mirea.kartyshovav.catcatalog.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.kartyshovav.catcatalog.domain.models.User;
import ru.mirea.kartyshovav.catcatalog.domain.repository.UserStorage;

public class SharedPrefUserStorage implements UserStorage {

    private static final String PREFS_NAME = "user_prefs";
    private static final String KEY_UID = "user_uid";
    private static final String KEY_EMAIL = "user_email";

    private final SharedPreferences sharedPreferences;

    public SharedPrefUserStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean save(User user) {
        if (user == null) return false;

        sharedPreferences.edit()
                .putString(KEY_UID, user.getUid())
                .putString(KEY_EMAIL, user.getEmail())
                .apply();

        return true;
    }

    @Override
    public User get() {
        String uid = sharedPreferences.getString(KEY_UID, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (uid == null || email == null) {
            return null;
        }

        return new User(uid, email);
    }
}