package uz.logic.cardanimationexample.model;

/**
 * Created by amirbain on 10/31/17.
 */

public class CardItem {

    public String cityName;
    public String imageUrl;


    public CardItem(String cityName, String imageUrl) {
        this.cityName = cityName;
        this.imageUrl = imageUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
