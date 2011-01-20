package inter.intergrator.file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class teste {
	public static void main(String[] args) {
		Connection con = null;
		try {
		Class.forName("oracle.jdbc.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@//173.22.21.193:1521/FORTAL", "INTER_ADMIN", "!*inter555.com.br");
		System.out.println("connectado");
		con.setAutoCommit(false);
		} catch (ClassNotFoundException ex) {
		System.out.println("Classe do driver nao encontrada: " + ex.getMessage());
		System.exit(0);
		} catch (SQLException ex) {
		System.out.println("Impossivel conectar com banco principal: " + ex.getMessage());
		System.exit(0);
		}
		try {
		ResultSet rs = con.createStatement().executeQuery("select count(0) from tb_loop_fortal");
		while (rs.next()){
		System.out.println(rs.getString(1));
		}
		} catch (SQLException ex) {
		System.out.println("Erro na query ");
		System.exit(0);
		}



		}
}
