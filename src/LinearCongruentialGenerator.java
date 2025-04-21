public class LinearCongruentialGenerator {
    private final long a = 1664525;
    private final long c = 1013904223;
    private long m = (long) Math.pow(2, 32);
    private long seed;

    public LinearCongruentialGenerator() { this.seed = generateSeed(); }

    private long generateSeed() {
        long time = System.currentTimeMillis();       // timp sistem ms
        long nano = System.nanoTime();                // precizie nanosecunde
        int hash = Runtime.getRuntime().hashCode();   // hash cu info de sistem
        String user = System.getProperty("user.name");

        // combinatie dubioasa
        return Math.abs((time ^ nano ^ hash ^ user.hashCode())) % m;
    }

    public int nextInt() { seed = (a * seed + c) % m; return (int) seed; }
}
