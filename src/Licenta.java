import java.time.LocalDate;

public abstract class Licenta implements Comparable<Licenta>{
    protected String cod;
    protected int id;
    protected String sistem;
    protected LocalDate dataGenerare;

    public Licenta(String sistem) { this.sistem = sistem; this.cod = " "; this.dataGenerare = LocalDate.now(); }

    public abstract String genereazaCod();
    public abstract boolean valideazaCod(String cod);

    public String getCod() {return cod;}
    public void setCod(String cod) { this.cod = cod; }
    public String getSistem() {return sistem;}

    @Override
    public String toString() { return "ID: " + id + " | " + sistem + " | " + cod + " | " + dataGenerare; }

    @Override
    public int compareTo(Licenta other) { return this.dataGenerare.compareTo(other.dataGenerare); }

    public LocalDate getDataGenerare() { return dataGenerare; }
    public void setDataGenerare(LocalDate dataGenerare) { this.dataGenerare = dataGenerare; }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
}


