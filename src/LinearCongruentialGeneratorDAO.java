import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LinearCongruentialGeneratorDAO extends GenericDAO<LinearCongruentialGenerator> {
    private static LinearCongruentialGeneratorDAO instance;

    private LinearCongruentialGeneratorDAO() throws SQLException { super(); }

    public static LinearCongruentialGeneratorDAO getInstance() throws SQLException { if (instance == null) instance = new LinearCongruentialGeneratorDAO(); return instance; }

    @Override
    public void create(LinearCongruentialGenerator lcg) {
        String sql = "INSERT INTO LinearCongruentialGenerator(started_at, increment, modulus, seed) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lcg.getStartedAt().toString()); // ISO 8601 format
            stmt.setLong(2, lcg.getIncrement());
            stmt.setLong(3, lcg.getModulus());
            stmt.setLong(4, lcg.getSeed());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<LinearCongruentialGenerator> readAll() {
        List<LinearCongruentialGenerator> list = new ArrayList<>();
        String sql = "SELECT * FROM LinearCongruentialGenerator";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LinearCongruentialGenerator lcg = new LinearCongruentialGenerator();
                lcg.setId(rs.getInt("id"));
                lcg.setStartedAt(LocalDate.from(LocalDateTime.parse(rs.getString("started_at"))));
                lcg.setIncrement(rs.getLong("increment"));
                lcg.setModulus(rs.getLong("modulus"));
                lcg.setSeed(rs.getLong("seed"));
                list.add(lcg);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public void update(LinearCongruentialGenerator lcg) {
        String sql = "UPDATE LinearCongruentialGenerator SET started_at = ?, increment = ?, modulus = ?, seed = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lcg.getStartedAt().toString());
            stmt.setLong(2, lcg.getIncrement());
            stmt.setLong(3, lcg.getModulus());
            stmt.setLong(4, lcg.getSeed());
            stmt.setInt(5, lcg.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM LinearCongruentialGenerator WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
