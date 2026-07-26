import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private static final String URL = "jdbc:sqlite:vidracaria.db";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            return conn;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }

    public static void criarTabelas() {
        String sqlReceitas = """
            CREATE TABLE IF NOT EXISTS receitas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nomeCliente TEXT,
                telefone TEXT,
                descricao TEXT,
                valor REAL,
                data TEXT,
                status TEXT
            )
        """;

        String sqlDespesas = """
            CREATE TABLE IF NOT EXISTS despesas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                descricao TEXT,
                valor REAL,
                data TEXT
            )
        """;

        try {
            Connection conn = conectar();
            conn.createStatement().execute(sqlReceitas);
            conn.createStatement().execute(sqlDespesas);
            System.out.println("Banco de dados pronto!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public static void salvarReceita(Receita receita) {
        String sql = "INSERT INTO receitas (nomeCliente, telefone, descricao, valor, data, status) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = conectar();
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, receita.getNomeCliente());
            stmt.setString(2, receita.gettelefone());
            stmt.setString(3, receita.getDescricao());
            stmt.setDouble(4, receita.getValor());
            stmt.setString(5, receita.getData());
            stmt.setString(6, receita.getstatus());
            stmt.executeUpdate();
            conn.close();
            System.out.println("Receita salva no banco!");
        }catch (SQLException e) {
            System.out.println("Erro ao salvar receita: " + e.getMessage());
        }
    }
    
    public static void salvarDespesa(Despesa despesa) {

        String sql = "INSERT INTO despesas (descricao, valor, data) VALUES (?, ?, ?)";

        try {
            Connection conn = conectar();
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2, despesa.getValor());
            stmt.setString(3, despesa.getData());
            stmt.executeUpdate();
            conn.close();
            System.out.println("Despesa salva no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar despesa: " + e.getMessage());
        }
    }

    public static void excluirDespesa(int id) {
    String sql = "DELETE FROM despesas WHERE id = ?";

    try {
        Connection conn = conectar();
        var stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        conn.close();
    }catch (SQLException e) {
        System.out.println("Erro ao excluir despesa: " + e.getMessage());
    }
}
    public static void excluirReceita(int id) {
        String sql = "DELETE FROM receitas WHERE id = ?";

        try {
            Connection conn = conectar();
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Receita excluída!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }

    public static ArrayList<Receita> listarReceitas() {

        ArrayList<Receita> lista = new ArrayList<>();
        String sql = "SELECT * FROM receitas";

        try {
            Connection conn = conectar();
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Receita r = new Receita(
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getString("data"),
                rs.getString("nomeCliente"),
                rs.getString("telefone"),
                rs.getString("status")
                );
                r.setId(rs.getInt("id"));
                lista.add(r);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar receitas: " + e.getMessage());
        }
        return lista;
    }

    public static ArrayList<Despesa> listarDespesas() {
        ArrayList<Despesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM despesas";

        try {
            Connection conn = conectar();
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Despesa d = new Despesa(
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getString("data")
                );
                d.setId(rs.getInt("id")); // ✅ aqui dentro do while
                lista.add(d);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar despesas: " + e.getMessage());
        }
        return lista;
    }

    public static ArrayList<Receita> pesquisarCliente(String nome) {
        ArrayList<Receita> lista = new ArrayList<>();
        String sql = "SELECT * FROM receitas WHERE nomeCliente LIKE ? OR descricao LIKE ? OR data LIKE ? OR status LIKE ?";

        try {
            Connection conn = conectar();
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + nome + "%");
            stmt.setString(3, "%" + nome + "%");
            stmt.setString(4, "%" + nome + "%");
            var rs = stmt.executeQuery();

            while (rs.next()) {
                Receita r = new Receita(
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getString("data"),
                rs.getString("nomeCliente"),
                rs.getString("telefone"),
                rs.getString("status")
                );
                lista.add(r);
            }
            conn.close();
            }catch (SQLException e) {
                    System.out.println("Erro ao pesquisar: " + e.getMessage());
            }
            return lista;
    }
}