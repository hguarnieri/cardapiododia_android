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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MenuFragment extends Fragment {

    // Stores which day of the week it represents
    int day_of_week;
    boolean isLunch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        day_of_week = getArguments().getInt("day_of_week");
        isLunch = getArguments().getBoolean("isLunch");

        // Get references for the TextViews
        TextView dayTextView = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_dia);
        TextView mainOptionTextView = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_principal);
        TextView secondOptionTextView = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_opcao);
        TextView sidePlateTextView = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_guarnicao);
        TextView saladTextView = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_salada);
        TextView desertTextView = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_sobremesa);
        TextView juiceTextView = (TextView) getView().findViewById(R.id.inside_fragment_cardapio_suco);

        if (MenusService.menus.size() > 1) {
            Menu menu = MenusService.menus.get(day_of_week + (isLunch ? 0 : 5));

            // Set text on the TextViews
            mainOptionTextView.setText(getText(menu.getPrincipal()));
            secondOptionTextView.setText(getText(menu.getOpcao()));
            sidePlateTextView.setText(getText(menu.getGuarnicao()));
            saladTextView.setText(getText(menu.getSalada()));
            desertTextView.setText(getText(menu.getSobremesa()));
            juiceTextView.setText(getText(menu.getSuco()));
        }

        // Set the day of week on the TextView date
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY + day_of_week);
        dayTextView.setText(new SimpleDateFormat("EEEE").format(calendar.getTime()));

    }

    // Returns text for the TextView
    private String getText(String value) {
        if (value.equals("-")) {
            return getResources().getString(R.string.not_defined);
        } else {
            return value;
        }
    }

}