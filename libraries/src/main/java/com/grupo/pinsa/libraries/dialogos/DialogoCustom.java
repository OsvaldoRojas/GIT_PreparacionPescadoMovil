package com.grupo.pinsa.libraries.dialogos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

class DialogoCustom extends DialogoBase<DialogoCustom> {
    private View addedView;

    public DialogoCustom(Context context) {
        super(context);
    }

    public DialogoCustom setView(@LayoutRes int layout) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewGroup parent = findView(R.id.id_grupopinsa_dialogo_custom_view);
        addedView = inflater.inflate(layout, parent, true);
        return this;
    }

    public DialogoCustom setView(View customView) {
        ViewGroup container = findView(R.id.id_grupopinsa_dialogo_custom_view);
        container.addView(customView);
        addedView = customView;
        return this;
    }

    public DialogoCustom setListener(int viewId, View.OnClickListener listener) {
        return setListener(viewId, false, listener);
    }

    public DialogoCustom setListener(int viewId, boolean dismissOnClick, View.OnClickListener listener) {
        if (addedView == null) {
            throw new IllegalStateException(string(R.string.grupopinsa_dialogo_error_view_no_definida));
        }
        View.OnClickListener clickListener = new ClickListenerDecorator(listener, dismissOnClick);
        findView(viewId).setOnClickListener(clickListener);
        return this;
    }

    public DialogoCustom configureView(@NonNull ViewConfigurator<View> configurator) {
        if (addedView == null) {
            throw new IllegalStateException(string(R.string.grupopinsa_dialogo_error_view_no_definida));
        }
        configurator.configureView(addedView);
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialogo_custom;
    }
}
