package dao;

import java.sql.*;

import model.Conteudo; 

public class ConteudoDAO {
private Connection conexao;
	
	public ConteudoDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "EADTools";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

//DAO USUARIO 
	public boolean inserirConteudo(Conteudo conteudo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO eadtoolbd.conteudo (\"ID\", \"Apliativo_ID\", \"Dica\", \"Imagem\", \"Tutoriais\", \"Dispositivos\") " + "VALUES ("+conteudo.getId() + ", " + conteudo.getIdAplicativo() + ", '"  
            + conteudo.getDica() + "', '" + conteudo.getImagem() +  "', '" + conteudo.getTutoriais() + "', '"  + conteudo.getDispositivos() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarConteudo(Conteudo conteudo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE eadtoolbd.conteudo SET \"Dica\" = '"  
            + conteudo.getDica() +  "', \"Imagem\" = '"  
            + conteudo.getImagem() +  "', \"Tutoriais\" = '"  
            + conteudo.getDispositivos() +   "', \"Dispositivos\" = '"  
            + conteudo.getIdAplicativo() + " WHERE \"ID\" = " + conteudo.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirConteudo(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM eadtoolbd.conteudo WHERE \"ID\" = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Conteudo[] getConteudos() {
		Conteudo[] conteudos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM eadtoolbd.conteudo");		
	         if(rs.next()){
	             rs.last();
	             conteudos = new Conteudo[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                    conteudos[i] = new Conteudo(rs.getInt("\"ID\""), rs.getInt("\"Aplicativo_ID\""), rs.getString("\"Dica\""), rs.getString("\"Imagem\""), rs.getString("\"Tutoriais\""), rs.getString("\"Dispositivos\""));
	                }
	            } 
	            st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return conteudos;
	}
}
