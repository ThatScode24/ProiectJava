import java.util.Set;

public class AdobePhotoshop6 extends Licenta {
    private final MersenneTwister rng = new MersenneTwister();   // folosim mersenne
    private final String productCode = "1045";    // cod de produs pentru adobe photoshop 6

    private final Set<String> Blacklist = Set.of(     // leaked  keys
            "104512345678901234567890",
            "104511111111111111111111"
    );

    public AdobePhotoshop6() { super("Photoshop6"); }

    @Override
    public String genereazaCod() {
        while (true) {
            StringBuilder code = new StringBuilder(productCode);
            while (code.length() < 24) { code.append(rng.nextInt(10)); }  // generam inca 20 de cifre

            String rawCode = code.toString();
            if (!isBlacklistedPattern(rawCode) && !Blacklist.contains(rawCode) && valideazaCod(rawCode)) { this.cod = formatCode(rawCode); return formatCode(rawCode); }  // verificam ca licenta nu e in blacklist
            // si ca este valid
        }
    }

    @Override
    public boolean valideazaCod(String cod) {
        String digitsOnly = cod.replaceAll("-", "");   // scoatem toate -
        if (digitsOnly.length() != 24 || !digitsOnly.matches("\\d+")) return false;    // numai cifre si de lungime 24

        int sum = 0;    // avem aici un fel de Luhn checksum
        boolean alternate = false;
        for (int i = digitsOnly.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(digitsOnly.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) n = (n % 10) + 1;
            }
            sum += n;
            alternate = !alternate;
        }
        return sum % 10 == 0;   // checksum este ok daca se termina cu 0
    }

    private boolean isBlacklistedPattern(String digitsOnly) { return digitsOnly.matches("(\\d)\\1{5,}") || digitsOnly.startsWith("1234") || digitsOnly.startsWith("0000"); }  // numai 0, stil 1234 sau ceva gen 22222222
    private String formatCode(String raw) { return raw.replaceAll("(.{4})(?!$)", "$1-"); } // adaugam un - la fiecare 4 cifre
}