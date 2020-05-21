package com.grupo.pinsa.libraries.dialogos;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.google.android.material.textfield.TextInputLayout;
import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

class DialogoInput extends DialogoBase<DialogoInput> {
    private final EditText inputText;
    private final TextInputLayout inputLayoutText;
    private final TextView positiveButton;
    private final TextView negativeButton;

    public DialogoInput(Context context) {
        super(context);
    }

    {
        inputText = findView(R.id.id_grupopinsa_dialogo_input);
        inputLayoutText = findView(R.id.id_grupopinsa_dialogo_input_layout);
        positiveButton = findView(R.id.id_grupopinsa_dialogo_positive_button);
        negativeButton = findView(R.id.id_grupopinsa_dialogo_negative_button);
    }

    /**
     * Positive Button
     */
    public DialogoInput setPositiveButton(@StringRes int text, View.OnClickListener listener) {
        return setPositiveButton(string(text), listener);
    }

    public DialogoInput setPositiveButton(String text, @Nullable View.OnClickListener listener) {
        positiveButton.setVisibility(View.VISIBLE);
        positiveButton.setText(text);
        positiveButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public DialogoInput setPositiveButtonText(@StringRes int text) {
        return setPositiveButton(text, null);
    }

    public DialogoInput setPositiveButtonText(String text) {
        return setPositiveButton(text, null);
    }

    public DialogoInput setPositiveButtonColorRes(@ColorRes int color) {
        return setPositiveButtonColor(color(color));
    }

    public DialogoInput setPositiveButtonColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        return this;
    }

    /**
     * Negative Button
     */
    public DialogoInput setNegativeButton(@StringRes int text, View.OnClickListener listener) {
        return setNegativeButton(string(text), listener);
    }

    public DialogoInput setNegativeButton(String text, @Nullable View.OnClickListener listener) {
        negativeButton.setVisibility(View.VISIBLE);
        negativeButton.setText(text);
        negativeButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public DialogoInput setNegativeButtonText(@StringRes int text) {
        return setPositiveButton(text, null);
    }

    public DialogoInput setNegativeButtonText(String text) {
        return setPositiveButton(text, null);
    }

    public DialogoInput setNegativeButtonColorRes(@ColorRes int color) {
        return setNegativeButtonColor(color(color));
    }

    public DialogoInput setNegativeButtonColor(@ColorInt int color) {
        negativeButton.setTextColor(color);
        return this;
    }

    /**
     * General
     */
    public DialogoInput setHint(@StringRes int text) {
        return setHint(string(text));
    }

    public DialogoInput setHint(String text) {
        inputLayoutText.setHint(text);
        return this;
    }

    public DialogoInput setInputMethod(int inputMethod) {
        inputText.setRawInputType(inputMethod);
        return this;
    }

    public DialogoInput setButtonsColorRes(@ColorRes int color) {
        return setButtonsColor(color(color));
    }

    public DialogoInput setButtonsColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        negativeButton.setTextColor(color);
        return this;
    }

    public String getInputValue() {
        return inputText.getText().toString();
    }

    public DialogoInput setInputValue(@StringRes int inputValue) {
        this.inputText.setText(inputValue);
        return this;
    }

    public DialogoInput setInputValue(String inputValue) {
        this.inputText.setText(inputValue);
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialogo_input;
    }
}
