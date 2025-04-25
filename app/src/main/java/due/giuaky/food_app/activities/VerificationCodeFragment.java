package due.giuaky.food_app.activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class VerificationCodeFragment extends Fragment {

    private EditText etVerificationCode;
    private MaterialButton btnVerify;
    private ImageButton btnBack;
    private TextView tvResendCode;
    private TextView tvEmailInfo;
    private String email;
    private CountDownTimer resendTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification_code, container, false);

        // Ánh xạ các view
        etVerificationCode = view.findViewById(R.id.etVerificationCode);
        btnVerify = view.findViewById(R.id.btnVerify);
        btnBack = view.findViewById(R.id.btnBack);
        tvResendCode = view.findViewById(R.id.tvResendCode);
        tvEmailInfo = view.findViewById(R.id.tvEmailInfo);

        // Lấy email từ arguments
        if (getArguments() != null) {
            email = getArguments().getString("email", "");
            tvEmailInfo.setText("Chúng tôi đang gửi mã đến email " + email);
        }

        // Xử lý sự kiện click nút quay lại
        btnBack.setOnClickListener(v -> {
            // Quay lại màn hình trước đó
            Navigation.findNavController(view).navigateUp();
        });

        // Xử lý sự kiện click nút xác nhận
        btnVerify.setOnClickListener(v -> {
            verifyCode();
        });

        // Xử lý sự kiện click vào gửi lại mã
        tvResendCode.setOnClickListener(v -> {
            resendCode();
        });

        // Bắt đầu đếm ngược cho nút gửi lại
        startResendTimer();

        return view;
    }

    private void startResendTimer() {
        tvResendCode.setEnabled(false);
        tvResendCode.setAlpha(0.5f);

        resendTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvResendCode.setText("Gửi lại (" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                tvResendCode.setText("Gửi lại");
                tvResendCode.setEnabled(true);
                tvResendCode.setAlpha(1.0f);
            }
        }.start();
    }

    private void resendCode() {
        // Hiển thị thông báo đã gửi lại mã
        Toast.makeText(requireContext(), "Đã gửi lại mã xác nhận. Vui lòng kiểm tra email của bạn.", Toast.LENGTH_SHORT).show();

        // Bắt đầu lại đếm ngược
        startResendTimer();
    }

    private void verifyCode() {
        String code = etVerificationCode.getText().toString().trim();

        // Kiểm tra mã xác nhận
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(requireContext(), "Vui lòng nhập mã xác nhận", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra độ dài mã xác nhận
        if (code.length() < 6) {
            Toast.makeText(requireContext(), "Mã xác nhận phải có 6 chữ số", Toast.LENGTH_SHORT).show();
            return;
        }

        // Hiển thị thông báo thành công
        showSuccessDialog("Xác nhận thành công", "Mã xác nhận hợp lệ. Vui lòng tạo mật khẩu mới.");
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

                // Chuyển đến màn hình đặt lại mật khẩu
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                Navigation.findNavController(requireView()).navigate(
                        R.id.action_verification_code_fragment_to_reset_password_fragment, bundle);
            });

            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            Log.e("Dialog", "Error showing dialog: " + e.getMessage());
            Toast.makeText(requireContext(), "Lỗi hiển thị dialog: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            // Nếu dialog lỗi, vẫn cố gắng chuyển màn hình
            try {
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                Navigation.findNavController(requireView()).navigate(
                        R.id.action_verification_code_fragment_to_reset_password_fragment, bundle);
            } catch (Exception navEx) {
                Log.e("Navigation", "Error navigating: " + navEx.getMessage());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (resendTimer != null) {
            resendTimer.cancel();
        }
    }
}