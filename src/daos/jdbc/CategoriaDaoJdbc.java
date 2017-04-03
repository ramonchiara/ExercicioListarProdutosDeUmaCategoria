package daos.jdbc;

import daos.CategoriaDao;
import daos.DaoException;
import entities.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDaoJdbc implements CategoriaDao {

    private String driver;
    private String url;
    private String user;
    private String pass;

    public CategoriaDaoJdbc(String driver, String url, String user, String pass) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public List<Categoria> findAll() throws DaoException {
        List<Categoria> categorias = new ArrayList<Categoria>();

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sql = "select * from Categoria order by nomeCategoria";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("idCategoria");
                String nome = rs.getString("nomeCategoria");
                Categoria categoria = new Categoria(id, nome);
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return categorias;
    }

    public Categoria findById(Long id) throws DaoException {
        Categoria categoria = null;

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sql = "select * from Categoria where idCategoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nomeCategoria");
                categoria = new Categoria(id, nome);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return categoria;
    }

}
