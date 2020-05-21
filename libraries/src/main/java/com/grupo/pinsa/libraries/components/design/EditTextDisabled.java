package com.grupo.pinsa.libraries.components.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
// import android.support.annotation.Nullable;
import androidx.annotation.StyleableRes;
// import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grupo.pinsa.libraries.R;
import com.grupo.pinsa.libraries.common.Conversion;

/**
 * Created by Juan J. Palomera on 09/11/2018
 */

public class EditTextDisabled extends LinearLayout {
    private TextView hintComponent;
    private TextView textComponent;
    private LinearLayout editTextDisabled;

    @StyleableRes
    private final int index0 = 0;
    private final int index1 = 1;
    private final int index2 = 2;

    public EditTextDisabled(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.grupopinsa_edit_text_disabled, this);

        int[] sets = {
                R.attr.EditTextDisabled_Hint
                , R.attr.EditTextDisabled_Text
                , R.attr.EditTextDisabled_Background
                , R.attr.EditTextDisabled_Text_Gravity
                , R.attr.EditTextDisabled_Hint_Gravity
        };
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);

        CharSequence hintCharSequence = typedArray.getText(index0) == null ? "" : typedArray.getText(index0);
        CharSequence textCharSequence = typedArray.getText(index1) == null ? "" : typedArray.getText(index1);

        hintComponent = findViewById(R.id.id_grupopinsa_edit_text_disabled_hint);
        textComponent = findViewById(R.id.id_grupopinsa_edit_text_disabled_text);
        editTextDisabled = findViewById(R.id.id_grupopinsa_edit_text_disabled);

        try {
            setHintValue(hintCharSequence);
            setTextValue(textCharSequence);
            setHintGravity(Gravity.START);
            setBackground(R.drawable.style_brd_bottom_lightgray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CharSequence getHintValue() {
        return hintComponent.getText();
    }

    public Integer getHintGravity() { return hintComponent.getGravity(); }

    public CharSequence getTextValue() {
        return textComponent.getText();
    }

    public Drawable getBackground() {
        return editTextDisabled.getBackground();
    }

    public void setBackground(Integer background) {
        editTextDisabled.setBackground(Conversion.resourceToDrawable(background));
    }

    public void setHintValue(CharSequence value) {
        hintComponent.setText(value);
    }

    public void setHintGravity(Integer gravity) {
        hintComponent.setGravity(gravity);
    }

    public void setTextValue(CharSequence value) {
        textComponent.setText(value);
    }

    public void setTextGravity(Integer gravity) {
        textComponent.setGravity(gravity);
    }
}
