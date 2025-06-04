import java.time.LocalDate;

public class LinearCongruentialGenerator {
    private final long a = 1664525;
    private long c = 1013904223;
    private long m = (long) Math.pow(2, 32);
    private long seed;
    private int id;
    private LocalDate started_at;
    public LinearCongruentialGenerator() { this.seed = generateSeed(); this.started_at = LocalDate.now(); }

    public long getSeed() { return this.seed;}
    public long getModulus() { return this.m; }
    public long getIncrement() { return this.c; }
    public int getId() { return this.id; }
    public LocalDate getStartedAt() {return this.started_at;}

    private long generateSeed() {
        long time = System.currentTimeMillis();       // timp sistem ms
        long nano = System.nanoTime();                // precizie nanosecunde
        int hash = Runtime.getRuntime().hashCode();   // hash cu info de sistem
        String user = System.getProperty("user.name");

        // combinatie dubioasa
        return Math.abs((time ^ nano ^ hash ^ user.hashCode())) % m;
    }

    public int nextInt() { seed = (a * seed + c) % m; return (int) seed; }

    public void setIncrement(long increment) { this.c = increment; }
    public void setSeed(long seed) { this.seed = seed; }
    public void setModulus(long modulus) {this.m = modulus;}
    public void setId(int id) { this.id = id; }
    public void setStartedAt(LocalDate startedAt) { this.started_at = startedAt; }
}
