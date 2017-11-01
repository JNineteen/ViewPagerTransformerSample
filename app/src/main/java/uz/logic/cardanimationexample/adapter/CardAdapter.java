package uz.logic.cardanimationexample.adapter;

import android.support.v7.widget.CardView;

/**
 * Created by amirbain on 10/31/17.
 */

public interface CardAdapter {
    int MAX_ELEVATION_FACTOR = 6;

    float getTopElevation();
    CardView getCardViewAt(int position);

    int getCount();
}
