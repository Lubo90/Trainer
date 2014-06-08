package modeleDanych;

import java.util.ArrayList;

/**
 * Created by Lubo on 04.06.14.
 */
public class Zestaw {
    String mNazwaZestawu;
    ArrayList<Cwiczenie> mListaCwiczen;
    int mAktualneCwiczenie = 0;

    public Zestaw setNazwa(String nazwaZestawu) {
        mNazwaZestawu = nazwaZestawu;
        return this;
    }

    public Zestaw setListaCwiczen(ArrayList<Cwiczenie> listaCwiczen) {
        mListaCwiczen = listaCwiczen;
        return this;
    }

    public Zestaw addCwiczenie(Cwiczenie cwiczenie) {
        if(mListaCwiczen == null)
            mListaCwiczen = new ArrayList<Cwiczenie>();

        mListaCwiczen.add(cwiczenie);
        return this;
    }

    public Cwiczenie getAktualneCwiczenie() {
        return mListaCwiczen.get(mAktualneCwiczenie);
    }

    public boolean nastepneCwiczenie() {
        return ++mAktualneCwiczenie < mListaCwiczen.size();
    }
}
