package uz.logic.cardanimationexample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.logic.cardanimationexample.adapter.CardPagerAdapter;
import uz.logic.cardanimationexample.adapter.ShadowTransformer;
import uz.logic.cardanimationexample.model.CardItem;

public class MainActivity extends AppCompatActivity implements CardPagerAdapter.ClickListener {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    CardPagerAdapter cardPagerAdapter;
    ShadowTransformer shadowTransformer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cardPagerAdapter = new CardPagerAdapter();


        cardPagerAdapter.addCardItem(new CardItem("Food ", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Twemoji_1f35d.svg/2000px-Twemoji_1f35d.svg.png", "This is so delicious"));
        cardPagerAdapter.addCardItem(new CardItem("Hot Dog", "https://images.vexels.com/media/users/3/143380/isolated/preview/5b77066837d27da03045a6705d99ef57-burrito-cartoon-food-by-vexels.png", "This is so bitter"));
        cardPagerAdapter.addCardItem(new CardItem("Whatever", "https://images.vexels.com/media/users/3/143479/isolated/preview/e9f70459fc4462131316b495865cd4f8-taco-icon-cartoon-by-vexels.png", "This is so awkward"));
        cardPagerAdapter.addCardItem(new CardItem("Piece of cake", "https://images.vexels.com/media/users/3/143457/isolated/preview/73c5d5c0c041987c54557d515154cbd9-quesadilla-food-icon-by-vexels.png", "This is so cool"));
        cardPagerAdapter.addCardItem(new CardItem("Chips", "https://images.vexels.com/media/users/3/143446/isolated/preview/8890d1b93b1909c16d8101864be943f0-nachos-cartoon-food-by-vexels.png", "This is so amazing"));

        shadowTransformer = new ShadowTransformer(this,viewPager, cardPagerAdapter);
        viewPager.setAdapter(cardPagerAdapter);
        viewPager.setPageTransformer(false, shadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipToPadding(false);

    }


    @Override
    public void onClick(CardView firstCard, int position) {



    }

    @Override
    public void onFlingUp(CardView firstCard,int position) {
        firstCard.setTranslationY(-100);
    }

    @Override
    public void onFlingDown(CardView firstCard, int position) {
        firstCard.setTranslationY(100);
    }

    public int convertDipToPixels(float dips) {
        return (int) (dips * MainActivity.this.getResources().getDisplayMetrics().density + 0.5f);
    }


}
