package com.example.data1700oblig3;

import lombok.Getter;

public class Billett {
    private int id;
    private String fValg;
    private String antall;
    private String fNavn;
    private String eNavn;
    private String tlfNr;
    private String epost;

    public Billett(){};

    public Billett (int id, String fValg, String antall, String fNavn, String eNavn, String tlfNr, String epost){
        this.id = id;
        this.fValg = fValg;
        this.antall = antall;
        this.fNavn = fNavn;
        this.eNavn = eNavn;
        this.tlfNr = tlfNr;
        this.epost = epost;
    };

    public Billett (String fValg, String antall, String fNavn, String eNavn, String tlfNr, String epost){
        this.fValg = fValg;
        this.antall = antall;
        this.fNavn = fNavn;
        this.eNavn = eNavn;
        this.tlfNr = tlfNr;
        this.epost = epost;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfValg() {
        return fValg;
    }

    public void setfValg(String fValg) {
        this.fValg = fValg;
    }

    public String getAntall() {
        return antall;
    }

    public void setAntall(String antall) {
        this.antall = antall;
    }

    public String getfNavn() {
        return fNavn;
    }

    public void setfNavn(String fNavn) {
        this.fNavn = fNavn;
    }

    public String geteNavn() {
        return eNavn;
    }

    public void seteNavn(String eNavn) {
        this.eNavn = eNavn;
    }

    public String getTlfNr() {
        return tlfNr;
    }

    public void setTlfNr(String tlfNr) {
        this.tlfNr = tlfNr;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    @Override
    public String toString(){
        return "Billett: " +
                "Filvalg: " + fValg + "\'" +
                "Antall billetter: " + "\'" +
                "Fornavn: " + fNavn + "\'" +
                "Etternavn: " + eNavn + "\'" +
                "Telefonnummer: " + tlfNr + "\'" +
                "Epost: " + epost + ". ";
    }
}
