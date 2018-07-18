package com.quellxcode.baksho;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;

import cz.msebera.android.httpclient.client.cache.Resource;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

import static com.quellxcode.baksho.R.styleable.MenuItem;
import static java.util.logging.Logger.global;


/**
 * Created by Bukhari on 12/15/2017.
 */


public class Home extends AppCompatActivity { /* When using Appcombat support library
                                                         you need to extend Main Activity to
                                                         ActionBarActivity.
                                                      */


    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Home", "Update Address", "About Us"};
    int ICONS[] = {R.drawable.home_icon, R.drawable.location_icon, R.drawable.aboutus_icon};
    ViewPager mViewPager;
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Baksho";
    String EMAIL = "Baksho@gmail.com";
    int PROFILE = R.drawable.user;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;

    SliderLayout sliderShow;
    ActionBar actionbar;
    private FeatureCoverFlow mCoverFlow;
    private CoverflowAdapter cAdapter;
    private ArrayList<CoverflowEntity> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;


   /* private FeatureCoverFlow mCoverFlow1;
    private CoverflowAdapter cAdapter1;
    private ArrayList<CoverflowEntity> mData1 = new ArrayList<>(0);
    private TextSwitcher mTitle1;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);







        /*toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NavigationAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        // ImageView imageView= (ImageView) findViewById(R.id.btn_menu);

        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            boolean isDrawerOpen;

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().hide();
                actionbar.setDisplayHomeAsUpEnabled(false);

            }

          /*  @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset > .55 && !isDrawerOpen){
                    onDrawerOpened(drawerView);
                    getSupportActionBar().hide();
                    isDrawerOpen = true;
                } else if(slideOffset < .45 && isDrawerOpen) {
                    onDrawerClosed(drawerView);
                    getSupportActionBar().show();
                    isDrawerOpen = false;
                }
            }*/

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionbar.setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().show();
            }
        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState(); // Finally we set the drawer toggle sync State


// ************************************************************ Top Slider*************************************************************************************************
        sliderShow = (SliderLayout) findViewById(R.id.slider);

        TextSliderView textSliderView1 = new TextSliderView(this);
        textSliderView1
                .description("AC")
                .image(R.drawable.slide2);
        TextSliderView textSliderView2 = new TextSliderView(this);
        textSliderView2
                .description("Electrical")
                .image(R.drawable.slide1);
        TextSliderView textSliderView3 = new TextSliderView(this);
        textSliderView3
                .description("Elactronics")
                .image(R.drawable.slide3);

        sliderShow.addSlider(textSliderView1);
        sliderShow.addSlider(textSliderView2);
        sliderShow.addSlider(textSliderView3);
        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderShow.setCustomAnimation(new DescriptionAnimation());
        sliderShow.setDuration(3500);
        sliderShow.startAutoCycle();


        // ************************************************************ Category Slider*************************************************************************************************

        mData.add(new CoverflowEntity(R.drawable.electric, R.string.title1));
        mData.add(new CoverflowEntity(R.drawable.ups, R.string.title2));
        mData.add(new CoverflowEntity(R.drawable.ac, R.string.title3));


        mTitle = (TextSwitcher) findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(Home.this);
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                return textView;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        cAdapter = new CoverflowAdapter(this);
        cAdapter.setData(mData);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(cAdapter);

        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Home.this,
                        getResources().getString(mData.get(position).titleResId),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(getResources().getString(mData.get(position).titleResId));
            }

            @Override
            public void onScrolling() {
                mTitle.setText("");
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportActionBar().hide();
        Drawer.openDrawer(GravityCompat.START);

        return true;
    }


    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }


}


