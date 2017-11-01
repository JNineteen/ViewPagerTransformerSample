package uz.logic.cardanimationexample.model;

/**
 * Created by amirbain on 10/31/17.
 */

public class CardItem {

    public String cityName;
    public String imageUrl;
    public String message;

    public CardItem(String cityName, String imageUrl, String message) {
        this.cityName = cityName;
        this.imageUrl = imageUrl;
        this.message = message;
    }

    public String getCityName() {
        return cityName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMessage() {
        return message;
    }
}
