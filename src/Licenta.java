import java.time.LocalDate;

public abstract class Licenta implements Comparable<Licenta>{
    protected String cod;
    protected String sistem;
    protected LocalDate dataGenerare;

    public Licenta(String sistem) {
        this.sistem = sistem;
        this.cod = " ";
        this.dataGenerare = LocalDate.now();
    }

    public abstract String genereazaCod();
    public abstract boolean valideazaCod(String cod);

    public String getCod() {return cod;}
    public String getSistem() {return sistem;}

    @Override
    public String toString() { return sistem + " | " + dataGenerare; }

    @Override
    public int compareTo(Licenta other) { return this.dataGenerare.compareTo(other.dataGenerare); }
}


