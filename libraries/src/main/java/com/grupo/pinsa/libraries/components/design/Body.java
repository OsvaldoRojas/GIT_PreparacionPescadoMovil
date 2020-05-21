package com.grupo.pinsa.libraries.components.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.libraries.R;
import com.grupo.pinsa.libraries.dialogos.Dialogo;

/**
 * Created by Juan J. Palomera on 09/11/2018
 */

public class Body extends LinearLayout {
    private RecyclerView recyclerView;

    public Body(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.grupopinsa_body, this);

        recyclerView = findViewById(R.id.id_grupopinsa_body_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        try {
            recyclerView.swapAdapter(adapter, true);
        } catch (Exception e) {
            Dialogo.hideDialogoProgreso();
            Dialogo.showDialogoError(recyclerView.getContext(), e.getMessage(), Dialogo.ACEPTAR, true);
        }
    }

    public void clear() {
        recyclerView.swapAdapter(null, true);
    }

    public void addHideDialogoProgresoEvent() {
        try {
            recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Dialogo.hideDialogoProgreso();

                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } catch (Exception e) {
            Dialogo.hideDialogoProgreso();
            Dialogo.showDialogoError(recyclerView.getContext(), e.getMessage(), Dialogo.ACEPTAR, true);
        }
    }
}