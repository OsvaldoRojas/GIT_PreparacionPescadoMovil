package com.grupo.pinsa.libraries.components.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * Created by Juan J. Palomera on 12/04/2019
 */

public interface FragmentCallbackInterface {
    void onCallback(Fragment fragment, String string);
    void onCallback(Fragment fragment, Bundle bundle);
    void onClose(Fragment fragment);
}
