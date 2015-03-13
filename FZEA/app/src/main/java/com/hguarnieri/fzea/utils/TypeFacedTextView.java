package com.hguarnieri.fzea.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hguarnieri.fzea.R;

/**
 * Created by H on 21/09/13.
 */
public class TypeFacedTextView extends TextView {

    public TypeFacedTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Typeface.createFromAsset doesn't work in the layout editor. Skipping ...
        if (isInEditMode())
        {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypeFacedTextView);
        String fontName = styledAttrs.getString(R.styleable.TypeFacedTextView_font);
        styledAttrs.recycle();

        if (fontName == null) {
            fontName = "Lato-Reg.ttf";
        }

        Typeface typeface = null;
        if (fontName.equals("lato_bold")) {
            typeface = Typeface.createFromAsset(context.getAssets(), "Lato-Bol.ttf");
        } else if (fontName.equals("lato_reg")) {
            typeface = Typeface.createFromAsset(context.getAssets(), "Lato-Reg.ttf");
        }  else if (fontName.equals("lato_light")) {
            typeface = Typeface.createFromAsset(context.getAssets(), "Lato-Lig.ttf");
        }

        setTypeface(typeface);

    }

}