package user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.UtilUsuario;
import exceptions.EntradaException;
import friendship.Friendship;
import friendship.Notificacao;
import interaction.CelebridadePOP;
import interaction.IconePOP;
import interaction.Interacao;
import interaction.Normal;
import interaction.Post;

public class Usuario implements Friendship, Comparable<Usuario> {

	private String email;
	private String senha;
	private String nome;
	private Date dataDeNascimento;
	private String foto;
	private List<Post> posts;
	private List<Usuario> amigos;
	private Notificacao notificacoes;
	private List<Usuario> solicitacoesDeAmizade;
	private Interacao popularidade;
	private int pops;
	private int popsMagico;

	/**
	 * Construtor da classe Usuario.
	 * 
	 * @param nome
	 * @param email
	 * @param senha
	 * @param dataDeNascimento
	 * @param foto
	 * @throws ParseException
	 * @throws EntradaException
	 */

	public Usuario(String nome, String email, String senha,
			String dataDeNascimento, String foto) throws ParseException,
			EntradaException {

		UtilUsuario.validaNomeCadastro(nome);
		UtilUsuario.validaDataCadastro(dataDeNascimento);
		UtilUsuario.validaEmailCadastro(email);
		UtilUsuario.validaSenhaCadastro(senha);
		UtilUsuario.validaFotoCadastro(foto);

		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = formataDataDeNascimento(dataDeNascimento);
		this.foto = foto;
		this.posts = new ArrayList<Post>();
		this.amigos = new ArrayList<Usuario>();
		this.notificacoes = new Notificacao();
		this.solicitacoesDeAmizade = new ArrayList<Usuario>();
		this.popularidade = new Normal();
		this.pops = 0;
		this.popsMagico = 0;
	}

	public Usuario(String nome, String email, String senha,
			String dataDeNascimento) throws ParseException, EntradaException {

		UtilUsuario.validaNomeCadastro(nome);
		UtilUsuario.validaDataCadastro(dataDeNascimento);
		UtilUsuario.validaEmailCadastro(email);
		UtilUsuario.validaSenhaCadastro(senha);

		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = formataDataDeNascimento(dataDeNascimento);
		this.foto = "resources/default.jpg";
		this.posts = new ArrayList<Post>();
		this.amigos = new ArrayList<Usuario>();
		this.notificacoes = new Notificacao();
		this.solicitacoesDeAmizade = new ArrayList<Usuario>();
		this.popularidade = new Normal();
		this.pops = 0;
		this.popsMagico = 0;
	}

	/**
	 * Metodo utilizado para adicionar um novo post a lista de posts do Usuario;
	 * tambem armazenando as hashtags deste no atributo hashtagsDosPosts do
	 * usuario.
	 * 
	 * @param novoPost
	 */

	public void postar(Post novoPost) {
		this.posts.add(novoPost);

	}

	/**
	 * Metodo utilizado para adicionar uma notificacao as notificacoes do
	 * Usuario.
	 * 
	 * @param mensagem
	 */

	public void addNotificacao(String mensagem) {
		this.notificacoes.addNotificacao(mensagem);
	}

	/**
	 * Metodo utilizado para remover uma notificacao das notificacoes do
	 * Usuario.
	 * 
	 * @param notificacao
	 */

	public void removeNotificacao(String notificacao) {
		this.notificacoes.removeNotificacao(notificacao);
	}

	/**
	 * Metodo utilizado para formatar a data de nascimento.
	 * 
	 * @param data
	 * @return
	 * @throws ParseException
	 */

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

