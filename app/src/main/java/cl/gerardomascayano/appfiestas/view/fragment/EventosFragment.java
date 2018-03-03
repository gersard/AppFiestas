package cl.gerardomascayano.appfiestas.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.gerardomascayano.appfiestas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventosFragment extends Fragment {


    public EventosFragment() {
        // Required empty public constructor
    }

    public static EventosFragment newInstance(){
        return new EventosFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eventos, container, false);
    }

}
