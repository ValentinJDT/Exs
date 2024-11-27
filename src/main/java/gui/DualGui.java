package gui;

import gui.uu.Pl;

public class DualGui {

    private Gui top;
    private Gui bottom;

    public DualGui(Gui top, Gui bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public Gui getTop() {
        return top;
    }

    public Gui getBottom() {
        return bottom;
    }

    public void open(Pl pl) {
        top.open(pl);
        bottom.setInv(pl.getInv());
        bottom.loadContent();
    }
}
