package com.hguarnieri.cardapiododia.utils;

import android.content.Context;

import com.hguarnieri.cardapiododia.R;
import com.hguarnieri.cardapiododia.models.Cardapio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Functions {

    public static String NomeDoMes(int i) {
        String mes[] = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
        return (mes[i - 1]);
    }

    public static String DiaDaSemana(int i) {
        String diasem[] = {"Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"};
        return (diasem[i - 1]);
    }

    public static String getDateAsString(Context context) {
        DateFormat df = new SimpleDateFormat(context.getString(R.string.date_format));

        Date today = Calendar.getInstance().getTime();

        return df.format(today);
    }

    public static int getTimeInMinutes() {
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        int hora = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int minutos = calendar.get(Calendar.MINUTE);

        return hora * 60 + minutos;
    }

    public static List<Cardapio> getCardapios() {
        List<Cardapio> cardapios = new ArrayList();

        String pagina = getHtml("http://www2.comp.ufscar.br/~henrique.guarnieri/cardapiov3/cardapio.txt");

        int i = 0;
        int t = 0;
        for (String s : pagina.split("!")) {
            if (!s.equals("****")) {
                switch (i) {
                    case 0:
                        cardapios.add(new Cardapio());
                        cardapios.get(t).setPrincipal(s);
                        i++;
                        break;
                    case 1:
                        cardapios.get(t).setOpcao(s);
                        i++;
                        break;
                    case 2:
                        cardapios.get(t).setGuarnicao(s);
                        i++;
                        break;
                    case 3:
                        cardapios.get(t).setSalada(s);
                        i++;
                        break;
                    case 4:
                        cardapios.get(t).setSobremesa(s);
                        i++;
                        break;
                    case 5:
                        cardapios.get(t).setSuco(s);
                        i++;
                        break;
                }
                if (i > 5) {
                    i = 0;
                    t++;
                }
            }
        }

        return cardapios;
    }

    public static String getHtml(String link) {
        URL url = null;
        StringBuffer s = new StringBuffer();
        try {
            url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(7000);
            con.setReadTimeout(7000);
            new Thread(new InterruptThread(con)).start();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "ISO-8859-1"));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                s.append(inputLine);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s.toString();

    }

    public static class InterruptThread implements Runnable {

        HttpURLConnection con;

        public InterruptThread(HttpURLConnection con) {
            this.con = con;
        }

        public void run() {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {

            }
            con.disconnect();
        }
    }
}
