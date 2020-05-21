package com.grupo.pinsa.libraries.dialogos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

class DialogoCompat {
    public static View.OnClickListener wrap(Dialog.OnClickListener listener) {
        return new DialogoCompat.DialogoOnClickListenerAdapter(listener);
    }

    static class DialogoOnClickListenerAdapter implements View.OnClickListener {
        private Dialog.OnClickListener adapter;

        DialogoOnClickListenerAdapter(DialogInterface.OnClickListener adapter) {
            this.adapter = adapter;
        }

        public void onClick(DialogInterface dialogInterface, int which) {
            if (adapter != null)
                adapter.onClick(dialogInterface, which);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
