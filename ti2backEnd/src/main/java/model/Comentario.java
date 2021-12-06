package model;

public class Comentario {
	private int id;
	private String data;
	private String texto;
	private int superComentario;
	private int idUsuario;
  	private int idAplicativo;
	
	public Comentario() {
		this.id = -1;
		this.data = "";
		this.texto = "";
        this.superComentario = 0;
        this.idUsuario = 0;
        this.idAplicativo = 0;
	}
	
	public Comentario(int id, String data, String texto, int superComentario , int idUsuario, int idAplicativo) {
		this.id = id;
		this.data = data;
		this.texto = texto;
        this.superComentario = superComentario;
        this.idUsuario = idUsuario;
        this.idAplicativo = idAplicativo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

    public int getSuperComentario() {
		return superComentario;
	}

	public void setSuperComentario(int superComentario) {
		this.superComentario = superComentario;
	}

    public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

    public int getIdAplicativo() {
		return idAplicativo;
	}

	public void setIdAplicativo(int idAplicativo) {
		this.idAplicativo = idAplicativo;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", data=" + data + ", texto=" + texto + ", superComentario=" + superComentario + "idUsuario" + idUsuario + ", idAplicativo=" + idAplicativo +"]";
	}	
}
