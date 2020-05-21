package com.grupo.pinsa.libraries.components.design;

import android.content.Context;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 09/11/2018
 */

@BindingMethods({
        @BindingMethod(type = HeaderCellText.class, attribute = "app:onIconClickListener", method = "setOnIconClickListener")
        , @BindingMethod(type = HeaderCellText.class, attribute = "app:iconVisible", method = "setIconVisibility")
        , @BindingMethod(type = HeaderCellText.class, attribute = "app:text", method = "setTextValue")
})
public class HeaderCellText extends LinearLayout {
    private TextView textCellHeader;
    private ImageView iconCellHeader;

    public HeaderCellText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.grupopinsa_header_cell_text, this);

        textCellHeader = findViewById(R.id.id_grupopinsa_header_cell_text);
        iconCellHeader = findViewById(R.id.id_grupopinsa_header_cell_icon);
    }

    public CharSequence getTextValue() {
        return textCellHeader.getText();
    }

    public void setTextValue(CharSequence value) {
        textCellHeader.setText(value);
    }

    public boolean isIconShowing() { return iconCellHeader.isShown(); }

    public void setIconVisibility(boolean visible){
        if (visible)
            iconCellHeader.setVisibility(View.VISIBLE);
        else
            iconCellHeader.setVisibility(View.GONE);
    }

    public void setOnIconClickListener(OnClickListener listener) {
        iconCellHeader.setOnClickListener(listener);
    }
}
