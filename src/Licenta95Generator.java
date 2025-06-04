public class Licenta95Generator extends Licenta{
    private final LinearCongruentialGenerator lcg;
    public Licenta95Generator() {
        super("Windows95");
        this.lcg = new LinearCongruentialGenerator();   // folism LCG pentru generare de data asta
    }

    @Override
    public String genereazaCod() {
        String site;
        do {
            site = String.format("%03d", Math.abs(lcg.nextInt()) % 1000);
        } while (site.matches("333|444|555|666|777|888|999"));

        String serial;
        do {
            int number = Math.abs(lcg.nextInt()) % 10_000_000;
            serial = String.format("%07d", number);
        } while (!isValidSerial(serial));

        String finn = site + "-" + serial; this.cod = finn; return finn;
    }

    private boolean isValidSerial(String serial) {    // functie pentru verificare part 2 a licentei
        int digitSum = 0;
        for (char c : serial.toCharArray()) {
            digitSum += Character.getNumericValue(c);
        }

        int lastDigit = Character.getNumericValue(serial.charAt(serial.length() - 1));
        return digitSum % 7 == 0 && lastDigit > 0 && lastDigit < 8;
    }

    @Override
    public boolean valideazaCod(String cod) {
        if (!cod.matches("\\d{3}-\\d{7}")) return false;

        String site = cod.substring(0, 3);
        String serial = cod.substring(4);

        if (site.matches("333|444|555|666|777|888|999")) return false;

        return isValidSerial(serial);
    }

}
