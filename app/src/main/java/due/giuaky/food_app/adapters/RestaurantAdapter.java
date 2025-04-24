package due.giuaky.food_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.delingon.model.Restaurant;

import java.util.List;

import due.giuaky.food_app.R;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private Context context;
    private List<Restaurant> restaurantList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant, int position);
    }

    public RestaurantAdapter(Context context, List<Restaurant> restaurantList, OnItemClickListener listener) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);

        holder.tvRestaurantName.setText(restaurant.getName());
        holder.tvCuisineType1.setText(restaurant.getCuisineType1());
        holder.tvCuisineType2.setText(restaurant.getCuisineType2());
        holder.tvDeliveryTime.setText(restaurant.getDeliveryTime());
        holder.tvPrice.setText(restaurant.getPrice());
        holder.tvRating.setText(String.valueOf(restaurant.getRating()));
        holder.ivRestaurantImage.setImageResource(restaurant.getImageResourceId());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(restaurant, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRestaurantImage;
        TextView tvRestaurantName, tvCuisineType1, tvCuisineType2, tvDeliveryTime, tvPrice, tvRating;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRestaurantImage = itemView.findViewById(R.id.ivRestaurantImage);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvCuisineType1 = itemView.findViewById(R.id.tvCuisineType1);
            tvCuisineType2 = itemView.findViewById(R.id.tvCuisineType2);
            tvDeliveryTime = itemView.findViewById(R.id.tvDeliveryTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}