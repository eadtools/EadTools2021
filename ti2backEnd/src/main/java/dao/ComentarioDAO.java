package dao;

import java.sql.*;

import model.Comentario;

public class ComentarioDAO {
	private Connection conexao;
		
		public ComentarioDAO() {
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
		public boolean inserirComentario(Comentario comentario) {
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				st.executeUpdate("INSERT INTO eadtoolbd.comentario (\"ID\", \"Data\", \"Texto\", \"superComentario\", \"Usuario_ID\", \"Aplicativo_ID\") " + "VALUES ("+comentario.getId() + ", '" + comentario.getData() + "', '"  
	            + comentario.getTexto() + "', " + comentario.getSuperComentario() +  ", " + comentario.getIdUsuario() + ", "  + comentario.getIdAplicativo() + ");");
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}
		
		public boolean atualizarComentario(Comentario comentario) {
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				String sql = "UPDATE eadtoolbd.comentario SET \"Data\" = '"  
	            + comentario.getData() +  "', \"Texto\" = '"  
	            + comentario.getTexto() +  "'"
	            + " WHERE \"ID\" = " + comentario.getId();
				st.executeUpdate(sql);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}
		
		public boolean excluirComentario(int id) {
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				st.executeUpdate("DELETE FROM eadtoolbd.comentario WHERE \"ID\" = " + id);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}
		
		public Comentario[] getComentario() {
			Comentario[] comentarios = null;
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery("SELECT * FROM eadtoolbd.comentario");		
		         if(rs.next()){
		             rs.last();
		             comentarios = new Comentario[rs.getRow()];
		             rs.beforeFirst();

		             for(int i = 0; rs.next(); i++) {
		                    comentarios[i] = new Comentario(rs.getInt("\"ID\""), rs.getString("\"Data\""), rs.getString("\"Texto\""), rs.getInt("\"superComentario\""), rs.getInt("\"Usuario_ID\""), rs.getInt("\"Aplicativo_ID\""));
		                }
		            } 
		            st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return comentarios;
		}
}
