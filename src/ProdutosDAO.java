/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @Odair jm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
     String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try (Connection conn = new conectaDAO().connectDB(); 
         PreparedStatement pstm = conn.prepareStatement(sql)) {

        pstm.setString(1, produto.getNome());
        pstm.setInt(2, produto.getValor());
        pstm.setString(3, produto.getStatus());

        pstm.execute();
        return true;

    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar: " + e.getMessage());
        return false;
    }
        
    }
    public boolean venderProduto(int idProduto) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement pstm = conn.prepareStatement(sql)) {

        pstm.setInt(1, idProduto);
        pstm.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Erro ao vender produto: " + e.getMessage());
        return false;
    }
}

    /**
 *
 * listagem
 */
    public ArrayList<ProdutosDTO> listarProdutos(){
  ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement pstm = conn.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery()) {

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            lista.add(produto);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar produtos: " + e.getMessage());
    }

    return lista;
    }
    /**
     * Outra listagem
     */
        public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement pstm = conn.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery()) {

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            lista.add(produto);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar vendidos: " + e.getMessage());
    }

    return lista;
}
}

