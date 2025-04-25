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

public class ForgotPasswordFragment extends Fragment {

    private EditText etResetEmail;
    private MaterialButton btnSendCode;
    private ImageButton btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        // Ánh xạ các view
        etResetEmail = view.findViewById(R.id.etResetEmail);
        btnSendCode = view.findViewById(R.id.btnSendCode);
        btnBack = view.findViewById(R.id.btnBack);

        // Xử lý sự kiện click nút quay lại
        btnBack.setOnClickListener(v -> {
            // Quay lại màn hình trước đó
            Navigation.findNavController(view).navigateUp();
        });

        // Xử lý sự kiện click nút gửi mã
        btnSendCode.setOnClickListener(v -> {
            resetPassword();
        });

        return view;
    }

    private void resetPassword() {
        String email = etResetEmail.getText().toString().trim();

        // Kiểm tra email
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(requireContext(), "Vui lòng nhập địa chỉ email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra định dạng email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "Vui lòng nhập địa chỉ email hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Hiển thị thông báo thành công
        showSuccessDialog("Gửi mã thành công", "Chúng tôi đã gửi mã xác nhận đến email của bạn. Vui lòng kiểm tra hộp thư đến.");
    }

    private void showSuccessDialog(String title, String message) {
        try {
            Dialog dialog = new Dialog(requireContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_success);

            // Thiết lập chiều rộng của dialog
            Window window = dialog.getWindow();
            if (window != null) {
                // Thiết lập dialog full width với margin
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

                // Chuyển đến màn hình nhập mã xác nhận
                String email = etResetEmail.getText().toString().trim();
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                Navigation.findNavController(requireView()).navigate(
                        R.id.action_forgot_password_fragment_to_verification_code_fragment, bundle);
            });

            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            Log.e("Dialog", "Error showing dialog: " + e.getMessage());
            Toast.makeText(requireContext(), "Lỗi hiển thị dialog: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}