package com.hguarnieri.cardapiododia.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.hguarnieri.cardapiododia.models.Cardapio;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by henrique on 24/02/14.
 */

/* Classe que gerencia a Thread de obtenção dos cardapios */
public class DownloadCardapio extends AsyncTask<Void, String, List<Cardapio>> {
    private Context context;

    private final ProgressDialog dialog;

    public DownloadCardapio(Activity activity, Context context) {
        dialog = new ProgressDialog(activity);
        this.context = context;
    }

    /* Função principal a ser executada */
    @Override
    protected List<Cardapio> doInBackground(Void... params) {

        List<Cardapio> cardapios = Functions.getCardapios();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        int i = 0;
        for(Cardapio c : cardapios) {
            cal.add(Calendar.DAY_OF_WEEK, i++);
            c.setData(sdf.format(cal.getTime()));

            if (i == 5) {
                i = 0;
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            }
        }
        return cardapios;
    }

    /* Função a ser executada antes da Thread */
    @Override
    protected void onPreExecute() {
        this.dialog.setTitle("Aguarde");
        this.dialog.setMessage("Atualizando cardápio..");
        this.dialog.show();
    }

    /* Função a ser executada após o fim da Thread */
    protected void onPostExecute(List<Cardapio> cardapios) {
        this.dialog.dismiss();
    }

    public void writeFile(String fileName, String value) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(value.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
