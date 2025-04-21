public class LicentaNTGenerator extends Licenta{
    private String dateSegment;
    private String identifier;
    private String validatedSegment;
    private String finalSegment;
    private final MersenneTwister mt;

    public LicentaNTGenerator() {
        super("Windows NT");
        this.dateSegment = dateSegment;
        this.identifier = identifier;
        this.validatedSegment = validatedSegment;
        this.finalSegment = finalSegment;
        this.mt = new MersenneTwister();
    }

    @Override
    public String genereazaCod() {
        String dateSegment = generateDateSegment();
        String identifier = "OEM";
        String validatedSegment = generateValidThirdSegment();
        String finalSegment = generateRandomString(5);
        return dateSegment + "-" + identifier + "-" + validatedSegment + "-" + finalSegment;
    }

    private String generateDateSegment() {
        int julianDay = mt.nextInt(366) + 1; // 001 to 366
        int year = 95 + mt.nextInt(9); // 95 to 03 (1995 to 2003)
        return String.format("%03d%02d", julianDay, year % 100); // e.g., 19296
    }

    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";    // caractere din care generam
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) { sb.append(chars.charAt(mt.nextInt(chars.length()))); }
        return sb.toString();
    }

    private String generateValidThirdSegment() {
        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append("0"); // incepe cu 0 obligatoriu
            for (int i = 1; i < 7; i++) { sb.append(mt.nextInt(10)); }
            String segment = sb.toString();
            if (isValidSegment(segment)) { return segment; }
        }
    }
    private boolean isValidSegment(String segment) {
        int sum = 0;
        for (char c : segment.toCharArray()) { sum += Character.getNumericValue(c); }
        return sum % 7 == 0;  // suma sa se imparta cu 7
    }

    @Override
    public boolean valideazaCod(String cod) {
        if (cod == null || cod.isEmpty()) return false;

        String[] parts = cod.split("-");
        if (parts.length != 4) return false;

        String dateSegment = parts[0];
        String identifier = parts[1];
        String validatedSegment = parts[2];
        String finalSegment = parts[3];

        // identifier tb sa fie OEM
        if (!identifier.equals("OEM")) return false;

        // 2.  5 cifre, 3 zi Juliana si un an de 2 cifre
        if (!dateSegment.matches("\\d{5}")) return false;
        int julianDay, year;
        try {
            julianDay = Integer.parseInt(dateSegment.substring(0, 3));
            year = Integer.parseInt(dateSegment.substring(3, 5));
        } catch (NumberFormatException e) { return false; }

        if (julianDay < 1 || julianDay > 366) return false;
        if ((year < 95 && year > 3)) return false; // 95-99 si 00-03

        // validare incepere cu 0 si suma modulo 7 = 0
        if (!validatedSegment.matches("0\\d{6}")) return false;
        int sum = 0;
        for (char c : validatedSegment.toCharArray()) { sum += Character.getNumericValue(c); }
        if (sum % 7 != 0) return false;

        //  5 caractere alfanumerice
        return finalSegment.matches("[A-Z0-9]{5}");
    }
}
