package com.grupo.pinsa.libraries.dialogos;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

class DialogoInfo extends DialogoBase<DialogoInfo> {
    private final TextView confirmButton;

    public DialogoInfo(Context context) {
        super(context);
    }

    {
        confirmButton = findView(R.id.id_grupopinsa_dialogo_confirm_button);
        confirmButton.setOnClickListener(new ClickListenerDecorator(null, true));
    }

    public DialogoInfo setConfirmButtonText(@StringRes int text) {
        return setConfirmButtonText(string(text));
    }

    public DialogoInfo setConfirmButtonText(String text) {
        confirmButton.setText(text);
        return this;
    }

    public DialogoInfo setConfirmButtonColor(@ColorRes int colorRes) {
        confirmButton.setTextColor(color(colorRes));
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialogo_info;
    }
}
