package com.hguarnieri.fzea;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hguarnieri.fzea.R;
import com.hguarnieri.fzea.utils.Horario;
import com.hguarnieri.fzea.utils.HorarioAdapter;


public class LaranjinhaActivity extends MenuDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, new MainPlaceholderFragment(), R.layout.activity_main);

        getSupportActionBar().hide();
    }

    public static class MainPlaceholderFragment extends Fragment {

        SectionsPagerAdapter mSectionsPagerAdapter;
        ViewPager mViewPager;

        public MainPlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_laranjinha, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

            mViewPager = (ViewPager) getView().findViewById(R.id.pager); //fragment_laranjinha.xml
            mViewPager.setAdapter(mSectionsPagerAdapter);

        }
    }

    @SuppressLint("ValidFragment")
    public static class HorarioFragment extends Fragment {

        Horario horario;

        public HorarioFragment(Horario horario) {
            this.horario = horario;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.inside_fragment_laranjinha, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ListView lv = (ListView) getView().findViewById(R.id.inside_fragment_laranjinha_list_horarios);
            TextView txtTitulo = (TextView) getView().findViewById(R.id.inside_fragment_laranjinha_titulo);
            TextView txtPeriodo = (TextView) getView().findViewById(R.id.inside_fragment_laranjinha_periodo_atual);

            switch (horario) {
                case SAIDA_PREDIO_CENTRAL:
                    txtTitulo.setText("Saída do Prédio Central");
                    txtPeriodo.setText("Período Letivo");
                    break;
                case PORTAO_DE_ACESSO:
                    txtTitulo.setText("Portão de Acesso");
                    txtPeriodo.setText("Período Letivo");
                    break;
                case CHEGADA_PREDIO_CENTRAL:
                    txtTitulo.setText("Chegada ao Prédio Central");
                    txtPeriodo.setText("Período Letivo");
                    break;
                case FERIAS_SAIDA_PREDIO_CENTRAL:
                    txtTitulo.setText("Saída do Prédio Central");
                    txtPeriodo.setText("Férias");
                    break;
                case FERIAS_PORTAO_DE_ACESSO:
                    txtTitulo.setText("Portão de Acesso");
                    txtPeriodo.setText("Férias");
                    break;
                case FERIAS_CHEGADA_PREDIO_CENTRAL:
                    txtTitulo.setText("Chegada ao Prédio Central");
                    txtPeriodo.setText("Férias");
                    break;
            }

            HorarioAdapter adapter = new HorarioAdapter(getActivity(), horario);
            lv.setAdapter(adapter);
        }

    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        boolean ferias = false;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Fragment();

            if (! ferias) {
                switch (position) {
                    case 0:
                        fragment = new HorarioFragment(Horario.SAIDA_PREDIO_CENTRAL);
                        break;
                    case 1:
                        fragment = new HorarioFragment(Horario.PORTAO_DE_ACESSO);
                        break;
                    case 2:
                        fragment = new HorarioFragment(Horario.CHEGADA_PREDIO_CENTRAL);
                        break;
                }
            } else {
                switch (position) {
                    case 0:
                        fragment = new HorarioFragment(Horario.FERIAS_SAIDA_PREDIO_CENTRAL);
                        break;
                    case 1:
                        fragment = new HorarioFragment(Horario.FERIAS_PORTAO_DE_ACESSO);
                        break;
                    case 2:
                        fragment = new HorarioFragment(Horario.FERIAS_CHEGADA_PREDIO_CENTRAL);
                        break;
                }
            }


            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

}