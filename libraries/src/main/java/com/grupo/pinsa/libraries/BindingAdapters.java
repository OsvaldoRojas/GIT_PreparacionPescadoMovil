package com.grupo.pinsa.libraries;

import androidx.databinding.BindingAdapter;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.grupo.pinsa.libraries.adapters.SpinnerAdapter;
import com.grupo.pinsa.libraries.components.LifeCycleActivity;
import com.grupo.pinsa.libraries.components.design.DetailCellText;
import com.grupo.pinsa.libraries.components.design.EditTextDisabled;
import com.grupo.pinsa.libraries.components.design.HeaderCellText;
import com.grupo.pinsa.libraries.components.listeners.EditorActionListener;
import com.grupo.pinsa.libraries.components.listeners.FocusChangedListener;
import com.grupo.pinsa.libraries.components.listeners.OnScanListener;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public abstract class BindingAdapters {
    public static class GeneralBinding {
        @BindingAdapter({"onFocusChanged"})
        public static void setOnFocusChanged(View view, final FocusChangedListener focusChangedListener) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    focusChangedListener.onFocusChanged(v, hasFocus);
                }
            });
        }

        @BindingAdapter({"visible"})
        public static void setVisible(View view, boolean visible) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public static class EditTextBinding {
        @BindingAdapter({"onSelection"})
        public static void setOnSelection(EditText editText, LifeCycleActivity lifeCycleActivity) {
            if (editText.getId() == lifeCycleActivity.getViewId()) {
                if (lifeCycleActivity.getEditSelection() == 0)
                    editText.setSelection(editText.length());
                else
                    editText.setSelection(lifeCycleActivity.getEditSelection());
            }
        }

        @BindingAdapter({"onScan"})
        public static void setOnScan(final EditText editText, final OnScanListener onScanListener) {
            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == 66)
                        onScanListener.OnScan();

                    return false;
                }
            });
        }

        @BindingAdapter({"onEditorAction"})
        public static void setEditorAction(EditText editText, final EditorActionListener editorActionListener) {
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (actionId) {
                        case EditorInfo.IME_ACTION_SEARCH:
                            editorActionListener.onSearchListener();
                            return true;
                        case EditorInfo.IME_ACTION_DONE:
                            editorActionListener.onDoneListener();
                            return true;
                        default:
                            return false;
                    }
                }
            });
        }

        @BindingAdapter({"editTextDisabled_Text"})
        public static void setEditTextDisabled_Text(EditTextDisabled editTextDisabled, String value) {
            editTextDisabled.setTextValue(value);
        }

        @BindingAdapter({"editTextDisabled_Text_Gravity"})
        public static void setEditTextDisabled_Text_Gravity(EditTextDisabled editTextDisabled, Integer gravity) {
            editTextDisabled.setTextGravity(gravity);
        }

        @BindingAdapter({"editTextDisabled_Hint"})
        public static void setEditTextDisabled_Hint(EditTextDisabled editTextDisabled, String value) {
            editTextDisabled.setHintValue(value);
        }

        @BindingAdapter({"editTextDisabled_Hint_Gravity"})
        public static void setEditTextDisabled_Hint_Gravity(EditTextDisabled editTextDisabled, Integer gravity) {
            editTextDisabled.setHintGravity(gravity);
        }

        @BindingAdapter({"editTextDisabled_Background"})
        public static void setEditTextDisabled_Background(EditTextDisabled editTextDisabled, int background) {
            editTextDisabled.setBackground(background);
        }

        @BindingAdapter({"headerCellText_Text"})
        public static void setHeaderCellText_Text(HeaderCellText headerCellText, String value) {
            headerCellText.setTextValue(value);
        }

        @BindingAdapter({"detailCellText_Text"})
        public static void setDetailCellText_Text(DetailCellText detailCellText, String value) {
            detailCellText.setTextValue(value);
        }
    }

    public static class SpinnerBinding {
        @BindingAdapter({"entries"})
        public static void setEntries(final Spinner spinner, final ArrayList<String> lista) {
            ArrayAdapter<?> arrayAdapter = new SpinnerAdapter(spinner.getContext(), R.layout.grupopinsa_spinner, lista);
            arrayAdapter.setDropDownViewResource(R.layout.grupopinsa_spinner);
            spinner.setAdapter(arrayAdapter);
        }
    }
}