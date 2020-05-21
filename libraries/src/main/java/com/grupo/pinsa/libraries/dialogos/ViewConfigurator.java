package com.grupo.pinsa.libraries.dialogos;

import android.view.View;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

interface ViewConfigurator<T extends View> {
    void configureView(T v);
}
