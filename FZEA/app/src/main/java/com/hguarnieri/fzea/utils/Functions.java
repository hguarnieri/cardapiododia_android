package com.hguarnieri.fzea.utils;

import com.hguarnieri.fzea.models.Cardapio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Henrique on 11/03/2015.
 */
public class Functions {
    private static final String[][] horarios = {{"06:25", "07:00", "07:35", "08:30", "09:45", "11:05", "11:50", "12:25", "13:35",
            "14:45", "16:00", "17:05", "17:35", "19:30", "20:15", "21:00", "21:45", "23:00"},

            {"06:40", "07:15", "07:50", "08:45", "10:00", "11:20", "12:05", "12:40", "13:50",
                    "15:00", "16:15", "17:20", "17:50", "19:45", "20:30", "21:15", "22:00", "23:15"},

            {"06:55", "07:30", "08:05", "09:00", "10:15", "11:35", "12:20", "12:55", "14:05",
                    "15:15", "16:30", "17:35", "18:05", "20:00", "20:45", "21:30", "22:15", "23:30"},

            {"05:50", "06:25", "07:35", "11:05", "12:25", "13:35", "15:00", "16:00", "17:05",
                    "17:45"},

            {"06:05", "06:40", "07:50", "11:20", "12:40", "13:50", "15:15", "16:15", "17:20",
                    "18:00"},

            {"06:20", "06:55", "08:05", "11:35", "12:55", "14:05", "15:30", "16:30", "17:35",
                    "18:15"}};

    public static String NomeDoMes(int i) {
        String mes[] = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
        return (mes[i - 1]);
    }

    public static String DiaDaSemana(int i) {
        String diasem[] = {"Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"};
        return (diasem[i - 1]);
    }

    public static String DataPorExtenso(java.util.Date dt) {
        int d = dt.getDate();
        int m = dt.getMonth() + 1;
        int a = dt.getYear() + 1900;
        Calendar data = new GregorianCalendar(a, m - 1, d);
        int ds = data.get(Calendar.DAY_OF_WEEK);
        return (DiaDaSemana(ds) + ", " + d + " de " + NomeDoMes(m) + " de " + a);
    }

    public static int minutosAtual() {
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        int hora = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int minutos = calendar.get(Calendar.MINUTE);

        return hora * 60 + minutos;
    }

    public static String nextTime(int t) {
        int atual = minutosAtual();

        for(String s : horarios[t]) {
            int min = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));

            if (atual <= min) {
                return s;
            }
        }

        return null;
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
                Thread.sleep(7000); // or Thread.sleep(con.getConnectTimeout())
            } catch (InterruptedException e) {

            }
            con.disconnect();
            //("Timer thread forcing to quit connection");
        }
    }
}
