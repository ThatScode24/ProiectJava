import java.time.LocalDate;
import java.time.LocalDateTime;

public class MersenneTwister {
    private static final int N = 624;
    private static final int M = 397;
    private static final int[] MAG01 = {0x0, 0x9908b0df};

    private int[] mt = new int[N]; // vector de stari
    private int index = N + 1;
    private int currentSeed;

    private int id;
    private LocalDate dataGenerare;

    // generam un seed pe stereoizi
    public MersenneTwister() {
        this(generateSuperSeed());
        this.dataGenerare = LocalDate.now();
    }

    public int getSeed(){ return currentSeed;}
    public LocalDate getDataGenerare() { return dataGenerare; }
    public int getId() { return id; }

    // seed manual
    public MersenneTwister(int seed) { init(seed); currentSeed = seed; }

    // creare seed complex cu date sistem
    private static int generateSuperSeed() {

        // date sistem
        long nano = System.nanoTime();
        long time = System.currentTimeMillis();
        long threadId = Thread.currentThread().getId();
        long mem = Runtime.getRuntime().freeMemory() + Runtime.getRuntime().totalMemory();

        // un fel de hash pentru a genera un seed

        long seed = nano ^ (time << 21) ^ (threadId << 13) ^ (mem << 7);
        seed ^= (seed >>> 33);
        seed *= 0xff51afd7ed558ccdL;
        seed ^= (seed >>> 33);
        seed *= 0xc4ceb9fe1a85ec53L;
        seed ^= (seed >>> 33);

        return (int) seed; // cast la int de 32 de biti
    }

    private void init(int seed) { mt[0] = seed; for (int i = 1; i < N; i++) mt[i] = (1812433253 * (mt[i - 1] ^ (mt[i - 1] >>> 30)) + i); }

    private void twist() {
        for (int i = 0; i < N; i++) {
            int y = (mt[i] & 0x80000000) | (mt[(i + 1) % N] & 0x7fffffff);
            mt[i] = mt[(i + M) % N] ^ (y >>> 1) ^ MAG01[y & 0x1];
        }
        index = 0;
    }

    public int nextInt() {   // functie de numar random
        if (index >= N) twist();

        int y = mt[index++];

        y ^= (y >>> 11);
        y ^= (y << 7) & 0x9d2c5680;
        y ^= (y << 15) & 0xefc60000;  // Tempering
        y ^= (y >>> 18);

        return y;
    }

    public int nextInt(int bound) {
        return Math.abs(nextInt()) % bound;
    }  // daca avem nevoie de range la numar

    public void setId(int id) { this.id = id; }

    public void setSeed(long seed) { this.currentSeed = (int) seed; }

    public void setDataGenerare(LocalDateTime startedAt) { this.dataGenerare = LocalDate.from(startedAt); }

}
