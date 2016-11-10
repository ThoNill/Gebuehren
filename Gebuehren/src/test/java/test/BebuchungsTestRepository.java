package test;

import java.util.ArrayList;
import java.util.List;

import bebucht.BebuchteEntität;
import bebucht.BuchungsAuftragMitEntität;
import bebucht.BuchungsRepository;
import buchung.Konto;

public class BebuchungsTestRepository implements BuchungsRepository{
    private static final Konto konto = new TestKonto(1,"Konto");
    private static final Konto gegenKonto = new TestKonto(1,"Gegenkonto");
    private List<BuchungsAuftragMitEntität> buchungen = new ArrayList<>();

    public BebuchungsTestRepository() {
    }

    @Override
    public Konto getSachKonto(BebuchteEntität entität) {
        return konto;
    }

    @Override
    public Konto getGegenKonto(BebuchteEntität entität) {
        return gegenKonto;
    }

    @Override
    public void insertBuchung(BuchungsAuftragMitEntität auftrag) {
        buchungen.add(auftrag);
    }

    public List<BuchungsAuftragMitEntität> getBuchungen() {
        return buchungen;
    }

}
