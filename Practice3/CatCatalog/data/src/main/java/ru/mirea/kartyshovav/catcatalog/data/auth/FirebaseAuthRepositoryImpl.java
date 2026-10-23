package ru.mirea.kartyshovav.catcatalog.data.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.kartyshovav.catcatalog.domain.repository.AuthRepository;

public class FirebaseAuthRepositoryImpl implements AuthRepository {

    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthRepositoryImpl() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password, AuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            callback.onSuccess();
                        } else {
                            callback.onFailure("Пользователь не найден");
                        }
                    } else {
                        String error = task.getException() != null
                                ? task.getException().getMessage()
                                : "Ошибка авторизации";
                        callback.onFailure(error);
                    }
                });
    }

    @Override
    public void logout() {
        firebaseAuth.signOut();
    }
}