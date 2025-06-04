import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MersenneTwisterDAO extends GenericDAO<MersenneTwister> {
    private static MersenneTwisterDAO instance;

    private MersenneTwisterDAO() throws SQLException { super(); }

    public static MersenneTwisterDAO getInstance() throws SQLException {
        if (instance == null) instance = new MersenneTwisterDAO();
        return instance;
    }

    @Override
    public void create(MersenneTwister mt) {
        String sql = "INSERT INTO MersenneTwister(seed, started_at) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, mt.getSeed());
            stmt.setString(2, mt.getDataGenerare().toString());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<MersenneTwister> readAll() {
        List<MersenneTwister> list = new ArrayList<>();
        String sql = "SELECT * FROM MersenneTwister";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MersenneTwister mt = new MersenneTwister();
                mt.setId(rs.getInt("id"));
                mt.setSeed(rs.getLong("seed"));
                mt.setDataGenerare(LocalDateTime.parse(rs.getString("started_at"))); // ISO 8601 format
                list.add(mt);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public void update(MersenneTwister mt) {
        String sql = "UPDATE MersenneTwister SET seed = ?, started_at = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, mt.getSeed());
            stmt.setString(2, mt.getDataGenerare().toString());
            stmt.setInt(3, mt.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM MersenneTwister WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
