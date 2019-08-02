package com.example.simulador_pescado.Atemperado;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.simulador_pescado.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Atemperado_Plan.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Atemperado_Plan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Atemperado_Plan extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View vista;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView tina1;
    private ImageView tina2;
    private ImageView tina3;
    private ImageView tina4;
    private ImageView tina5;
    private ImageView tina6;
    private ImageView tina7;
    private ImageView tina8;
    private ImageView tina9;
    private ImageView tina10;
    private ImageView tina11;
    private ImageView tina12;
    private ImageView tina13;
    private ImageView tina14;
    private ImageView tina15;
    private ImageView tina16;
    private ImageView tina17;
    private ImageView tina18;
    private ImageView tina19;
    private ImageView tina20;
    private ImageView tina21;
    private ImageView tina22;
    private ImageView tina23;
    private ImageView tina24;
    private ImageView tina25;
    private ImageView tina26;
    private ImageView tina27;
    private ImageView tina28;
    private ImageView tina29;
    private ImageView tina30;
    private ImageView tina31;
    private ImageView tina32;
    private ImageView tina33;
    private ImageView tina34;
    private ImageView tina35;
    private ImageView tina36;
    private ImageView tina37;
    private ImageView tina38;
    private ImageView tina39;
    private ImageView tina40;
    private ImageView tina41;
    private ImageView tina42;
    private ImageView tina43;
    private ImageView tina44;
    private ImageView tina45;
    private ImageView tina46;
    private ImageView tina47;
    private ImageView tina48;
    private ImageView tina49;
    private ImageView tina50;
    private ImageView tina51;
    private ImageView tina52;
    private ImageView tina53;
    private ImageView tina54;
    private ImageView tina55;
    private ImageView tina56;
    private ImageView tina57;
    private ImageView tina58;
    private ImageView tina59;
    private ImageView tina60;
    private ImageView tina61;
    private ImageView tina62;
    private ImageView tina63;
    private ImageView tina64;
    private ImageView tina65;
    private ImageView tina66;
    private ImageView tina67;
    private ImageView tina68;
    private ImageView tina69;
    private ImageView tina70;
    private ImageView tina71;
    private ImageView tina72;
    private ImageView tina73;
    private ImageView tina74;
    private ImageView tina75;
    private ImageView tina76;
    private ImageView tina77;
    private ImageView tina78;
    private ImageView tina79;
    private ImageView tina80;
    private ImageView tina81;
    private ImageView tina82;
    private ImageView tina83;
    private ImageView tina84;
    private ImageView tina85;
    private ImageView tina86;
    private ImageView tina87;
    private ImageView tina88;

    private ImageView montacargas;

    private OnFragmentInteractionListener mListener;

    public Fragment_Atemperado_Plan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Atemperado_Plan.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Atemperado_Plan newInstance(String param1, String param2) {
        Fragment_Atemperado_Plan fragment = new Fragment_Atemperado_Plan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.vista = inflater.inflate(R.layout.fragment_fragment__atemperado__plan, container, false);

        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
