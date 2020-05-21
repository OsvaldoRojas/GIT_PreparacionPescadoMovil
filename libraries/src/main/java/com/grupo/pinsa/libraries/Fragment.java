package com.grupo.pinsa.libraries;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.grupo.pinsa.libraries.components.fragment.FragmentCallback;
import com.grupo.pinsa.libraries.dialogos.Dialogo;

/**
 * Created by Juan J. Palomera on 25/10/2018
 */

public class Fragment extends androidx.fragment.app.Fragment {
    public FragmentCallback fragmentCallback;
    public Activity activity;
    public Fragment fragment;

    public Fragment setActvity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public Fragment setFragmentCallback(FragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragment = this;

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        Dialogo.hideDialogoProgreso();
        super.onStart();
    }
}
