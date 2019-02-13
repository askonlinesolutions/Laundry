package com.laundry.CustomTextRegular;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextRegulars extends AppCompatTextView {

    public CustomTextRegulars(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextRegulars(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextRegulars(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.ttf");
        setTypeface(tf, 1);

    }

}


