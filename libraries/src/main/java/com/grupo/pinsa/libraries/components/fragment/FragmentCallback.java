package com.grupo.pinsa.libraries.components.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 12/04/2019
 */

public abstract class FragmentCallback implements FragmentCallbackInterface {
    @Override
    public void onCallback(Fragment fragment, String string) {
        fragment.getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @Override
    public void onCallback(Fragment fragment, Bundle bundle) {
        fragment.getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @Override
    public void onClose(Fragment fragment) {
        fragment.getFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
