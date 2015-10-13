package usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manager.Post;
import friendship.Friendship;
import friendship.Notificacao;

public class Usuario implements Friendship {

	// TODO alteracao do java 8 de data

	private String email;
	private String senha;
	private String nome;
	private Date dataDeNascimento;
	private String foto;
	private boolean log;
	private List<Post> posts;
	private List<Usuario> amigos;
	private Notificacao notificacoes;
	private List<Usuario> solicitacoesDeAmizade;

	public Usuario(String nome, String email, String senha, String dataDeNascimento, String foto)
			throws ParseException {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = formataDataDeNascimento(dataDeNascimento);
		this.foto = foto;
		this.log = false;
		this.posts = new ArrayList<Post>();
		this.amigos = new ArrayList<Usuario>();
		this.notificacoes = new Notificacao();
		this.solicitacoesDeAmizade = new ArrayList<Usuario>();
	}

	public Usuario(String nome, String email, String senha, String dataDeNascimento) throws ParseException {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = formataDataDeNascimento(dataDeNascimento);
		this.foto = "resources/default.jpg";
		this.log = true;
		this.posts = new ArrayList<Post>();
		this.amigos = new ArrayList<Usuario>();
		this.notificacoes = new Notificacao();
		this.solicitacoesDeAmizade = new ArrayList<Usuario>();
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

	public void addNotificacao(String mensagem) {
		this.notificacoes.addNotificacao(mensagem);
	}
	
	public void removeNotificacao(String notificacao) {
		this.notificacoes.removeNotificacao(notificacao);
	}
	
	private Date formataDataDeNascimento(String data) throws ParseException {
		if (data == null || data.equals(""))
			return null;

		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
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
		DateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd");
		return dataFormatada.format(this.dataDeNascimento);
	}

	public void setDataDeNascimento(String novaDataDeNascimento) throws ParseException {
		this.dataDeNascimento = formataDataDeNascimento(novaDataDeNascimento);
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

	public List<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}

	public List<String> getNotificacoes() {
		return this.notificacoes.getNotificacoes();		
	}

	public List<Usuario> getSolicitacoesDeAmizade() {
		return solicitacoesDeAmizade;
	}

	public String toString() {
		String EOL = System.getProperty("line.separator");
		String saida = "Usuario: " + this.nome + EOL + "Email: " + this.email;
		return saida;
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

	// Implementation of Friendship interface;

	@Override
	public void aceitaAmigo(Usuario novoAmigo) {
		this.amigos.add(novoAmigo);
	}

	@Override
	public void removeAmigo(Usuario amigoRemovido) {
		this.amigos.remove(amigoRemovido);
	}
	
	@Override
	public void addSolicitacao(Usuario usuario) {
		this.solicitacoesDeAmizade.add(usuario);
	}

	@Override
	public Usuario buscaAmigo(String emailDoAmigo) {
		for (Usuario usuario : amigos) {
			if (usuario.getEmail().equals(emailDoAmigo)) {
				return usuario;
			}
		}
		return null;
	}
}