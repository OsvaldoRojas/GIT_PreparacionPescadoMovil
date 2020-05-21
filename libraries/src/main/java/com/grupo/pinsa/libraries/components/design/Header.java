package com.grupo.pinsa.libraries.components.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 09/11/2018
 */

public class Header extends LinearLayout {
    private LinearLayout mContentView;

    public Header(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.grupopinsa_header, this);

        mContentView = findViewById(R.id.id_grupopinsa_header_content);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (mContentView == null)
            super.addView(child, index, params);
        else
            mContentView.addView(child, index, params);
    }
}
