package com.hguarnieri.fzea;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hguarnieri.fzea.R;
import com.hguarnieri.fzea.utils.Functions;

import java.util.Date;


public class MainActivity extends MenuDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, new MainPlaceholderFragment(), R.layout.activity_main);

        getSupportActionBar().setTitle("Início");
        getSupportActionBar().hide();


    }

    public static class MainPlaceholderFragment extends Fragment {

        public MainPlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }


        public void onActivityCreated(Bundle savedInstanceState) {

            ((LinearLayout) getView().findViewById(R.id.inicio_cardapios)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), CardapioActivity.class));

                }
            });

            ((LinearLayout) getView().findViewById(R.id.inicio_horarios)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                startActivity(new Intent(getActivity(), LaranjinhaActivity.class));

                }
            });

            ((TextView) getActivity().findViewById(R.id.fragment_main_txt_saida)).setText("Saída do Prédio Central - " + Functions.nextTime(0));
            ((TextView) getActivity().findViewById(R.id.fragment_main_txt_portao)).setText("Portão de Acesso - " + Functions.nextTime(1));
            ((TextView) getActivity().findViewById(R.id.fragment_main_txt_chegada)).setText("Chegada ao Prédio Central - " + Functions.nextTime(2));

            ((TextView) getActivity().findViewById(R.id.txt_data)).setText(Functions.DataPorExtenso(new Date()));

            super.onActivityCreated(savedInstanceState);
        }

    }



}