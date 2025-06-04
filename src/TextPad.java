public class TextPad extends Licenta {
    private final MersenneTwister mt;

    public TextPad() { super("TextPad"); this.mt = new MersenneTwister(); }

    public String genereazaCod() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (i > 0) key.append("-");
            int part = mt.nextInt(90000) + 10000; // bloc de 5 cifre
            key.append(part);
        }
        cod = key.toString(); return key.toString();
    }

    public boolean valideazaCod(String cod) {
        // verificam formatul cu regex
        if (!cod.matches("\\d{5}-\\d{5}-\\d{5}")) return false;

        // suma cifrelor divizibila cu 7
        String digitsOnly = cod.replace("-", "");
        int sum = 0;
        for (char c : digitsOnly.toCharArray())  sum += Character.getNumericValue(c);

        return sum % 7 == 0;
    }
}
