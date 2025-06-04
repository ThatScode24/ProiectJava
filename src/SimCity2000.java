// format: SC2K-XXXXXX
// folosim Mersenne

public class SimCity2000 extends Licenta {
    private final MersenneTwister rng = new MersenneTwister();
    private static final String PREFIX = "SC2K-";  // toate licentele incep cu prefixul asta

    public SimCity2000() { super("SimCity2000"); }


    @Override
    public String genereazaCod() {
        while (true) {
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < 6; i++) code.append(rng.nextInt(10));   // 6 numere random

            String digits = code.toString();

            if (!isBlacklisted(digits) && valideazaCod(PREFIX + digits)) this.cod = PREFIX + digits; return PREFIX + digits;  // verificam blacklist si validare
        }
    }

    @Override
    public boolean valideazaCod(String cod) {
        // dam strip la prefix
        if (!cod.startsWith(PREFIX)) return false;

        String digits = cod.substring(PREFIX.length());

        if (!digits.matches("\\d{6}")) return false;


        int sum = digits.chars().map(ch -> ch - '0').sum();
        if (sum % 3 != 0) return false;    // suma sa fie divizibila cu 3


        int last = digits.charAt(5) - '0';

        return last % 2 == 0;   // ultima cifra tb sa fie para
    }

    private boolean isBlacklisted(String digits) { return digits.matches("(\\d)\\1{5}") || "000000".equals(digits); } // fara lucruri de genu 0000000...
}