	/**
	 * Metodo utilizado para obter o email do usuario.
	 * 
	 * @return
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Metodo utilizado para alterar o email do usuario.
	 * 
	 * @param novoEmail
	 */

	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}

	/**
	 * Metodo utilizado para obter a senha do usuario.
	 * 
	 * @return
	 */

	public String getSenha() {
		return senha;
	}

	/**
	 * Metodo utilizado para alterar a senha do usuario.
	 * 
	 * @param novaSenha
	 */

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	/**
	 * Metodo utilizado para obter o nome de cadastro do usuario.
	 * 
	 * @return
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * Metodo utilizado para alterar o nome do usuario.
	 * 
	 * @param novoNome
	 */

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	/**
	 * Metodo utilizado para obter a data de nascimento formatada do usuario.
	 * 
	 * @return
	 */

	public String getDataDeNascimento() {
		DateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd");
		return dataFormatada.format(this.dataDeNascimento);
	}

	/**
	 * Metodo utilizado para alterar a data de nascimento do usuario.
	 * 
	 * @param novaDataDeNascimento
	 * @throws ParseException
	 */

	public void setDataDeNascimento(String novaDataDeNascimento)
			throws ParseException {
		this.dataDeNascimento = formataDataDeNascimento(novaDataDeNascimento);
	}

	/**
	 * Metodo utilizado para obter a foto do usuario.
	 * 
	 * @return
	 */

	public String getFoto() {
		return foto;
	}

	/**
	 * Metodo utilizado para alterar a foto do usuario.
	 * 
	 * @param novaFoto
	 */

	public void setFoto(String novaFoto) {
		this.foto = novaFoto;
	}

	/**
	 * Metodo utilizado para obter a lista de posts do usuario.
	 * 
	 * @return
	 */

	public List<Post> getPosts() {
		return posts;
	}

	/**
	 * Metodo utilizado para obter um determinado post dos posts do usuario.
	 * 
	 * @param indiceDoPost
	 * @return
	 */

	public Post getPostIndex(int indiceDoPost) {
		return this.posts.get(indiceDoPost);
	}

	/**
	 * Metodo utilizado para obter um determinado post formatado.
	 * 
	 * @param index
	 * @return
	 */

	public String getPostFormatado(int index) {
		return posts.get(index).getPostFormatado();
	}

	/**
	 * Metodo utilizado para obter a data de um determinado post do usuario.
	 * 
	 * @param indexPost
	 * @return
	 */

	public String getDataPost(int indexPost) {
		return this.posts.get(indexPost).getDataFormatada();
	}

	public int getPopsPost(int indexPost) {
		return this.posts.get(indexPost).getPopularidade();
	}
	
	public int getCurtidasDoPost(int indexPost) {
		return this.posts.get(indexPost).getCurtidas();
	}
	
	public int getRejeicoesDoPost(int indexPost) {
		return this.posts.get(indexPost).getRejeicoes();
	}
	
	/**
	 * Metodo utilizado para obter a mensagem do post, a mensagem eh igual ao
	 * texto do post juntamente com as midias.
	 * 
	 * @param indexPost
	 * @return
	 */

	public String getMensagemPost(int indexPost) {
		return this.posts.get(indexPost).getMensagem();
	}

	/**
	 * Metodo utilizado para obter as hashtags de um determinado post do
	 * usuario.
	 * 
	 * @param indexPost
	 * @return
	 */

	public String getHashtagPost(int indexPost) {
		return this.posts.get(indexPost).getHashtags();
	}

	/**
	 * Metodo utilizado para obter a lista de conteudo de um determinado post do
	 * usuario.
	 * 
	 * @param indexPost
	 * @return
	 */

	public List<String> getConteudoPost(int indexPost) {
		return this.posts.get(indexPost).getConteudoDoPost();
	}

	/**
	 * Metodo utilizado para obter o conteudo de um determinado post do usuario.
	 * 
	 * @param indexPost
	 * @param indiceDoConteudo
	 * @return
	 */

	public String getConteudoPost(int indexPost, int indiceDoConteudo) {
		return this.posts.get(indexPost).getConteudoDoPost(indiceDoConteudo);
	}

	/**
	 * Metodo utilizado para alterar a lista de posts do usuario.
	 * 
	 * @param posts
	 */

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	/**
	 * Metodo utilizado para obter a lista de amigos do usuario.
	 * 
	 * @return
	 */

	public List<Usuario> getAmigos() {
		return amigos;
	}

	/**
	 * Metodo utilizado para alterar a lista de amigos do usuario.
	 * 
	 * @param amigos
	 */

	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}

	/**
	 * Metodo utilizado para obter a lista de notificacoes do usuario.
	 * 
	 * @return
	 */

	public List<String> getNotificacoes() {
		return this.notificacoes.getNotificacoes();
	}

	/**
	 * Metodo utilizado para obter a lista de solicitacoes de amizade do
	 * usuario.
	 * 
	 * @return
	 */

	public List<Usuario> getSolicitacoesDeAmizade() {
		return solicitacoesDeAmizade;
	}

	/**
	 * Metodo utilizado para obter a popularidade do usuario.
	 * 
	 * @return
	 */

	public String getPopularidade() {
		return popularidade.toString();
	}

	/**
	 * Metodo utilizado para atualizar a popularidade.
	 * 
	 */

	public void atualizaPopularidade() {
		atualizaPops();
		if (this.pops <= 500) {
			this.popularidade = new Normal();
		} else if (this.pops > 500 && this.pops <= 1000) {
			this.popularidade = new CelebridadePOP();
		} else {
			this.popularidade = new IconePOP();
		}
	}

	/**
	 * Metodo utilizado para obtencao dos pops do usuario.
	 * 
	 * @return
	 */

	public int getPops() {
		return pops;
	}

	/**
	 * Metodo utilizado para alterar os pops do usuario.
	 * 
	 * @param pops
	 */

	public void setPops(int pops) {
		this.pops = pops;
	}
	
	/**
	 * Metodo utilizado para alterar os pops do usuario para fins de testes
	 * 
	 * @param pops
	 */

	public void setPopsMagico(int pops) {
		this.popsMagico += pops;
		atualizaPopularidade();
	}

	/**
	 * Metodo utilizado para adicionar pops aos pops do usuario.
	 * 
	 * @param popsAcumulados
	 */

	public void addPops(int popsAcumulados) {
		this.pops += popsAcumulados;
	}

	/**
	 * Metodo utilizado para atualizar os pops.
	 * 
	 */

	public void atualizaPops() {
		int popsAcumulados = this.popsMagico;
		for (Post post : posts) {
			popsAcumulados = popsAcumulados + post.getPopularidade();
		}
		this.pops = popsAcumulados;
	} 

	/**
	 * Metodo utilizado para alterar a data de nascimento do usuario.
	 * 
	 * @param dataDeNascimento
	 */

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	/**
	 * Metodo utilizado para alterar as notificacoes do usuario.
	 * 
	 * @param notificacoes
	 */

	private void setNotificacoes(Notificacao notificacoes) {
		this.notificacoes = notificacoes;
	}

	/**
	 * Metodo utilizado para alterar as solicitacoes de amizade do usuario.
	 * 
	 * @param solicitacoesDeAmizade
	 */

	private void setSolicitacoesDeAmizade(List<Usuario> solicitacoesDeAmizade) {
		this.solicitacoesDeAmizade = solicitacoesDeAmizade;
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

	/**
	 * Metodo utilizado para adicionar um amigo a lista de amigos.
	 */

	@Override
	public void aceitaAmigo(Usuario novoAmigo) {
		this.amigos.add(novoAmigo);
		removeSolicitacao(novoAmigo);
	}

	/**
	 * Metodo utilizado para remover um amigo da lista de amigos.
	 */

	@Override
	public void removeAmigo(Usuario amigoRemovido) {
		this.amigos.remove(amigoRemovido);
	}

	/**
	 * Metodo utilizado para adicionar uma nova solicitacao de amizade.
	 */

	@Override
	public void addSolicitacao(Usuario usuario) {
		this.solicitacoesDeAmizade.add(usuario);
	}

	/**
	 * Metodo utilizado para remover uma solicitacao de amizade.
	 */

	@Override
	public void removeSolicitacao(Usuario usuario) {
		this.solicitacoesDeAmizade.remove(usuario);
	}

	/**
	 * Metodo utilizado para verificar se o usuario informado eh amigo do
	 * usuario atual.
	 */

	@Override
	public Usuario buscaAmigo(String emailDoAmigo) {
		for (Usuario usuario : amigos) {
			if (usuario.getEmail().equals(emailDoAmigo)) {
				return usuario;
			}
		}
		return null;
	}

	// Beginning of Interaction

	/**
	 * Metodo utilizado para curtir um post.
	 * 
	 * @param post
	 */

	public void curtir(Post post) {
		this.atualizaPopularidade();
		this.popularidade.curtir(post);
		post.curtir();
	}

	/**
	 * Metodo utilizado para rejeitar um post.
	 * 
	 * @param post
	 */

	public void rejeitar(Post post) {
		this.popularidade.rejeitar(post);
		post.rejeitar();
		this.atualizaPopularidade();
	}

	@Override
	public int compareTo(Usuario novoUsuario) {

		if (this.pops > novoUsuario.getPops()) {
			return 1;
		} else if (this.pops == novoUsuario.getPops()) {
			return this.email.compareTo(novoUsuario.getEmail());
		} else {
			return -1;
		}

	}

}