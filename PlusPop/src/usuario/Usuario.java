package usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import manager.Post;

public class Usuario {
	
	// TODO alteracao do java 8 de data

	private String email;
	private String senha;
	private String nome;
	private String dataDeNascimento;
	private String foto;
	private boolean log;
	private List<Post> posts;

	public Usuario(String nome, String email, String senha, String dataDeNascimento, String foto) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.foto = foto;
		this.log = false;
		this.posts = new ArrayList<Post>();
	}

	public Usuario(String nome, String email, String senha, String dataDeNascimento) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.foto = "resources/default.jpg";
		this.log = true;
		this.posts = new ArrayList<Post>();
	}

	public boolean login() {
		this.log = true;
		return this.log;
	}

	public boolean logout() {
		if (this.log == true) {
			this.log = false;
			return true;
		} else {
			return false;
		}
	}

	public void postar(Post novoPost) {
		this.posts.add(novoPost);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario outroUsuario = (Usuario) obj;
			return this.email.equals(outroUsuario.getEmail());
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public String getDataDeNascimento() {
		DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		return dataFormat.format(this.dataDeNascimento);
	}

	public void setDataDeNascimento(String novaDataDeNascimento) {
		this.dataDeNascimento = novaDataDeNascimento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String novaFoto) {
		this.foto = novaFoto;
	}

	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String toString() {
		String EOL = System.getProperty("line.separator");
		String saida = "Usuario: " + this.nome + EOL + "Email: " + this.email;
		return saida;
	}
}