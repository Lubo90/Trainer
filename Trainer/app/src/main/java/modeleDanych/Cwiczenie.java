package modeleDanych;

import android.graphics.drawable.Drawable;

/**
 * Created by Lubo on 03.06.14.
 */
public class Cwiczenie {
    int mCzasTrwania;
    String mNazwa;
    Drawable mGrafika;
    String mOpis;
    int mLiczbaPorzadkowa;

    public Cwiczenie setLiczbaPorzadkowa(int liczbaPorzadkowa) {
        mLiczbaPorzadkowa = liczbaPorzadkowa;
        return this;
    }

    public Cwiczenie setCzasTrwania(int czasTrwania) {
        mCzasTrwania = czasTrwania;
        return this;
    }

    public Cwiczenie setNazwa(String nazwa) {
        mNazwa = nazwa;
        return this;
    }

    public Cwiczenie setGrafika(Drawable grafika) {
        mGrafika = grafika;
        return this;
    }

    public Cwiczenie setOpis(String opis) {
        mOpis = opis;
        return this;
    }
}
