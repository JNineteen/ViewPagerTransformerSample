package uz.logic.cardanimationexample.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
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
    private float mBaseElevation;
    ClickListener clickListener;

    public CardPagerAdapter(ClickListener clickListener) {
        cardViewList = new ArrayList<>();
        cardItemList = new ArrayList<>();
        this.clickListener=clickListener;
    }

    public void addCardItem(CardItem item) {
        cardViewList.add(null);
        cardItemList.add(item);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
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


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_card, container, false);
        container.addView(view);
        bind(cardItemList.get(position), view);
        final CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        cardViewList.set(position, cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(cardView);
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
        public void onClick(View view);
    }
}
