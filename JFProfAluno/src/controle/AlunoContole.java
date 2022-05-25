/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Aluno;
import util.BancoDados;

/**
 *
 * @author sala303b
 */
public class AlunoContole {
    public static boolean Cadastrar(Aluno a) {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Produto.
            String sql = "INSERT INTO tb_aluno  ";
            sql += " (idusuario, matricula, nomealuno, dtnascimento, sexo) ";
            sql += " VALUES (?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, a.getIdUsuario());
            ps.setString(2, a.getMatricula());
            ps.setString(3, a.getNomeAluno());
            ps.setDate(4, a.getDtNascimento());
            ps.setString(5, a.getSexo());
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                final ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    final int lastId = rs.getInt(1);
                    System.out.println("O numero do id é:"
                            + lastId);
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
