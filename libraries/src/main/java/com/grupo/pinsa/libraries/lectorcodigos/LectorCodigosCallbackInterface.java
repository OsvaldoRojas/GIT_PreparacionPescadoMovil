package com.grupo.pinsa.libraries.lectorcodigos;

import android.os.Bundle;

/**
 * Created by Juan J. Palomera on 10/03/2019
 */

public interface LectorCodigosCallbackInterface {
    void Leer(LectorCodigosFragment fragment, String lectura);
    void Leer(LectorCodigosFragment fragment, Bundle lectura);
    void Cerrar();
}
