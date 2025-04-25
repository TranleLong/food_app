package due.giuaky.food_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import due.giuaky.food_app.R;
import due.giuaky.food_app.adapters.RestaurantAdapter;
import com.example.delingon.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListFragment extends Fragment implements RestaurantAdapter.OnItemClickListener {

    private RecyclerView rvRestaurants;
    private RestaurantAdapter adapter;
    private List<Restaurant> restaurantList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        rvRestaurants = view.findViewById(R.id.rvRestaurants);

        // Khởi tạo danh sách nhà hàng
        initRestaurantData();

        // Thiết lập RecyclerView với GridLayoutManager 2 cột
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvRestaurants.setLayoutManager(layoutManager);

        // Thiết lập Adapter
        adapter = new RestaurantAdapter(getContext(), restaurantList, this);
        rvRestaurants.setAdapter(adapter);

        return view;
    }

    private void initRestaurantData() {
        restaurantList = new ArrayList<>();

        // Thêm dữ liệu mẫu
        restaurantList.add(new Restaurant("Tacos Nanchas", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.bread));
        restaurantList.add(new Restaurant("McDonald's", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake));
        restaurantList.add(new Restaurant("KFC Foods", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.rice));
        restaurantList.add(new Restaurant("Cafe MayField's", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake2));
        restaurantList.add(new Restaurant("Burger King", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake3));
        restaurantList.add(new Restaurant("Coffee House", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake4));
        restaurantList.add(new Restaurant("Tacos Nanchas", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake5));
        restaurantList.add(new Restaurant("McDonald's", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake6));
        restaurantList.add(new Restaurant("KFC Foods", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake7));
        restaurantList.add(new Restaurant("Cafe MayField's", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake8));
        restaurantList.add(new Restaurant("Burger King", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake9));
        restaurantList.add(new Restaurant("Coffee House", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake10));
    }

    @Override
    public void onItemClick(Restaurant restaurant, int position) {
        // Xử lý khi người dùng nhấn vào nhà hàng
        Toast.makeText(getContext(), "Đã chọn: " + restaurant.getName(), Toast.LENGTH_SHORT).show();
    }
}