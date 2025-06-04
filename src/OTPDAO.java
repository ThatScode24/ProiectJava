import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OTPDAO extends GenericDAO<OTP> {
    private static OTPDAO instance;

    private OTPDAO() throws SQLException { super(); }

    public static OTPDAO getInstance() throws SQLException { if (instance == null) instance = new OTPDAO(); return instance; }

    @Override
    public void create(OTP otp) {
        String sql = "INSERT INTO OneTimePassword(generated_at, password) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, otp.getGeneratedAt().toString());
            stmt.setString(2, otp.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<OTP> readAll() {
        List<OTP> list = new ArrayList<>();
        String sql = "SELECT * FROM OneTimePassword";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OTP otp = new OTP();
                otp.setId(rs.getInt("id"));
                otp.setGeneratedAt(LocalDate.from(LocalDateTime.parse(rs.getString("generated_at"))));
                otp.setPassword(rs.getString("password"));
                list.add(otp);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public void update(OTP otp) {
        String sql = "UPDATE OneTimePassword SET generated_at=?, password=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, otp.getGeneratedAt().toString());
            stmt.setString(2, otp.getPassword());
            stmt.setInt(3, otp.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM OneTimePassword WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
