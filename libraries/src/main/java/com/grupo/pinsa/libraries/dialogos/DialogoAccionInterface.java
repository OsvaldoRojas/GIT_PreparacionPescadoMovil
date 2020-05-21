package com.grupo.pinsa.libraries.dialogos;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

interface DialogoAccionInterface {
    void onPositive();
    void onPositive(String value);
    void onNegative();
    void onDate(String date);
}
