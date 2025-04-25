package due.giuaky.food_app.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import due.giuaky.food_app.R;

public class LoginActivity extends Fragment {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister, tvForgotPassword;

    // Demo credentials
    private static final String DEMO_EMAIL = "user@example.com";
    private static final String DEMO_PASSWORD = "password";

    // SharedPreferences for saving login state
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        // Khởi tạo SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Ánh xạ các view
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvRegister = view.findViewById(R.id.tvRegister);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);

        // Xử lý sự kiện click nút đăng nhập
        btnLogin.setOnClickListener(v -> loginUser(view));

        // Xử lý sự kiện click vào đăng ký
        tvRegister.setOnClickListener(v -> {
            // Chuyển đến màn hình đăng ký
            Navigation.findNavController(view).navigate(R.id.action_login_fragment_to_register_fragment);
        });

        // Thay thế đoạn code xử lý sự kiện click vào quên mật khẩu trong LoginActivity.java
        tvForgotPassword.setOnClickListener(v -> {
            // Chuyển đến màn hình quên mật khẩu
            Navigation.findNavController(view).navigate(R.id.action_login_fragment_to_forgot_password_fragment);
        });

        return view;
    }


    private void loginUser(View view) {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Kiểm tra email
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Vui lòng nhập email");
            return;
        }

        // Kiểm tra password
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Vui lòng nhập mật khẩu");
            return;
        }

        // Kiểm tra thông tin đăng nhập (demo)
        if (email.equals(DEMO_EMAIL) && password.equals(DEMO_PASSWORD)) {
            // Lưu trạng thái đăng nhập
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_IS_LOGGED_IN, true);
            editor.apply();

            // Chuyển đến Home Fragment
            Navigation.findNavController(view).navigate(R.id.action_login_fragment_to_navigation_home);
        } else {
            Toast.makeText(requireContext(), "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }
}