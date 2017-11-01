package uz.logic.cardanimationexample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.logic.cardanimationexample.adapter.CardPagerAdapter;
import uz.logic.cardanimationexample.adapter.ShadowTransformer;
import uz.logic.cardanimationexample.model.CardItem;

public class MainActivity extends AppCompatActivity implements CardPagerAdapter.ClickListener,View.OnClickListener{
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    CardPagerAdapter cardPagerAdapter;
    ShadowTransformer shadowTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cardPagerAdapter = new CardPagerAdapter(this);
        cardPagerAdapter.addCardItem(new CardItem("New York","google.com"));
        cardPagerAdapter.addCardItem(new CardItem("New York","google.com"));
        cardPagerAdapter.addCardItem(new CardItem("New York","google.com"));
        cardPagerAdapter.addCardItem(new CardItem("New York","google.com"));
        cardPagerAdapter.addCardItem(new CardItem("New York","google.com"));

        shadowTransformer = new ShadowTransformer(viewPager,cardPagerAdapter);
        viewPager.setAdapter(cardPagerAdapter);
//        shadowTransformer.enableScaling(true);
        viewPager.setPageTransformer(false, shadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipToPadding(false);


    }


    @Override
    public void onClick(View view) {

        new CardPagerAdapter.ClickListener() {
            @Override
            public void onClick(View view) {
                shadowTransformer.enableScaling(true);
            }
        };
    }
}
