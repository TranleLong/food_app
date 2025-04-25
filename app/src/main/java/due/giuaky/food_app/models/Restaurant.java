package due.giuaky.food_app.models;

public class Restaurant {
    private int id;
    private String name;
    private String cuisineType1;
    private String cuisineType2;
    private String deliveryTime;
    private String price;
    private float rating;
    private int imageResourceId;
    private String description;

    public Restaurant(String name, String cuisineType1, String cuisineType2, String deliveryTime, String price, float rating, int imageResourceId) {
        this.name = name;
        this.cuisineType1 = cuisineType1;
        this.cuisineType2 = cuisineType2;
        this.deliveryTime = deliveryTime;
        this.price = price;
        this.rating = rating;
        this.imageResourceId = imageResourceId;
    }

    public Restaurant(int id, String name, String description, String deliveryTime, String price, float rating, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deliveryTime = deliveryTime;
        this.price = price;
        this.rating = rating;
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisineType1() {
        return cuisineType1;
    }

    public void setCuisineType1(String cuisineType1) {
        this.cuisineType1 = cuisineType1;
    }

    public String getCuisineType2() {
        return cuisineType2;
    }

    public void setCuisineType2(String cuisineType2) {
        this.cuisineType2 = cuisineType2;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}