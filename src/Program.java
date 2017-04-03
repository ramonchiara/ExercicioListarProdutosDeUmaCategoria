
import daos.CategoriaDao;
import daos.DaoException;
import daos.DaoFactory;
import daos.ProdutoDao;
import entities.Categoria;
import entities.Produto;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        try {
            CategoriaDao categoriaDao = DaoFactory.getCategoriaDao();
            List<Categoria> categorias = categoriaDao.findAll();
            System.out.println("Categorias:");
            for (Categoria c : categorias) {
                System.out.println(c.getId() + "-" + c.getNome());
            }

            System.out.print("Escolha uma categoria: ");
            Long id = Long.parseLong(console.nextLine());
            Categoria escolhida = categoriaDao.findById(id);
            System.out.println("Produtos da categoria: " + escolhida.getNome());

            ProdutoDao produtoDao = DaoFactory.getProdutoDao();
            List<Produto> produtos = produtoDao.findByCategoria(escolhida);
            for (Produto p : produtos) {
                System.out.println(p.getNome() + "(R$ " + p.getPreco() + ")");
            }

        } catch (DaoException ex) {
            System.out.println(ex);
        }
    }

}
