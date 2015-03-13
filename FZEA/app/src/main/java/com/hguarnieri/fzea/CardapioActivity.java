package com.hguarnieri.fzea;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hguarnieri.fzea.R;
import com.hguarnieri.fzea.models.Cardapio;
import com.hguarnieri.fzea.utils.DownloadCardapio;
import com.hguarnieri.fzea.utils.Functions;

import java.util.List;


public class CardapioActivity extends MenuDrawerActivity {

    private static List<Cardapio> cardapios;
    private static int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, new MainPlaceholderFragment(), R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tipo = extras.getInt("TIPO");
        }

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
            View rootView = inflater.inflate(R.layout.fragment_cardapio, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            (new DownloadCardapio(getActivity(), getActivity().getApplicationContext()) {
                @Override
                protected void onPostExecute(List<Cardapio> c) {
                    super.onPostExecute(c);
                    cardapios = c;

                    mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

                    mViewPager = (ViewPager) getView().findViewById(R.id.pager); //fragment_laranjinha.xml
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                }
            }).execute();

            Button btnAlmoco = (Button) getView().findViewById(R.id.fragment_cardapio_btnAlmoco);
            Button btnJantar = (Button) getView().findViewById(R.id.fragment_cardapio_btnJantar);

            btnAlmoco.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CardapioActivity.class);
                    intent.putExtra("TIPO", 0);
                    startActivity(intent);
                    getActivity().finish();
                }
            }));

            btnJantar.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CardapioActivity.class);
                    intent.putExtra("TIPO", 1);

                    startActivity(intent);
                    getActivity().finish();
                }
            }));
        }
    }

    @SuppressLint("ValidFragment")
    public static class CardapioFragment extends Fragment {

        int semana;


        public CardapioFragment(int semana) {
            this.semana = semana + tipo * 4;
        }


        public CardapioFragment() {

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.inside_fragment_cardapio, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            TextView txtDia = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_dia);

            TextView txtPrincipal = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_principal);
            TextView txtOpcao = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_opcao);
            TextView txtGuarnicao = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_guarnicao);
            TextView txtSalada = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_salada);
            TextView txtSobremesa = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_sobremesa);
            TextView txtSuco = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_suco);

            if (cardapios.get(semana).getPrincipal().equals("-")) {
                ((LinearLayout) getView().findViewById(R.id.inside_fragment_cardapio_ll_principal)).setVisibility(View.GONE);
            } else {
                txtPrincipal.setText(cardapios.get(semana).getPrincipal());
            }

            if (cardapios.get(semana).getOpcao().equals("-")) {
                ((LinearLayout) getView().findViewById(R.id.inside_fragment_cardapio_ll_opcao)).setVisibility(View.GONE);
            } else {
                txtOpcao.setText(cardapios.get(semana).getOpcao());
            }

            if (cardapios.get(semana).getGuarnicao().equals("-")) {
                ((LinearLayout) getView().findViewById(R.id.inside_fragment_cardapio_ll_guarnicao)).setVisibility(View.GONE);
            } else {
                txtGuarnicao.setText(cardapios.get(semana).getGuarnicao());
            }

            if (cardapios.get(semana).getSalada().equals("-")) {
                ((LinearLayout) getView().findViewById(R.id.inside_fragment_cardapio_ll_salada)).setVisibility(View.GONE);
            } else {
                txtSalada.setText(cardapios.get(semana).getSalada());
            }

            if (cardapios.get(semana).getSobremesa().equals("-")) {
                ((LinearLayout) getView().findViewById(R.id.inside_fragment_cardapio_ll_sobremesa)).setVisibility(View.GONE);
            } else {
                txtSobremesa.setText(cardapios.get(semana).getSobremesa());
            }

            if (cardapios.get(semana).getSuco().equals("-")) {
                ((LinearLayout) getView().findViewById(R.id.inside_fragment_cardapio_ll_suco)).setVisibility(View.GONE);
            } else {
                txtSuco.setText(cardapios.get(semana).getSuco());
            }

            txtDia.setText(Functions.DiaDaSemana(semana + 2 - tipo * 4));

        }

    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        boolean ferias = false;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new CardapioFragment(position);

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

    }

}