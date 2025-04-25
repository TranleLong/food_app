package due.giuaky.food_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import due.giuaky.food_app.R;
import due.giuaky.food_app.models.Restaurant;

public class FeaturedRestaurantAdapter extends RecyclerView.Adapter<FeaturedRestaurantAdapter.RestaurantViewHolder> {

    private Context context;
    private List<Restaurant> restaurantList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant, int position);
    }

    public FeaturedRestaurantAdapter(Context context, List<Restaurant> restaurantList, OnItemClickListener listener) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_featured_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);

        holder.tvRestaurantName.setText(restaurant.getName());
        holder.tvRestaurantDescription.setText(restaurant.getDescription());
        holder.tvDeliveryTime.setText(restaurant.getDeliveryTime());
        holder.tvDeliveryFee.setText(restaurant.getPrice());
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
        TextView tvRestaurantName, tvRestaurantDescription, tvDeliveryTime, tvDeliveryFee, tvRating;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRestaurantImage = itemView.findViewById(R.id.ivRestaurantImage);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantDescription = itemView.findViewById(R.id.tvRestaurantDescription);
            tvDeliveryTime = itemView.findViewById(R.id.tvDeliveryTime);
            tvDeliveryFee = itemView.findViewById(R.id.tvDeliveryFee);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}