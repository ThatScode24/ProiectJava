import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String URL = "jdbc:sqlite:licente.db"; // link catre baza noastra sqlite

    public static Connection getConnection() throws SQLException { return DriverManager.getConnection(URL); }
}