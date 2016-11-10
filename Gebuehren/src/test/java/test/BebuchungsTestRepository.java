package test;

import java.util.ArrayList;
import java.util.List;

import bebucht.BebuchteEntit�t;
import bebucht.BuchungsAuftragMitEntit�t;
import bebucht.BuchungsRepository;
import buchung.Konto;

public class BebuchungsTestRepository implements BuchungsRepository{
    private static final Konto konto = new TestKonto(1,"Konto");
    private static final Konto gegenKonto = new TestKonto(1,"Gegenkonto");
    private List<BuchungsAuftragMitEntit�t> buchungen = new ArrayList<>();

    public BebuchungsTestRepository() {
    }

    @Override
    public Konto getSachKonto(BebuchteEntit�t entit�t) {
        return konto;
    }

    @Override
    public Konto getGegenKonto(BebuchteEntit�t entit�t) {
        return gegenKonto;
    }

    @Override
    public void insertBuchung(BuchungsAuftragMitEntit�t auftrag) {
        buchungen.add(auftrag);
    }

    public List<BuchungsAuftragMitEntit�t> getBuchungen() {
        return buchungen;
    }

}
