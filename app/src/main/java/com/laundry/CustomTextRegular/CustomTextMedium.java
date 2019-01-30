package com.laundry.CustomTextRegular;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomTextMedium extends AppCompatTextView {

        public CustomTextMedium(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        public CustomTextMedium(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public CustomTextMedium(Context context) {
            super(context);
            init();
        }

        private void init() {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Medium.ttf");
            setTypeface(tf ,1);
        }
    }


