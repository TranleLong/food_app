package due.giuaky.food_app.activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;

import due.giuaky.food_app.R;

public class ResetPasswordFragment extends Fragment {

    private EditText etNewPassword, etConfirmPassword;
    private MaterialButton btnResetPassword;
    private ImageButton btnBack;
    private String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        // Ánh xạ các view
        etNewPassword = view.findViewById(R.id.etNewPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        btnBack = view.findViewById(R.id.btnBack);

        // Lấy email từ arguments
        if (getArguments() != null) {
            email = getArguments().getString("email", "");
        }

        // Xử lý sự kiện click nút quay lại
        btnBack.setOnClickListener(v -> {
            // Quay lại màn hình trước đó
            Navigation.findNavController(view).navigateUp();
        });

        // Xử lý sự kiện click nút đổi mật khẩu
        btnResetPassword.setOnClickListener(v -> {
            resetPassword();
        });

        return view;
    }

    private void resetPassword() {
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Kiểm tra mật khẩu mới
        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(requireContext(), "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xác nhận mật khẩu
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(requireContext(), "Vui lòng xác nhận mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra độ dài mật khẩu
        if (newPassword.length() < 6) {
            Toast.makeText(requireContext(), "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra mật khẩu trùng khớp
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(requireContext(), "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // Hiển thị thông báo thành công
        showSuccessDialog("Đổi mật khẩu thành công", "Mật khẩu của bạn đã được thay đổi thành công. Vui lòng đăng nhập lại bằng mật khẩu mới.");
    }

    private void showSuccessDialog(String title, String message) {
        try {
            Dialog dialog = new Dialog(requireContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_success);

            // Thiết lập chiều rộng của dialog
            Window window = dialog.getWindow();
            if (window != null) {
                // Thiết lập dialog full width với margin (đã được định nghĩa trong CardView)
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // Thêm animation cho dialog
                window.setWindowAnimations(android.R.style.Animation_Dialog);
            }

            // Tìm các view trong dialog
            TextView tvTitle = dialog.findViewById(R.id.tvSuccessTitle);
            TextView tvMessage = dialog.findViewById(R.id.tvSuccessMessage);
            MaterialButton btnOk = dialog.findViewById(R.id.btnOk);

            // Thiết lập nội dung
            tvTitle.setText(title);
            tvMessage.setText(message);

            // Xử lý sự kiện click nút OK
            btnOk.setOnClickListener(v -> {
                dialog.dismiss();

                // Chuyển về màn hình đăng nhập
                try {
                    Navigation.findNavController(requireView()).navigate(
                            R.id.action_reset_password_fragment_to_login_fragment);
                } catch (Exception e) {
                    Log.e("Navigation", "Error navigating to login: " + e.getMessage());
                    Toast.makeText(requireContext(), "Lỗi khi chuyển về màn hình đăng nhập", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            Log.e("Dialog", "Error showing dialog: " + e.getMessage());
            Toast.makeText(requireContext(), "Lỗi hiển thị dialog: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            // Nếu dialog lỗi, vẫn cố gắng chuyển màn hình
            try {
                Navigation.findNavController(requireView()).navigate(
                        R.id.action_reset_password_fragment_to_login_fragment);
            } catch (Exception navEx) {
                Log.e("Navigation", "Error navigating: " + navEx.getMessage());
            }
        }
    }
}