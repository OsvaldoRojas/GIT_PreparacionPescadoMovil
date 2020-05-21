package com.grupo.pinsa.libraries.components;

import androidx.databinding.BaseObservable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.grupo.pinsa.libraries.components.listeners.FocusChangedListener;

/**
 * Created by Juan J. Palomera on 30/10/2018
 */

public abstract class LifeCycleActivity extends BaseObservable {
    private final String VIEW_ID_KEY = "VIEW_ID";
    private final String EDIT_TEXT_SELECTION_KEY = "EDIT_TEXT_SELECTION";

    private int viewId;
    private int editSelection;

    public LifeCycleActivity() {
    }

    /**
     * Constructores
     */
    public LifeCycleActivity(int viewId, int editSelection) {
        this.viewId = viewId;
        this.editSelection = editSelection;
    }

    /**
     * Getter & Setter
     */
    public LifeCycleActivity getLifeCycleActivity() {
        return this;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public int getEditSelection() {
        return editSelection;
    }

    public void setEditSelection(int editSelection) {
        this.editSelection = editSelection;
    }

    public void setEditSelection(View view) {
        if (view instanceof EditText)
            setEditSelection(((EditText) view).getSelectionEnd());
    }

    /**
     * Eventos
     */
    public void onClickLifeCycle(View view) {
        setViewId(view.getId());
    }

    public FocusChangedListener onFocusChangedLifeCycle() {
        return new FocusChangedListener() {
            @Override
            public void onFocusChanged(View view, boolean hasFocus) {
                setFocusLifeCycle(view);
            }
        };
    }

    public void setFocusLifeCycle(View view) {
        setViewId(view.getId());
        setEditSelection(0);
    }

    /**
     * Funciones
     */
    public void saveInstanceState(Bundle outState) {
        outState.putInt(VIEW_ID_KEY, viewId);
        outState.putInt(EDIT_TEXT_SELECTION_KEY, editSelection);
    }

    public void restoresInstanceState(Bundle saveInstanceState) {
        viewId = saveInstanceState.getInt(VIEW_ID_KEY);
        editSelection = saveInstanceState.getInt(EDIT_TEXT_SELECTION_KEY);
    }
}
