package us.wiinom.zpo.trainer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import modeleDanych.Cwiczenie;
import modeleDanych.Zestaw;
import serwisy.TrainingService;
import wyjatki.MalformedFileException;

/**
 * Created by Lubo on 02.06.14.
 */
public class TreningActivity extends Activity {
    TrainingService trainingService = new TrainingService(this.getApplicationContext());
    Zestaw zestawCwiczen;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.trening);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(String.valueOf(getIntent().getSerializableExtra("SelectedTrainingFile")));

        try {
            zestawCwiczen = trainingService.getTrainingItems(getIntent().getSerializableExtra("SelectedTrainingFile").toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedFileException e) {
            e.printStackTrace();
        }
    }

    private void btnMenuOnClickEventHandler(View v) {
        finish();
    }
}
