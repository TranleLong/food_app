package due.giuaky.food_app.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import due.giuaky.food_app.R;

public class RegisterFragment extends Fragment {

    private EditText etFullName, etEmail, etPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private TextInputLayout tilFullName, tilEmail, tilPassword;

    // SharedPreferences for saving user data
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "UserPrefs";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Khởi tạo SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Ánh xạ các view
        etFullName = view.findViewById(R.id.etFullName);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        tvLogin = view.findViewById(R.id.tvLogin);

        tilFullName = view.findViewById(R.id.tilFullName);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);

        // Xử lý sự kiện click nút đăng ký
        btnRegister.setOnClickListener(v -> registerUser(view));

        // Xử lý sự kiện click vào đăng nhập
        tvLogin.setOnClickListener(v -> {
            // Chuyển đến màn hình đăng nhập
            Navigation.findNavController(view).navigate(R.id.action_register_fragment_to_login_fragment);
        });

        return view;
    }

    private void registerUser(View view) {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        boolean isValid = true;

        // Kiểm tra họ tên
        if (TextUtils.isEmpty(fullName)) {
            tilFullName.setError("Vui lòng nhập họ tên");
            isValid = false;
        } else {
            tilFullName.setError(null);
        }

        // Kiểm tra email
        if (TextUtils.isEmpty(email)) {
            tilEmail.setError("Vui lòng nhập email");
            isValid = false;
        } else if (!isValidEmail(email)) {
            tilEmail.setError("Email không hợp lệ");
            isValid = false;
        } else {
            tilEmail.setError(null);
        }

        // Kiểm tra password
        if (TextUtils.isEmpty(password)) {
            tilPassword.setError("Vui lòng nhập mật khẩu");
            isValid = false;
        } else if (password.length() < 6) {
            tilPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            isValid = false;
        } else {
            tilPassword.setError(null);
        }

        if (isValid) {
            // Lưu thông tin người dùng
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fullName", fullName);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();

            // Hiển thị thông báo đăng ký thành công
            showSuccessDialog(view);
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void showSuccessDialog(View view) {
        // Tạo dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_register_success, null);
        builder.setView(dialogView);

        // Tạo và hiển thị dialog
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        // Xử lý nút OK
        Button btnOk = dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> {
            dialog.dismiss();
            // Chuyển đến màn hình đăng nhập
            Navigation.findNavController(view).navigate(R.id.action_register_fragment_to_login_fragment);
        });
    }
}