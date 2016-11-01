package test;

import beans.Konto;

public class TestKonto implements Konto {
    private int nummer;
    private String name;

    TestKonto(int nummer, String name) {
        super();
        this.nummer = nummer;
        this.name = name;
    }

    public int getNummer() {
        return nummer;
    }


    public String getName() {
        return name;
    }

 

}
