package com.laundry.CustomTextRegular;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

public class Regular extends AppCompatTextView {
    public Regular(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular.ttf");
        this.setTypeface(face);
    }

    public Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular.ttf");
        this.setTypeface(face);
    }

    public Regular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular.ttf");
        this.setTypeface(face);
    }
}
