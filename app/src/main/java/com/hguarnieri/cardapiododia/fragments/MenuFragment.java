package com.hguarnieri.cardapiododia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hguarnieri.cardapiododia.R;
import com.hguarnieri.cardapiododia.models.Menu;
import com.hguarnieri.cardapiododia.services.MenusService;

/**
 * Created by Henrique on 20/05/2016.
 */
public class MenuFragment extends Fragment {

    int dia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cardapio, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dia = getArguments().getInt("dia");

        Menu menu = MenusService.menus.get(dia);

        TextView txtDia = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_dia);

        TextView txtPrincipal = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_principal);
        TextView txtOpcao = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_opcao);
        TextView txtGuarnicao = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_guarnicao);
        TextView txtSalada = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_salada);
        TextView txtSobremesa = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_sobremesa);
        TextView txtSuco = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_suco);

        txtPrincipal.setText(getText(menu.getPrincipal()));
        txtOpcao.setText(getText(menu.getOpcao()));
        txtGuarnicao.setText(getText(menu.getGuarnicao()));
        txtSalada.setText(getText(menu.getSalada()));
        txtSobremesa.setText(getText(menu.getSobremesa()));
        txtSuco.setText(getText(menu.getSuco()));

        //Functions.DiaDaSemana(semana + 2 - tipo * 4)
        txtDia.setText("");
    }

    private String getText(String value) {
        if (value.equals("-")) {
            return "Não definido";
        } else {
            return value;
        }
    }

}