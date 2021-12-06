package model;

public class Aplicativo {
	private int id;
	private String nome;
	private int nota;
	
	public Aplicativo() {
		this.id = -1;
		this.nome = "";
		this.nota = 0;
	}
	
	public Aplicativo(int id, String nome, int nota) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Aplicativo [id=" + id + ", nome=" + nome + ", nota=" + nota + "]";
	}	
	
}
	