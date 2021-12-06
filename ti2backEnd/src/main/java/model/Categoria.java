package model;

public class Categoria {
		private int id;
		private String nome;
	  
		
		public Categoria() {
			this.id = -1;
			this.nome = "";
			
		}
		
		public Categoria(int id, String nome) {
			this.id = id;
			this.nome = nome;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String texto) {
			this.nome = texto;
		}

		@Override
		public String toString() {
			return "Categoria [id=" + id + ", nome=" + nome +"]";
		}	
		
}
