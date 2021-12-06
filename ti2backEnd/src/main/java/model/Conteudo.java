package model;

public class Conteudo {
	  private int idAplicativo;
	  private int id;
	  private String dica;
	  private String imagem;
	  private String tutoriais;
	  private String dispositivos;
	    
		
		public Conteudo() {
			this.idAplicativo = -1;
			this.id = -1;
			this.dica = "";
	        this.imagem = "";
	        this.tutoriais = "";
	        this.dispositivos = "";
		}
		
		public Conteudo(int idAplicativo, int id, String dica, String imagem, String tutoriais , String dispositivos) {
			this.idAplicativo = idAplicativo;
			this.id = id;
			this.dica = dica;
	        this.imagem = imagem;
	        this.tutoriais = tutoriais;
	        this.dispositivos = dispositivos;
		}

		public int getIdAplicativo() {
			return idAplicativo;
		}

		public void setIdAplicativo(int idAplicativo) {
			this.idAplicativo = idAplicativo;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDica() {
			return dica;
		}

		public void setDica(String dica) {
			this.dica = dica;
		}

	    public String getImagem() {
			return imagem;
		}

		public void setImagem(String imagem) {
			this.imagem = imagem;
		}

	    public String getTutoriais() {
			return tutoriais;
		}

		public void setTutoriais(String tutoriais) {
			this.tutoriais = tutoriais;
		}

	    public String getDispositivos() {
			return dispositivos;
		}

		public void setDispositivos(String dispositivos) {
			this.dispositivos = dispositivos;
		}


		@Override
		public String toString() {
			return "Conteudo [idAplicativo=" + idAplicativo + ", id=" + id + ", dica=" + dica + ", imagem=" + imagem + ", tutoriais=" + tutoriais + ", dispositivos=" + dispositivos +"]";
		}
		
}
