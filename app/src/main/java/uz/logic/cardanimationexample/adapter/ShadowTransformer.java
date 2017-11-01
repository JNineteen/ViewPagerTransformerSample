package uz.logic.cardanimationexample.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import uz.logic.cardanimationexample.R;

/**
 * Created by amirbain on 10/31/17.
 */

public class ShadowTransformer implements ViewPager.PageTransformer, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private CardAdapter mAdapter;
    private float mLastOffset;
    Context context;

    public ShadowTransformer(Context context,ViewPager viewPager, CardAdapter adapter) {
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
        this.context = context;
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float topElevation = mAdapter.getTopElevation();
        float bottomElevation = mAdapter.getTopElevation();

        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        CardView currentTopCard = mAdapter.getCardViewAt(realCurrentPosition);

        ImageView imageView = currentTopCard.findViewById(R.id.imageView);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (currentTopCard != null) {

//            currentTopCard.setTranslationY(10*Math.abs(realOffset));
            if (realOffset < 0.6) {
                imageView.setScaleX((float) (1 - realOffset));
                imageView.setScaleY((float) (1 - realOffset));
            }
            currentTopCard.setCardElevation((topElevation + topElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        CardView nextTopCard = mAdapter.getCardViewAt(nextPosition);
        ImageView nextImageView = nextTopCard.findViewById(R.id.imageView);
        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextTopCard != null) {

            Log.d("SCROLLING", "onPageScrolled: " + realOffset);
//            nextTopCard.setTranslationY(-10*Math.abs(realOffset));

            if (realOffset > 0.5) {
                nextImageView.setScaleX((float) (realOffset));
                nextImageView.setScaleY((float) (realOffset));
            }

            nextTopCard.setCardElevation((topElevation + topElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }




        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void transformPage(View page, float position) {

    }

}
