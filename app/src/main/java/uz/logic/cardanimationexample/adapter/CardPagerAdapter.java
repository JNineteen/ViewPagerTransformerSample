package uz.logic.cardanimationexample.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uz.logic.cardanimationexample.R;
import uz.logic.cardanimationexample.model.CardItem;

/**
 * Created by amirbain on 10/31/17.
 */

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> cardViewList;
    private List<CardItem> cardItemList;


    private float topElevation;
    private float bottomElevation;

    public CardPagerAdapter() {
        cardViewList = new ArrayList<>();
        cardItemList = new ArrayList<>();

    }

    public void addCardItem(CardItem item) {
        cardViewList.add(null);
        cardItemList.add(item);
    }

    @Override
    public float getTopElevation() {
        return topElevation;
    }


    @Override
    public CardView getCardViewAt(int position) {
        return cardViewList.get(position);
    }


    @Override
    public int getCount() {
        return cardItemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    ClickListener clickListener;
     CardView firstCardView;
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_card, container, false);
        container.addView(view);
        bind(cardItemList.get(position), view);
       firstCardView = (CardView) view.findViewById(R.id.cardView);


        if (topElevation == 0) {
            topElevation = firstCardView.getCardElevation();
        }


        firstCardView.setMaxCardElevation(topElevation * MAX_ELEVATION_FACTOR);


        cardViewList.set(position, firstCardView);

        clickListener = (ClickListener) firstCardView.getContext();

        final GestureDetector gestureDetector = new GestureDetector(container.getContext(), new GestureListener(firstCardView,position));

        firstCardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    return false;
                }
                return true;
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        cardViewList.set(position, null);
    }

    private void bind(CardItem item, View view) {
        TextView titleTextView = (TextView) view.findViewById(R.id.text_view_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        titleTextView.setText(item.getCityName());
        Picasso.with(view.getContext()).load(item.getImageUrl()).into(imageView);
    }

    public interface ClickListener {
        void onClick(CardView firstCard, int position);

        void onFlingUp(CardView firstCard, int position);

        void onFlingDown(CardView firstCard, int position);
    }


    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        CardView firstCard;  int position;

        public GestureListener(CardView firstCard, int position) {
            this.firstCard = firstCard;
            this.position = position;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Right to left
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Left to right
            }

            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                clickListener.onFlingUp(firstCard,position);
                return false; // Bottom to top
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                clickListener.onFlingDown(firstCard,position);
                return false; // Top to bottom
            }
            return false;
        }
    }


}
