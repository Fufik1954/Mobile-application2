package ru.mirea.kartyshovav.catcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.kartyshovav.catcatalog.data.auth.FirebaseAuthRepositoryImpl;
import ru.mirea.kartyshovav.catcatalog.data.storage.sharedprefs.SharedPrefUserStorage;
import ru.mirea.kartyshovav.catcatalog.domain.models.User;
import ru.mirea.kartyshovav.catcatalog.domain.repository.AuthRepository;
import ru.mirea.kartyshovav.catcatalog.domain.repository.UserStorage;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.auth.LoginUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.user.SaveUserUseCase;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private AuthRepository authRepository;
    private UserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        authRepository = new FirebaseAuthRepositoryImpl();
        userStorage = new SharedPrefUserStorage(this);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите email и пароль", Toast.LENGTH_SHORT).show();
                return;
            }

            LoginUseCase loginUseCase = new LoginUseCase(authRepository);
            loginUseCase.execute(email, password, new AuthRepository.AuthCallback() {
                @Override
                public void onSuccess() {
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (firebaseUser != null) {
                        User user = new User(firebaseUser.getUid(), firebaseUser.getEmail());
                        SaveUserUseCase saveUserUseCase = new SaveUserUseCase(userStorage);
                        saveUserUseCase.execute(user);
                    }

                    Toast.makeText(LoginActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(LoginActivity.this, "Ошибка: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        });

        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите email и пароль", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            if (firebaseUser != null) {
                                User user = new User(firebaseUser.getUid(), firebaseUser.getEmail());
                                SaveUserUseCase saveUserUseCase = new SaveUserUseCase(userStorage);
                                saveUserUseCase.execute(user);
                            }

                            Toast.makeText(LoginActivity.this, "Аккаунт создан", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String error = task.getException() != null
                                    ? task.getException().getMessage()
                                    : "Ошибка регистрации";
                            Toast.makeText(LoginActivity.this, "Ошибка: " + error, Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}