package com.grupo.pinsa.libraries.lectorcodigos;

import android.os.Bundle;

/**
 * Created by Juan J. Palomera on 10/03/2019
 */

public abstract class LectorCodigosCallback implements LectorCodigosCallbackInterface{
    @Override
    public void Leer(LectorCodigosFragment fragment, String lectura) {

    }

    @Override
    public void Leer(LectorCodigosFragment fragment, Bundle lectura) {

    }

    @Override
    public void Cerrar() {

    }
}
