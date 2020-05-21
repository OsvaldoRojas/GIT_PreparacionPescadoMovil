package com.grupo.pinsa.libraries.dialogos;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

@SuppressWarnings("unchecked")
abstract class DialogoBase<T extends DialogoBase> {
    private Dialog dialog;
    private View dialogView;

    private ImageView iconTitle;
    private TextView messageTitle;
    private TextView messageView;

    public DialogoBase(Context context) {
        this(context, 0);
    }

    public DialogoBase(Context context, int layoutRest) {
        if (layoutRest == 0)
            layoutRest = getLayout();

        init(new AlertDialog.Builder(context), layoutRest);
    }

    public void init(AlertDialog.Builder builder, @LayoutRes int layoutRes) {
        dialogView = LayoutInflater.from(builder.getContext()).inflate(layoutRes, null);
        dialog = builder.setView(dialogView).create();

        iconTitle = findView(R.id.id_grupopinsa_dialogo_icon);
        messageTitle = findView(R.id.id_grupopinsa_dialogo_title);
        messageView = findView(R.id.id_grupopinsa_dialogo_message);
    }

    @LayoutRes
    protected abstract int getLayout();

    /**
     * Message
     */
    public T setMessage(@StringRes int message) {
        return setMessage(string(message));
    }

    public T setMessage(CharSequence message) {
        messageView.setVisibility(View.VISIBLE);
        messageView.setText(message);
        return (T) this;
    }

    public CharSequence getMessage() {
        return messageView.getText();
    }

    public T setMessageColor(int color) {
        messageView.setTextColor(color);
        return (T) this;
    }

    public int getMessageColor() {
        return messageView.getCurrentTextColor();
    }

    public T setMessageGravity(int gravity) {
        messageView.setGravity(gravity);
        return (T) this;
    }

    public int getMessageGravity() {
        return messageView.getGravity();
    }

    /**
     * Title
     */
    public T setTitle(@StringRes int title) { return setTitle(string(title)); }

    public T setTitle(CharSequence title) {
        messageTitle.setVisibility(View.VISIBLE);
        messageTitle.setText(title);
        return (T) this;
    }

    public CharSequence getTitle() {
        return messageTitle.getText();
    }

    public T setTitleColor(int color) {
        messageTitle.setTextColor(color);
        return (T) this;
    }

    public int getTitleColor() {
        return messageTitle.getCurrentTextColor();
    }

    public T setTitleGravity(int gravity) {
        messageTitle.setGravity(gravity);
        return (T) this;
    }

    public int getTitleGravity() {
        return messageTitle.getGravity();
    }

    /**
     * Header
     */
    public T setIcon(@DrawableRes int iconRes) {
        iconTitle.setVisibility(View.VISIBLE);
        iconTitle.setImageResource(iconRes);
        return (T) this;
    }

    public T setTopColor(@ColorInt int topColor) {
        findView(R.id.id_grupopinsa_dialogo_header).setBackgroundColor(topColor);
        return (T) this;
    }

    public T setTopColorRes(@ColorRes int topColorRes) {
        return setTopColor(color(topColorRes));
    }

    public T setTopColorDrawable(Drawable drawable) {
        findView(R.id.id_grupopinsa_dialogo_header).setBackground(drawable);
        return (T) this;
    }

    /**
     * General
     */
    public T setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return (T) this;
    }

    public Dialog create() {
        return dialog;
    }

    public Dialog show() {
        dialog.show();
        return dialog;
    }

    boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    protected class ClickListenerDecorator implements View.OnClickListener {

        private View.OnClickListener clickListener;
        private final boolean closeOnClick;

        protected ClickListenerDecorator(View.OnClickListener clickListener, boolean closeOnClick) {
            this.clickListener = clickListener;
            this.closeOnClick = closeOnClick;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                if (clickListener instanceof DialogoCompat.DialogoOnClickListenerAdapter) {
                    DialogoCompat.DialogoOnClickListenerAdapter listener = (DialogoCompat.DialogoOnClickListenerAdapter) clickListener;
                    listener.onClick(dialog, v.getId());
                } else {
                    clickListener.onClick(v);
                }
            }
            if (closeOnClick) {
                dismiss();
            }
        }
    }

    /**
     * Utilerias
     */
    protected <ViewClass extends View> ViewClass findView(int id) {
        return (ViewClass) dialogView.findViewById(id);
    }

    protected String string(@StringRes int message) {
        return dialogView.getContext().getString(message);
    }

    protected int color(@ColorRes int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }

    protected Context getContext() {
        return dialogView.getContext();
    }
}
