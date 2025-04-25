package due.giuaky.food_app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import due.giuaky.food_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navView = findViewById(R.id.nav_view);

        // Thiết lập Navigation
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.navigation_search, R.id.view)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Thêm listener để ẩn/hiện thanh navigation và bottom navigation
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.login_fragment || destination.getId() == R.id.register_fragment
                    || destination.getId() == R.id.forgot_password_fragment
                    || destination.getId() == R.id.reset_password_fragment
                    || destination.getId() == R.id.verification_code_fragment) {
                // Ẩn thanh ActionBar khi ở màn hình đăng nhập
                if (getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
                // Ẩn thanh Bottom Navigation khi ở màn hình đăng nhập
                navView.setVisibility(View.GONE);
            } else {
                // Hiện thanh ActionBar cho các màn hình khác
                if (getSupportActionBar() != null) {
                    getSupportActionBar().show();
                }
                // Hiện thanh Bottom Navigation cho các màn hình khác
                navView.setVisibility(View.VISIBLE);
            }
        });
    }
}