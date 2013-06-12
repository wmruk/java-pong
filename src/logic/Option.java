/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Color;

/**
 *
 * @author Wojtek
 */
public class Option {
    private String gracz1;
    private String gracz2;
    private Color kolorG1 = Color.red;
    private Color kolorG2 = Color.blue;
    private Color kolorPilka = Color.black;
    private int punkty = 10;
    private int poziom = 50;

    public int getPoziom() {
        return poziom;
    }

    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    public Color getKolorG1() {
        return kolorG1;
    }

    public void setKolorG1(Color kolorG1) {
        this.kolorG1 = kolorG1;
    }

    public Color getKolorG2() {
        return kolorG2;
    }

    public void setKolorG2(Color kolorG2) {
        this.kolorG2 = kolorG2;
    }

    public Color getKolorPilka() {
        return kolorPilka;
    }

    public void setKolorPilka(Color kolorPilka) {
        this.kolorPilka = kolorPilka;
    }

    public String getGracz1() {
        return gracz1;
    }

    public void setGracz1(String gracz1) {
        this.gracz1 = gracz1;
    }

    public String getGracz2() {
        return gracz2;
    }

    public void setGracz2(String gracz2) {
        this.gracz2 = gracz2;
    }
    
    
}
