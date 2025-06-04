import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// Generic dao de pe care derivam pentru fiecare clasa in parte


public abstract class GenericDAO<T> {
    public Connection conn;

    protected GenericDAO() throws SQLException { this.conn = DBManager.getConnection(); }

    public abstract void create(T entity);
    public abstract List<T> readAll();
    public abstract void update(T entity);
    public abstract void delete(int id);
}
