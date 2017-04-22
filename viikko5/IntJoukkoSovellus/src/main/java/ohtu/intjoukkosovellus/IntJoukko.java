
package ohtu.intjoukkosovellus;

public class IntJoukko {
    // aloitustalukon koko
    public final static int KAPASITEETTI = 5;   
    // uuden luotavan taulukon oletuskasvatuskoko
    public final static int OLETUSKASVATUS = 5;  
    // kasvatuskoko = (uuden tauluko koko - vanhan taulukon koko)
    private int kasvatuskoko;
    private int[] lukujono;
    private int alkioidenLukumaara;

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLukumaara = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLukumaara = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) throws IndexOutOfBoundsException {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Taulukon kapasiteetti ei voi olla pienempi kuin nolla");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Taulukon kasvatuskoko ei voi olla pienempi kuin nolla");
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLukumaara = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (alkioidenLukumaara == 0) {
            lukujono[0] = luku;
            alkioidenLukumaara++;
            return true;
        } else if (!kuuluu(luku)) {
            lukujono[alkioidenLukumaara] = luku;
            alkioidenLukumaara++;
            if (alkioidenLukumaara % lukujono.length == 0) {
                lukujono = kasvataTaulukko(lukujono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        boolean lukuOnOlemassa = false;
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                lukuOnOlemassa = true;
            }
        }
        return lukuOnOlemassa;
    }

    public boolean poista(int luku) {
        int indeksi = -1;
        int apu;
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                indeksi = i;
                lukujono[i] = 0;
                break;
            }
        }
        if (indeksi != -1) {
            for (int j = indeksi; j < alkioidenLukumaara - 1; j++) {
                apu = lukujono[j];
                lukujono[j] = lukujono[j + 1];
                lukujono[j + 1] = apu;
            }
            alkioidenLukumaara--;
            return true;
        }
        return false;
    }

    private int[] kasvataTaulukko(int[] taulukko) {
        int[] uusiTaulukko = new int[taulukko.length + kasvatuskoko];
        for (int i = 0; i < taulukko.length; i++) {
            uusiTaulukko[i] = taulukko[i];
        }
        return uusiTaulukko;
    }

    public int mahtavuus() {
        return alkioidenLukumaara;
    }


    @Override
    public String toString() {
        if (alkioidenLukumaara == 0) {
            return "{}";
        } else if (alkioidenLukumaara == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            String tuloste = "{";
            for (int i = 0; i < alkioidenLukumaara - 1; i++) {
                tuloste += lukujono[i];
                tuloste += ", ";
            }
            tuloste += lukujono[alkioidenLukumaara - 1];
            tuloste += "}";
            return tuloste;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLukumaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }
        
}