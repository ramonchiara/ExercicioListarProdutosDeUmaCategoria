package daos;

import entities.Categoria;
import entities.Produto;
import java.util.List;

public interface ProdutoDao {

    public List<Produto> findByCategoria(Categoria categoria) throws DaoException;

}
