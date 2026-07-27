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
                status TEXT,
                tipoVidro TEXT
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
            // Adiciona coluna tipoVidro se não existir (para bancos antigos)
            try {
                conn.createStatement().execute("ALTER TABLE receitas ADD COLUMN tipoVidro TEXT");
            } catch (SQLException ignored) {}
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public static void salvarReceita(Receita receita) {
        String sql = "INSERT INTO receitas (nomeCliente, telefone, descricao, valor, data, status, tipoVidro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = conectar();
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, receita.getNomeCliente());
            stmt.setString(2, receita.getTelefone());
            stmt.setString(3, receita.getDescricao());
            stmt.setDouble(4, receita.getValor());
            stmt.setString(5, receita.getData());
            stmt.setString(6, receita.getStatus());
            stmt.setString(7, receita.getTipoVidro());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            System.out.println("Erro ao salvar despesa: " + e.getMessage());
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
        } catch (SQLException e) {
            System.out.println("Erro ao excluir receita: " + e.getMessage());
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
        } catch (SQLException e) {
            System.out.println("Erro ao excluir despesa: " + e.getMessage());
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
                    rs.getString("status"),
                    rs.getString("tipoVidro")
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
                d.setId(rs.getInt("id"));
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
                    rs.getString("status"),
                    rs.getString("tipoVidro")
                );
                r.setId(rs.getInt("id"));
                lista.add(r);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar: " + e.getMessage());
        }
        return lista;
    }

    public static double[] calcularRelatorioPorPeriodo(String dataInicio, String dataFim, String tipoVidro) {
        double totalReceitas = 0;
        double totalDespesas = 0;

        // Converte dd/mm/aaaa para aaaa-mm-dd para comparação correta
        String[] partsInicio = dataInicio.split("/");
        String[] partsFim = dataFim.split("/");
        
        String inicioConvertido = partsInicio[2] + "-" + partsInicio[1] + "-" + partsInicio[0];
        String fimConvertido = partsFim[2] + "-" + partsFim[1] + "-" + partsFim[0];

        // Busca todas as receitas e filtra manualmente
        ArrayList<Receita> todasReceitas = listarReceitas();
        for (Receita r : todasReceitas) {
            try {
                String[] parts = r.getData().split("/");
                String dataConvertida = parts[2] + "-" + parts[1] + "-" + parts[0];
                
                boolean dentroDoperiodo = dataConvertida.compareTo(inicioConvertido) >= 0 
                                    && dataConvertida.compareTo(fimConvertido) <= 0;
                boolean tipoOk = tipoVidro.equals("Todos") || tipoVidro.equals(r.getTipoVidro());
                
                if (dentroDoperiodo && tipoOk) {
                    totalReceitas += r.getValor();
                }
            } catch (Exception ignored) {}
        }

        // Busca todas as despesas e filtra manualmente
        ArrayList<Despesa> todasDespesas = listarDespesas();
        for (Despesa d : todasDespesas) {
            try {
                String[] parts = d.getData().split("/");
                String dataConvertida = parts[2] + "-" + parts[1] + "-" + parts[0];
                
                boolean dentroDoperiodo = dataConvertida.compareTo(inicioConvertido) >= 0 
                                    && dataConvertida.compareTo(fimConvertido) <= 0;
                
                if (dentroDoperiodo) {
                    totalDespesas += d.getValor();
                }
            } catch (Exception ignored) {}
        }

        return new double[]{totalReceitas, totalDespesas, totalReceitas - totalDespesas};
    }
}