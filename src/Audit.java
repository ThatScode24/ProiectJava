import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit{
    private static final String FILE_NAME = "audit.csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String actiune) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String linie = actiune + "," + timestamp + "\n";
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { writer.write(linie); } catch (IOException e) { System.err.println("Eroare scriere audit: " + e.getMessage()); }
    }
}
