package com.example.jo.obligatorisk2.DataModell;

/**
 * Created by Jo on 17.04.2017.
 *
 * ~ Generert programatisk fra JSON.
 * Istedenfor å hente kategorier hver gang har vi en statisk tabell over mulige verdier.
 */

public enum Kategori {
    HAGEUTSTYR,
    HOBBYMALING,
    KERAMIKK,
    KONFEKT_OG_MARSIPAN,
    KURVER_OG_FLETTING,
    TEKSTIL_SøM_OG_STRIKKING,
    FISKE,
    JAKT,
    MODELLBYGGING,
    BøKER,
    LEKER,
    HAGEMøBLER,
    DUKKER_OG_NISSER,
    BUSKER,
    BLOMSTERFRø,
    BLOMSTERLøKER,
    DEKORASJONER,
    GRøNNSAKSFRø,
    TRESLøYD,
    HOBBYPAKKER,
    GJøDSEL;

    public String displayName()
    {
        return beksrivelse[katNr-1];
    }

    public int kategori()
    {
        return katNr;
    }

    public static String s(Kategori k)
    {
        return k.displayName();
    }
/// boilerplate
    static final String[] beksrivelse =
                    {"Hageutstyr",
                    "Hobbymaling",
                    "Keramikk",
                    "Konfekt og marsipan",
                    "Kurver og fletting",
                    "Tekstil, søm og strikking",
                    "Fiske",
                    "Jakt",
                    "Modellbygging",
                    "Bøker",
                    "Leker",
                    "Hagemøbler",
                    "Dukker og nisser",
                    "Busker",
                    "Blomsterfrø",
                    "Blomsterløker",
                    "Dekorasjoner",
                    "Grønnsaksfrø",
                    "Tresløyd",
                    "Hobbypakker",
                    "Gjødsel"};
    int katNr;

    static {
        int kat = 1;
        Kategori[] values = Kategori.values();
        for(Kategori k : values) {
            k.katNr = kat;
            ++kat;
        }
    }
}
