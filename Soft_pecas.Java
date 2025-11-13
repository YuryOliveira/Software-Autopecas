import java.sql.*;
import java.util.*;

class Peca {
    private int id;
    private String nome;
    private int codigoFabricante;
    private double preco;
    private int localEstoque;
    private int idFornecedor;
    // Getters e Setters
}

class PecaDAO {
    private Connection conn;
    public PecaDAO(Connection conn) { this.conn = conn; }

    public List<Peca> listarTodas() throws SQLException {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT * FROM peca";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Peca p = new Peca();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));
            p.setIdFornecedor(rs.getInt("id_fornecedor_peca"));
            pecas.add(p);
        }
        return pecas;
    }
}

class EstoqueDAO {
    private Connection conn;
    public EstoqueDAO(Connection conn) { this.conn = conn; }

    public int obterQuantidadeAtual(int idPeca) throws SQLException {
        String sql = "SELECT SUM(quantidade * IF(tipo_operacao='entrada',1,-1)) as saldo FROM estoque WHERE id_peca_estoque=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idPeca);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("saldo");
        return 0;
    }

    public void registrarSaida(int idPeca, int qtd) throws SQLException {
        String sql = "INSERT INTO estoque (data_movimento,id_peca_estoque,quantidade,tipo_operacao,id_loja_estoque) VALUES (NOW(),?,?, 'saida',1)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idPeca);
        ps.setInt(2, qtd);
        ps.executeUpdate();
    }
}

class EstoqueService {
    private EstoqueDAO estoqueDAO;
    private PecaDAO pecaDAO;

    public EstoqueService(EstoqueDAO e, PecaDAO p) {
        this.estoqueDAO = e;
        this.pecaDAO = p;
    }

    public void gerarRelatorioEstoque() throws SQLException {
        List<Peca> pecas = pecaDAO.listarTodas();
        for (Peca p : pecas) {
            int qtd = estoqueDAO.obterQuantidadeAtual(p.getId());
            System.out.printf("Peça: %s | Quantidade: %d%n", p.getNome(), qtd);
        }
    }

    public void verificarEstoqueMinimo(int minimo) throws SQLException {
        List<Peca> pecas = pecaDAO.listarTodas();
        for (Peca p : pecas) {
            int qtd = estoqueDAO.obterQuantidadeAtual(p.getId());
            if (qtd < minimo) {
                System.out.println("ALERTA: Peça " + p.getNome() + " abaixo do estoque mínimo!");
            }
        }
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "senha");
        PecaDAO pecaDAO = new PecaDAO(conn);
        EstoqueDAO estoqueDAO = new EstoqueDAO(conn);
        EstoqueService estoqueService = new EstoqueService(estoqueDAO, pecaDAO);
        estoqueService.gerarRelatorioEstoque();
        estoqueService.verificarEstoqueMinimo(5);
    }
}

