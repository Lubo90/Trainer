package serwisy;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.FileNotFoundException;
import java.io.IOException;

import modeleDanych.Cwiczenie;
import modeleDanych.Zestaw;
import wyjatki.MalformedFileException;

/**
 * Created by Lubo on 04.06.14.
 */
public class TrainingService {
    FileReadingService frs;

    public TrainingService(Context c) {
        frs = new FileReadingService(c);
    }

    public String[] getTrainings() {
        return frs.getFileList();
    }

    public Zestaw getTrainingItems(String trainingName) throws IOException, MalformedFileException {
        String trainingData = frs.readFile(trainingName);
        String[] exercises = trainingData.split("\r?\n|\r");

        Zestaw zestawCwiczen = new Zestaw().setNazwa(trainingName);

        for(String s : exercises) {
            zestawCwiczen.addCwiczenie(getCwiczenieItem(s));
        }

        return zestawCwiczen;
    }

    private Cwiczenie getCwiczenieItem(String exerciseData) throws FileNotFoundException, MalformedFileException {
        String[] splittedData = exerciseData.split(";");

        if(splittedData.length < 5)
            throw new MalformedFileException("Plik treningu posiada niewłaściwą strukturę.");

        int liczbaPorzadkowa = Integer.valueOf(splittedData[0]);
        String nazwaCwiczenia = splittedData[1];
        int iloscSekund = Integer.valueOf(splittedData[2]);
        String opis = splittedData[3];

        String linkDoObrazka = splittedData[4];
        Drawable grafika = frs.loadImage(linkDoObrazka);

        return new Cwiczenie()
                .setLiczbaPorzadkowa(liczbaPorzadkowa)
                .setCzasTrwania(iloscSekund)
                .setNazwa(nazwaCwiczenia)
                .setOpis(opis)
                .setGrafika(grafika);
    }
}
