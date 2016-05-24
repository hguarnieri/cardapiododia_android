package com.hguarnieri.cardapiododia.services;

import android.os.AsyncTask;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hguarnieri.cardapiododia.models.Menu;
import com.hguarnieri.cardapiododia.utils.Functions;

public class MenusService {

    public static List<Menu> menus = null;

    public static class DownloadMenu extends AsyncTask<Void, String, List<Menu>> {

        @Override
        protected List<Menu> doInBackground(Void... params) {
            return getMenus();
        }

        public List<Menu> getMenus() {
            List<Menu> menus = new ArrayList();

            String pagina = Functions.getHtml("http://www2.comp.ufscar.br/~henrique.guarnieri/cardapiov3/cardapio.txt");

            int i = 0;
            int t = 0;
            for (String s : pagina.split("!")) {
                if (!s.equals("****")) {
                    switch (i) {
                        case 0:
                            menus.add(new Menu());
                            menus.get(t).setPrincipal(s);
                            i++;
                            break;
                        case 1:
                            menus.get(t).setOpcao(s);
                            i++;
                            break;
                        case 2:
                            menus.get(t).setGuarnicao(s);
                            i++;
                            break;
                        case 3:
                            menus.get(t).setSalada(s);
                            i++;
                            break;
                        case 4:
                            menus.get(t).setSobremesa(s);
                            i++;
                            break;
                        case 5:
                            menus.get(t).setSuco(s);
                            i++;
                            break;
                    }
                    if (i > 5) {
                        i = 0;
                        t++;
                    }
                }
            }

            return menus;
        }

    }
}
