package com.grupo.pinsa.libraries.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grupo.pinsa.libraries.common.Constantes;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Juan J. Palomera on 07/11/2018
 */

public class SpinnerAdapter extends ArrayAdapter {
    private final int resourceId;
    private final ArrayList<String> lista;

    public SpinnerAdapter(@NonNull Context context, int resource, ArrayList<String> strings) {
        this(context, resource, strings, Constantes.DEFAULT_SPINNER_OPTION);
    }

    public SpinnerAdapter(@NonNull Context context, int resource, ArrayList<String> strings, String opcionDefault) {
        //noinspection unchecked
        super(context, resource, strings);

        strings.add(0, opcionDefault);

        resourceId = resource;
        lista = strings;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TextView textView;

        if (convertView == null) {
            textView = (TextView) Objects.requireNonNull(inflater).inflate(resourceId, parent, false);
        } else
            textView = (TextView) convertView;

        if (textView.getText() != null)
            textView.setText(lista.get(position));

        return textView;
    }

    @Override
    public View getDropDownView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TextView textView;

        if (convertView == null) {
            textView = (TextView) Objects.requireNonNull(inflater).inflate(resourceId, parent, false);
        } else
            textView = (TextView) convertView;

        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(lista.get(position));
                textView.setSingleLine(false);
            }
        });

        return textView;
    }
}
