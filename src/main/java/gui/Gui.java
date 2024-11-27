package gui;

import gui.uu.Inv;
import gui.uu.Pl;

public class Gui {

    private Inv inv;

    public Gui(Inv inv) {
        loadContent();
    }

    public Gui() {
        // Génère pas l'inventaire
    }

    public void setInv(Inv inv) {
        this.inv = inv;
    }

    public void loadContent() {

    }

    public void open(Pl pl) {
        if(inv == null) {
            loadContent();
        }

        pl.openInv(inv);
    }

}
