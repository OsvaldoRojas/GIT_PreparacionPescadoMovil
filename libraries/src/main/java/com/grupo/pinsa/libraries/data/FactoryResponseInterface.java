package com.grupo.pinsa.libraries.data;

/**
 * Created by Juan J. Palomera on 25/09/2018
 */

public interface FactoryResponseInterface {
    void onSuccess(String json);
    void onNotify(String message);
    void onWarning(String message);
    void onFailure(String message);
}
