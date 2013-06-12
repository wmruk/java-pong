package logic;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Pong extends JPanel implements Runnable {
    // Pozycja piłki (X, Y) i gracza (1 [X,Y], 2 [X,Y])

    private int PozycjaPilkaX = 100;
    private int PozycjaPilkaY = 125;
    private int Gracz1X = 10;
    private int Gracz1Y = 100;
    private int Gracz2X = 380;
    private int Gracz2Y = 100;
    private String wygral;
    public static Option set = new Option();
    Thread watek;
    int przesunieciePrawo = 5; // przesunięcie w prawo
    int przesuniecieLewo = -5; // --||-- w lewo
    int przesuniecieGora = 5; // --||-- w góre
    int przesuniecieDol = -5; // --||-- w dół
    int wysokoscPilki, szerokoscPilki; // rozmiary piłki
    // wyniki
    int punktyGracz1 = -1, punktyGracz2 = 0;
    boolean gracz1RuchGora, gracz1RuchDol, gracz2RuchGora, gracz2RuchDol;
    boolean graStart, koniecGry;

    public Pong() {
        graStart = true;
        watek = new Thread(this);
        watek.start();
    }

    // rysuje pilke i graczy
    @Override
    public void paintComponent(Graphics gc) {
        setOpaque(false);
        super.paintComponent(gc);



        // piłha
        gc.setColor(set.getKolorPilka());
        gc.fillOval(PozycjaPilkaX, PozycjaPilkaY, 10, 10);

        // gracze
        gc.setColor(set.getKolorG1());
        gc.fillRect(Gracz1X, Gracz1Y, 10, 45);
        gc.setColor(set.getKolorG2());
        gc.fillRect(Gracz2X, Gracz2Y, 10, 45);

        //wyniki
        gc.setColor(Color.BLACK);
        gc.drawString(set.getGracz1() + ": " + punktyGracz1, 25, 10);
        gc.drawString(set.getGracz2() + ": " + punktyGracz2, 150, 10);

        if (koniecGry) {
            gc.drawString("Koniec gry!", 150, 125);
            gc.drawString("Wygrał gracz: " + wygral , 150, 145);
        }
    }

    // pozycja pilki
    public void rysujPilke(int nx, int ny) {
        PozycjaPilkaX = nx;
        PozycjaPilkaY = ny;
        this.wysokoscPilki = this.getWidth();
        this.szerokoscPilki = this.getHeight();
        repaint();
    }

    // sterowanie klawiatura - wcisnij przycisk
    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            // gracz pierwszy
            case KeyEvent.VK_W:
                gracz1RuchGora = true;
                break;
            case KeyEvent.VK_S:
                gracz1RuchDol = true;
                break;

            // gracz 2
            case KeyEvent.VK_UP:
                gracz2RuchGora = true;
                break;
            case KeyEvent.VK_DOWN:
                gracz2RuchDol = true;
                break;
        }
    }

    //  sterowanie klawiatura - puść przycisk
    public void keyReleased(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            // gracz 1
            case KeyEvent.VK_W:
                gracz1RuchGora = false;
                break;
            case KeyEvent.VK_S:
                gracz1RuchDol = false;
                break;

            // gracz 2
            case KeyEvent.VK_UP:
                gracz2RuchGora = false;
                break;
            case KeyEvent.VK_DOWN:
                gracz2RuchDol = false;
                break;
        }
    }

    // gracz 1 poruszanie
    public void ruchGracz1() {
        if (gracz1RuchGora == true && Gracz1Y >= 6) {
            Gracz1Y += przesuniecieDol;
        }
        if (gracz1RuchDol == true && Gracz1Y <= (this.getHeight() - 50)) {
            Gracz1Y += przesuniecieGora;
        }
        rysujGracz1(Gracz1X, Gracz1Y);
    }

    // gracz 2 poruszanie
    public void ruchGracz2() {
        if (gracz2RuchGora == true && Gracz2Y >= 6) {
            Gracz2Y += przesuniecieDol;
        }
        if (gracz2RuchDol == true && Gracz2Y <= (this.getHeight() - 50)) {
            Gracz2Y += przesuniecieGora;
        }
        rysujGracz2(Gracz2X, Gracz2Y);
    }

    // pozycja gracza 1
    public void rysujGracz1(int x, int y) {
        this.Gracz1X = x;
        this.Gracz1Y = y;
        repaint();
    }

    // pozycja gracza 2
    public void rysujGracz2(int x, int y) {
        this.Gracz2X = x;
        this.Gracz2Y = y;
        repaint();
    }

    public void run() {
        boolean prawoLewo = false;
        boolean goraDol = false;

        while (true) {
            if (graStart) {
                // ruch piłka prawo - lewo
                if (prawoLewo) {
                    // prawo
                    PozycjaPilkaX += przesunieciePrawo;
                    if (PozycjaPilkaX >= (wysokoscPilki - 10)) {
                        prawoLewo = false;
                    }
                } else {
                    // lewo
                    PozycjaPilkaX += przesuniecieLewo;
                    if (PozycjaPilkaX <= 0) {
                        prawoLewo = true;
                    }
                }
                // ruch piłki góra - dół
                if (goraDol) {
                    // góra
                    PozycjaPilkaY += przesuniecieGora;
                    if (PozycjaPilkaY >= (szerokoscPilki - 10)) {
                        goraDol = false;
                    }
                } else {
                    // dół
                    PozycjaPilkaY += przesuniecieDol;
                    if (PozycjaPilkaY <= 0) {
                        goraDol = true;
                    }
                }

                rysujPilke(PozycjaPilkaX, PozycjaPilkaY);
                // szybkosc
                try {
                    Thread.sleep(set.getPoziom());
                } catch (InterruptedException ex) {
                }

                // poruszaj graczem 1
                ruchGracz1();

                // poruszaj graczem 2
                ruchGracz2();

                // gracz 1 punkty++
                if (PozycjaPilkaX >= (wysokoscPilki - 10)) {
                    punktyGracz1++;
                    this.rysujGracz1(10, 100);
                    this.rysujGracz2(380, 100);
                    this.rysujPilke(380, 125);
                    //graStart = false;
                }
                // gracz 2 punkty++
                if (PozycjaPilkaX == 0) {
                    punktyGracz2++;
                    this.rysujGracz1(10, 100);
                    this.rysujGracz2(380, 100);
                    this.rysujPilke(20, 125);
                    //graStart = false;
                }
                //koniec gry
                if (punktyGracz1 == set.getPunkty() || punktyGracz2 == set.getPunkty()) {
                    if (punktyGracz1 == set.getPunkty()) {
                        wygral = set.getGracz1();
                    } else if (punktyGracz2 == set.getPunkty()) {
                        wygral = set.getGracz2();
                    }
                    graStart = false;
                    koniecGry = true;
                }
                // pilka odbita gracz 1
                if (PozycjaPilkaX == Gracz1X + 10 && PozycjaPilkaY >= Gracz1Y && PozycjaPilkaY <= (Gracz1Y + 45)) {
                    prawoLewo = true;
                }
                // pilka odbita gracz 2
                if (PozycjaPilkaX == (Gracz2X - 10) && PozycjaPilkaY >= Gracz2Y && PozycjaPilkaY <= (Gracz2Y + 45)) {
                    prawoLewo = false;
                }
            }
        }

    }
}
