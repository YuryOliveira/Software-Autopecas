// Single-file version: LojaXPTO_SingleFile.java
import java.sql.*;
import java.util.*;
import java.math.*;
import java.sql.Timestamp;

class Peca { /* fields and getters/setters omitted for brevity in single-file demo */
    public Integer id; public String nome; public BigDecimal preco; public Integer estoqueMinimo; public Integer idFornecedor;
}
class EstoqueMovimento { public Integer id; public Timestamp dataMovimento; public Integer idPeca; public Integer quantidade; public String tipoOperacao; public Integer idLoja; }
class ItemPedido { public Integer id; public Integer idPedido; public Integer idPeca; public Integer quantidade; public BigDecimal precoUnitario; }
class Pedido { public Integer id; public String idCliente; public String idVendedor; public Date dataPedido; public BigDecimal valorTotal; public BigDecimal desconto; public String formaPagamento; public String statusPedido; public List<ItemPedido> itens = new ArrayList<>(); }

public class LojaXPTO_SingleFile {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC", "root", "senha");
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Single-file demo started (very minimal).");
        try (Connection conn = getConnection()) {
            System.out.println("Connected to DB OK.");
            // Demo: list pieces (simple)
            var st = conn.prepareStatement("SELECT id, nome, preco, local_estoque, id_fornecedor_peca FROM peca");
            var rs = st.executeQuery();
            while (rs.next()) {
                System.out.printf("Peca[id=%d,nome=%s,preco=%s]%n", rs.getInt("id"), rs.getString("nome"), rs.getBigDecimal("preco"));
            }
        }
    }
}