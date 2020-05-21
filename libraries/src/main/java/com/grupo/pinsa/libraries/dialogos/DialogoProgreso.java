package com.grupo.pinsa.libraries.dialogos;

import android.content.Context;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

class DialogoProgreso extends DialogoBase<DialogoProgreso> {
    public DialogoProgreso(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialogo_progreso;
    }
}
