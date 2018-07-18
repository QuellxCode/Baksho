package com.quellxcode.baksho;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by QuellX on 12/19/2017.
 */

public class SliderAdapter extends PagerAdapter {
    Context mContext;
    View view;
    SliderAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    private int[] sliderImagesId = new int[]{
            R.drawable.slide1, R.drawable.slide2, R.drawable.slide3
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);

     //   mImageView.setAdjustViewBounds(mImageView.getAdjustViewBounds());
    //    Blurry.with(mContext).capture(view).into(mImageView);

        mImageView.setImageResource(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;

     /*   imageview = (ImageView) itemView.findViewById(R.id.imageView);

        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap blurred = blurRenderScript(bitmap, radiusArr[position]);//second parametre is radius
        imageview.setImageBitmap(blurred);*/

    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}