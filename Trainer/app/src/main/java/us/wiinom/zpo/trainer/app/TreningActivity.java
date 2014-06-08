package us.wiinom.zpo.trainer.app;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import modeleDanych.Cwiczenie;
import modeleDanych.Zestaw;
import serwisy.TrainingService;
import wyjatki.MalformedFileException;

/**
 * Created by Lubo on 02.06.14.
 */
public class TreningActivity extends Activity {
    TrainingService trainingService;
    Zestaw zestawCwiczen;
    Timer timerCwiczenieSecs = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.trening);

        String trainingName = String.valueOf(getIntent().getSerializableExtra("SelectedTrainingFile"));
        trainingService = new TrainingService(this.getApplicationContext());

        TextView tvTrening = (TextView) findViewById(R.id.tvTrening);
        tvTrening.setText(trainingName);

        try {
            zestawCwiczen = trainingService.getTrainingItems(trainingName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedFileException e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Niewłaściwie sformatowany plik treningu.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }


        TimerTask timerTask;
        timerCwiczenieSecs.scheduleAtFixedRate(timerTask = new TimerTask() {
            @Override
            public void run() {
                if(zestawCwiczen.getAktualneCwiczenie().getCzasTrwaniaAndZmniejsz() == 0)
                    if(!zestawCwiczen.nastepneCwiczenie()) {
                        this.cancel();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Koniec treningu, gratulacje!", Toast.LENGTH_LONG).show();
                            }
                        });
                        return;
                    }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadCwiczenieData(zestawCwiczen.getAktualneCwiczenie());
                    }
                });
            }
        }, 0, 1000);
    }

    private void loadCwiczenieData(Cwiczenie cwiczenie) {
        TextView tvCwiczenie = (TextView) findViewById(R.id.tvNazwaCwiczenia);
        TextView tvCzas = (TextView) findViewById(R.id.tvCzas);
        ImageView ivObrazek = (ImageView) findViewById(R.id.ivObrazek);

        tvCwiczenie.setText(cwiczenie.getNazwa());
        tvCzas.setText(String.valueOf(cwiczenie.getCzasTrwania()) + " sek.");
        ivObrazek.setImageDrawable(cwiczenie.getGrafika());
    }

    private void btnMenuOnClickEventHandler(View v) {
        timerCwiczenieSecs.cancel();
        finish();
    }
}
