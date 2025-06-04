import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LicentaDAO extends GenericDAO<Licenta>{
    private static LicentaDAO instance;

    private LicentaDAO() throws SQLException { super(); }

    public static LicentaDAO getInstance() throws SQLException { if (instance == null) instance = new LicentaDAO(); return instance; }

    @Override
    public void create(Licenta licenta) {

        String sql = "INSERT INTO licente(cod, sistem, dataGenerare) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, licenta.getCod());
            stmt.setString(2, licenta.getSistem());
            stmt.setString(3, licenta.getDataGenerare().toString());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<Licenta> readAll() {
        List<Licenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM licente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String sistem = rs.getString("sistem");
                Licenta l = switch (sistem) {
                    case "TextPad" -> new TextPad();
                    case "Windows95" -> new Licenta95Generator();
                    case "SimCity2000" -> new SimCity2000();
                    case "Photoshop6" -> new AdobePhotoshop6();
                    case "WindowsNT" -> new LicentaNTGenerator();
                    default -> throw new IllegalArgumentException("Unknown sistem: " + sistem);
                };
                l.setId(rs.getInt("id"));
                l.setCod(rs.getString("cod"));
                l.setDataGenerare(LocalDate.parse(rs.getString("dataGenerare")));
                lista.add(l);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void update(Licenta licenta) {
        String sql = "UPDATE licente SET cod=?, sistem=?, dataGenerare=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, licenta.getCod());
            stmt.setString(2, licenta.getSistem());
            stmt.setString(3, licenta.getDataGenerare().toString());
            stmt.setInt(4, licenta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM licente WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
