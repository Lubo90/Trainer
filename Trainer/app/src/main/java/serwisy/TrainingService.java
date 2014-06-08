package serwisy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

import modeleDanych.Cwiczenie;
import modeleDanych.Zestaw;
import wyjatki.MalformedFileException;

/**
 * Created by Lubo on 04.06.14.
 */
public class TrainingService {
    FileReadingService mFrs;
    Context mContext;

    public TrainingService(Context c) {
        mFrs = new FileReadingService(c);
    }

    public String[] getTrainings() {
        try {
            return mFrs.getFileList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Dane treningów nie zostały znalezione", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Zestaw getTrainingItems(String trainingName) throws IOException, MalformedFileException {
        String trainingData = mFrs.readFile(trainingName);
        String[] exercises = trainingData.split("\r?\n|\r");

        Zestaw zestawCwiczen = new Zestaw().setNazwa(trainingName);

        Cwiczenie[] posortowaneCwiczenia = new Cwiczenie[exercises.length];

        for(int i = 0; i < exercises.length; i++) {
            Cwiczenie cwiczenie = getCwiczenieItem(exercises[i], trainingName);

            if(posortowaneCwiczenia[i] != null)
                throw new MalformedFileException("Plik zawiera niepoprawnie skonfigurowaną kolejność ćwiczeń");

            posortowaneCwiczenia[i] = cwiczenie;
        }

        for(Cwiczenie c : posortowaneCwiczenia) {
            zestawCwiczen.addCwiczenie(c);
        }

        return zestawCwiczen;
    }

    private Cwiczenie getCwiczenieItem(String exerciseData, String trainingName) throws FileNotFoundException, MalformedFileException {
        String[] splittedData = exerciseData.split(";");

        if(splittedData.length != 4)
            throw new MalformedFileException("Plik treningu posiada niewłaściwą strukturę.");

        try {
            int liczbaPorzadkowa = Integer.valueOf(splittedData[0]);
            String nazwaCwiczenia = splittedData[1];
            int iloscSekund = Integer.valueOf(splittedData[2]);

            String linkDoObrazka = trainingName + "/" + splittedData[3];
            Drawable grafika = mFrs.loadImage(linkDoObrazka);

            return new Cwiczenie()
                    .setLiczbaPorzadkowa(liczbaPorzadkowa)
                    .setCzasTrwania(iloscSekund)
                    .setNazwa(nazwaCwiczenia)
                    .setGrafika(grafika);
        } catch (Exception ex) {
            throw new MalformedFileException("Plik treningu posiada niewłaściwą strukturę", ex);
        }
    }
}
