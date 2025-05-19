import java.util.ArrayList;
import java.util.List;

interface Display {
    void update(int temperatuur);
}

interface Weerbron {
    void voegDisplayToe(Display d);
    void verwijderDisplay(Display d);
    void notifyDisplays();
}

class Weerstation implements Weerbron {
    private List<Display> displays = new ArrayList<>();
    private int temperatuur;

    public void setTemperatuur(int temp) {
        this.temperatuur = temp;
        notifyDisplays();
    }

    public int getTemperatuur() {
        return temperatuur;
    }

    @Override
    public void voegDisplayToe(Display d) {
        displays.add(d);
    }

    @Override
    public void verwijderDisplay(Display d) {
        displays.remove(d);
    }

    @Override
    public void notifyDisplays() {
        for (Display d : displays) {
            d.update(temperatuur);
        }
    }
}

class TekstDisplay implements Display {
    @Override
    public void update(int temperatuur) {
        System.out.println("TekstDisplay: De temperatuur is nu " + temperatuur + " graden.");
    }
}

class GrafiekDisplay implements Display {
    @Override
    public void update(int temperatuur) {
        System.out.print("GrafiekDisplay: ");
        for (int i = 0; i < temperatuur; i += 2) {
            System.out.print("*");
        }
        System.out.println(" (" + temperatuur + "°C)");
    }
}

class AlarmDisplay implements Display {
    @Override
    public void update(int temperatuur) {
        if (temperatuur > 30) {
            System.out.println("ALARM! De temperatuur is boven de 30 graden: " + temperatuur + "°C");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Weerstation station = new Weerstation();

        station.voegDisplayToe(new TekstDisplay());
        station.voegDisplayToe(new GrafiekDisplay());
        station.voegDisplayToe(new AlarmDisplay());

        station.setTemperatuur(25);
        System.out.println();
        station.setTemperatuur(32);
    }
}
