package daos;

import daos.jdbc.ProdutoDaoJdbc;
import daos.jdbc.CategoriaDaoJdbc;

public class DaoFactory {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "";
    private static String user = "";
    private static String pass = "";

    public static CategoriaDao getCategoriaDao() {
        return new CategoriaDaoJdbc(driver, url, user, pass);
    }

    public static ProdutoDao getProdutoDao() {
        return new ProdutoDaoJdbc(driver, url, user, pass);
    }

}
