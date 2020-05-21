package com.grupo.pinsa.libraries.dialogos;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

class DialogoEstandar extends DialogoBase<DialogoEstandar> {
    private final TextView positiveButton;
    private final TextView negativeButton;
    private final TextView neutralButton;

    public DialogoEstandar(Context context) {
        super(context);
    }

    {
        positiveButton = findView(R.id.id_grupopinsa_dialogo_positive_button);
        negativeButton = findView(R.id.id_grupopinsa_dialogo_negative_button);
        neutralButton = findView(R.id.id_grupopinsa_dialogo_neutral_button);
    }

    /**
     * Positive Button
     */
    public DialogoEstandar setPositiveButton(@StringRes int text, View.OnClickListener listener) {
        return setPositiveButton(string(text), listener);
    }

    public DialogoEstandar setPositiveButton(String text, @Nullable View.OnClickListener listener) {
        positiveButton.setVisibility(View.VISIBLE);
        positiveButton.setText(text);
        positiveButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public DialogoEstandar setPositiveButtonText(@StringRes int text) {
        return setPositiveButton(text, null);
    }

    public DialogoEstandar setPositiveButtonText(String text) {
        return setPositiveButton(text, null);
    }

    public DialogoEstandar setPositiveButtonColorRes(@ColorRes int color) {
        return setPositiveButtonColor(color(color));
    }

    public DialogoEstandar setPositiveButtonColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        return this;
    }

    /**
     * Negative Button
     */
    public DialogoEstandar setNegativeButton(@StringRes int text, View.OnClickListener listener) {
        return setNegativeButton(string(text), listener);
    }

    public DialogoEstandar setNegativeButton(String text, @Nullable View.OnClickListener listener) {
        negativeButton.setVisibility(View.VISIBLE);
        negativeButton.setText(text);
        negativeButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public DialogoEstandar setNegativeButtonText(@StringRes int text) {
        return setNegativeButton(text, null);
    }

    public DialogoEstandar setNegativeButtonText(String text) {
        return setNegativeButton(text, null);
    }

    public DialogoEstandar setNegativeButtonColorRes(@ColorRes int color) {
        return setNegativeButtonColor(color(color));
    }

    public DialogoEstandar setNegativeButtonColor(@ColorInt int color) {
        negativeButton.setTextColor(color);
        return this;
    }

    /**
     * Neutral Button
     */
    public DialogoEstandar setNeutralButton(@StringRes int text, View.OnClickListener listener) {
        return setNeutralButton(string(text), new ClickListenerDecorator(listener, true));
    }

    public DialogoEstandar setNeutralButton(String text, @Nullable View.OnClickListener listener) {
        neutralButton.setVisibility(View.VISIBLE);
        neutralButton.setText(text);
        neutralButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public DialogoEstandar setNeutralButtonText(@StringRes int text) {
        return setNeutralButton(text, null);
    }

    public DialogoEstandar setNeutralButtonText(String text) {
        return setNeutralButton(text, null);
    }

    public DialogoEstandar setNeutralButtonColorRes(@ColorRes int color) {
        return setNeutralButtonColor(color(color));
    }

    public DialogoEstandar setNeutralButtonColor(@ColorInt int color) {
        neutralButton.setTextColor(color);
        return this;
    }

    /**
     * General
     */
    public DialogoEstandar setButtonsColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        negativeButton.setTextColor(color);
        neutralButton.setTextColor(color);
        return this;
    }

    public DialogoEstandar setButtonsColorRes(@ColorRes int colorRes) {
        return setButtonsColor(color(colorRes));
    }

    public DialogoEstandar setOnButtonClickListener(View.OnClickListener listener) {
        return setOnButtonClickListener(true, listener);
    }

    public DialogoEstandar setOnButtonClickListener(boolean closeOnClick, View.OnClickListener listener) {
        View.OnClickListener clickHandler = new ClickListenerDecorator(listener, closeOnClick);
        positiveButton.setOnClickListener(clickHandler);
        neutralButton.setOnClickListener(clickHandler);
        negativeButton.setOnClickListener(clickHandler);
        return this;
    }

    public DialogoEstandar createDialog(DialogoEstandar standardSIP) {
        this.show();
        return this;
    }

    @Override
    protected int getLayout() { return ButtonLayout.HORIZONTAL.layoutRes; }

    public enum ButtonLayout {
        HORIZONTAL(R.layout.dialogo_estandar);
        final @LayoutRes
        int layoutRes;
        ButtonLayout(@LayoutRes int layoutRes) { this.layoutRes = layoutRes; }
    }
}
