package com.grupo.pinsa.libraries.impresion.etiqueta.zebra;

/**
 * Created by Juan J. Palomera on 23/03/2019
 */

public interface ImpresionEtiquetaCallbackInterface {
    void onSuccess();
    void onFailure(String mensajeError);
}
