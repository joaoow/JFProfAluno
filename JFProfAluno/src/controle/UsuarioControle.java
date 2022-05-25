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
import util.BancoDados;

/**
 *
 * @author sala303b
 */
public class UsuarioControle {
    public static long TemUsuario(String login, String senha) {

        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_usuario ";
            sql += " WHERE login = ? AND senha = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int cod = rs.getInt("id");

                if (cod > 0) {

                    return cod;
                }

            }
            return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }
    
    
}
