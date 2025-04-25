package due.giuaky.food_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import due.giuaky.food_app.R;
import due.giuaky.food_app.adapters.FeaturedRestaurantAdapter;
import due.giuaky.food_app.adapters.FoodAdapter;
import due.giuaky.food_app.adapters.RestaurantAdapter;
import due.giuaky.food_app.models.Food;
import due.giuaky.food_app.models.Restaurant;

public class HomeFragment extends Fragment implements FoodAdapter.OnItemClickListener,
        FeaturedRestaurantAdapter.OnItemClickListener, RestaurantAdapter.OnItemClickListener {

    private RecyclerView rvPopularFoods, rvFeaturedRestaurants, rvAllRestaurants;
    private FoodAdapter foodAdapter;
    private FeaturedRestaurantAdapter featuredRestaurantAdapter;
    private RestaurantAdapter restaurantAdapter;
    private List<Food> foodList;
    private List<Restaurant> featuredRestaurantList;
    private List<Restaurant> allRestaurantList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo RecyclerViews
        rvPopularFoods = view.findViewById(R.id.rvPopularFoods);
        rvFeaturedRestaurants = view.findViewById(R.id.rvFeaturedRestaurants);
        rvAllRestaurants = view.findViewById(R.id.rvAllRestaurants);

        // Khởi tạo dữ liệu
        initFoodData();
        initFeaturedRestaurantData();
        initAllRestaurantData();

        // Thiết lập adapter cho Popular Foods
        LinearLayoutManager foodLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvPopularFoods.setLayoutManager(foodLayoutManager);
        foodAdapter = new FoodAdapter(getContext(), foodList, this);
        rvPopularFoods.setAdapter(foodAdapter);

        // Thiết lập adapter cho Featured Restaurants
        LinearLayoutManager featuredLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFeaturedRestaurants.setLayoutManager(featuredLayoutManager);
        featuredRestaurantAdapter = new FeaturedRestaurantAdapter(getContext(), featuredRestaurantList, this);
        rvFeaturedRestaurants.setAdapter(featuredRestaurantAdapter);

        // Thiết lập adapter cho All Restaurants
        GridLayoutManager allRestaurantsLayoutManager = new GridLayoutManager(getContext(), 1);
        rvAllRestaurants.setLayoutManager(allRestaurantsLayoutManager);
        restaurantAdapter = new RestaurantAdapter(getContext(), allRestaurantList, this);
        rvAllRestaurants.setAdapter(restaurantAdapter);

        return view;
    }

    private void initFoodData() {
        foodList = new ArrayList<>();
        foodList.add(new Food(1, "Krispy Creme", "Krispy Creme Donut", "5$", R.drawable.cake4));
        foodList.add(new Food(2, "Mario Raisins", "Rice Bowl, Pasta", "8$", R.drawable.rice));
        foodList.add(new Food(3, "Burger King", "Burger, Fast Food", "6$", R.drawable.cake));
        foodList.add(new Food(4, "Pizza Hut", "Pizza, Italian", "10$", R.drawable.cake10));
    }

    private void initFeaturedRestaurantData() {
        featuredRestaurantList = new ArrayList<>();
        featuredRestaurantList.add(new Restaurant(1, "McDonald's", "Fast Food • Burger • Chicken", "25min", "Free", 4.5f, R.drawable.strawbery));
        featuredRestaurantList.add(new Restaurant(2, "The Halal Guy", "Middle Eastern • Halal • Rice", "30min", "15.000đ", 4.7f, R.drawable.cake6));
        featuredRestaurantList.add(new Restaurant(3, "KFC", "Fast Food • Chicken • Burger", "20min", "Free", 4.3f, R.drawable.cake7));
    }

    private void initAllRestaurantData() {
        allRestaurantList = new ArrayList<>();
        allRestaurantList.add(new Restaurant("McDonald's", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake8));
        allRestaurantList.add(new Restaurant("Cafe Bircher's", "American", "Salad food", "30min", "Free", 4.7f, R.drawable.cake4));
        allRestaurantList.add(new Restaurant("KFC Foods", "Chinese", "American", "25min", "Free", 4.5f, R.drawable.cake5));
    }

    @Override
    public void onItemClick(Food food, int position) {
        Toast.makeText(getContext(), "Đã chọn món: " + food.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Restaurant restaurant, int position) {
        Toast.makeText(getContext(), "Đã chọn nhà hàng: " + restaurant.getName(), Toast.LENGTH_SHORT).show();
    }
}