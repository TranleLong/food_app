package due.giuaky.food_app.activities;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import due.giuaky.food_app.R;
import due.giuaky.food_app.ui.home.HomeFragment;

public class LoginActivity extends Fragment {

    private EditText emailInput, passwordInput;
    private Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_login, container, false);

        emailInput = rootView.findViewById(R.id.email_input);
        passwordInput = rootView.findViewById(R.id.password_input);
        loginButton = rootView.findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> loginUser());

        return rootView;
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Kiểm tra tài khoản (chỉ là ví dụ, cần tích hợp hệ thống xác thực)
        if (email.equals("user@example.com") && password.equals("password123")) {
            // Chuyển tới màn hình chính sau khi đăng nhập thành công
            // Giả sử chuyển hướng đến MainActivity (có thể dùng Fragment hoặc Activity tùy trường hợp)
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navigation_home, new HomeFragment()) // MainFragment phải được tạo sẵn
                    .commit();
        } else {
            Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
