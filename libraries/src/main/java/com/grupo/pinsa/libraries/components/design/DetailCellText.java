package com.grupo.pinsa.libraries.components.design;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
// import android.support.annotation.Nullable;
import androidx.annotation.StyleableRes;
// import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 10/11/2018
 */

public class DetailCellText extends LinearLayout {
    private LinearLayout mContentView;
    private TextView textDetailCell;

    @StyleableRes
    private final int index0 = 0;

    public DetailCellText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.grupopinsa_detail_cell_text, this);

        int[] sets = { R.attr.DetailCellText_Color };
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);

        int color = typedArray.getColor(index0, 0);

        mContentView = findViewById(R.id.id_grupopinsa_detail_cell_text_content);
        textDetailCell = findViewById(R.id.id_grupopinsa_detail_cell_text_text);

        if (color != 0)
            setTextColor(color);
    }

    public CharSequence getTextValue() {
        return textDetailCell.getText();
    }

    public void setTextValue(CharSequence value) {
        textDetailCell.setText(value);
    }

    private void setTextColor(int color) {
        textDetailCell.setTextColor(color);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (mContentView == null)
            super.addView(child, index, params);
        else
            mContentView.addView(child, index, params);
    }
}
