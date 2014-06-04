package modeleDanych;

import android.graphics.drawable.Drawable;

/**
 * Created by Lubo on 03.06.14.
 */
public class Cwiczenie {
    int mCzasTrwania;
    String mNazwa;
    Drawable mGrafika;

    public Cwiczenie(int czasTrwania, String nazwa, Drawable grafika) {
        mCzasTrwania = czasTrwania;
        mNazwa = nazwa;
        mGrafika = grafika;
    }
}
