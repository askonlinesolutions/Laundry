package com.laundry.CustomTextRegular;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class NormalTextView extends TextView {
    public NormalTextView(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Medium.ttf");
        this.setTypeface(face);
    }

    public NormalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Medium.ttf");
        this.setTypeface(face);
    }

    public NormalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Medium.ttf");
        this.setTypeface(face);
    }
}
