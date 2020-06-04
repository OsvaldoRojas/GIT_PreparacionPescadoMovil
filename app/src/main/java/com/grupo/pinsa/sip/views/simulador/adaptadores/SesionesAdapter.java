package com.grupo.pinsa.sip.views.simulador.adaptadores;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SesionesAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> listaFragments= new ArrayList<>();
    private final List<String> listatitulos=new ArrayList<>();

    public void addfragments(Fragment fragment,String titulo){
        listatitulos.add(titulo);
        listaFragments.add(fragment);
    }
    public SesionesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listatitulos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }
}
