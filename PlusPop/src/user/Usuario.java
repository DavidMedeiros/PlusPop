package user;

import interaction.CelebridadePOP;
import interaction.IconePOP;
import interaction.Interacao;
import interaction.Normal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import post.Feed;
import post.Post;
import util.UtilUsuario;
import exceptions.BaixarPostException;
import exceptions.EntradaException;
import exceptions.LogicaException;
import friendship.Friendship;
import friendship.Notificacao;

public class Usuario implements Friendship, Comparable<Usuario>, Serializable {

	private static final long serialVersionUID = 2318452896369602166L;
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
	private Feed feed;

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

		UtilUsuario.validaNome(nome);
		UtilUsuario.validaDataNascimento(dataDeNascimento);
		UtilUsuario.validaStringEmail(email);
		UtilUsuario.validaSenha(senha);
		UtilUsuario.validaFoto(foto);

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
		this.feed = new Feed();
	}

	public Usuario(String nome, String email, String senha,
			String dataDeNascimento) throws ParseException, EntradaException {

		UtilUsuario.validaNome(nome);
		UtilUsuario.validaDataNascimento(dataDeNascimento);
		UtilUsuario.validaStringEmail(email);
		UtilUsuario.validaSenha(senha);

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
		this.feed = new Feed();
	}

	/**
	 * Metodo utilizado para adicionar um novo post a lista de posts do Usuario;
	 * tambem armazenando as hashtags deste no atributo hashtagsDosPosts do
	 * usuario.
	 * 
	 * @param novoPost
	 * @return
	 * @throws EntradaException
	 */

	public Post criarPost(String mensagem, String data) throws EntradaException {
		Post novoPost = new Post(mensagem, data);
		this.posts.add(novoPost);
		return novoPost;

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
	 * @throws EntradaException
	 */

	public void setEmail(String novoEmail) throws EntradaException {
		UtilUsuario.validaStringEmail(novoEmail);
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
	 * @throws EntradaException
	 */

	public void setSenha(String novaSenha) throws EntradaException {
		UtilUsuario.validaSenha(novaSenha);
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
	 * @throws EntradaException
	 */

	public void setNome(String novoNome) throws EntradaException {
		UtilUsuario.validaNome(novoNome);
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
	 * @throws EntradaException
	 */

	public void setDataDeNascimento(String novaDataDeNascimento)
			throws ParseException, EntradaException {
		UtilUsuario.validaDataNascimento(novaDataDeNascimento);
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
	 * @throws EntradaException
	 */

	public void setFoto(String novaFoto) throws EntradaException {
		UtilUsuario.validaFoto(novaFoto);
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

	/**
	 * Metodo utilizado para obter a quantidade de pops de um post do usuario.
	 * 
	 * @param indexPost
	 * @return
	 */

	public int getPopsPost(int indexPost) {
		return this.posts.get(indexPost).getPopularidade();
	}

	/**
	 * Metodo utilizado para obter a quantidade de curtidas de um post do
	 * usuario.
	 * 
	 * @param indexPost
	 * @return
	 */

	public int getCurtidasDoPost(int indexPost) {
		return this.posts.get(indexPost).getCurtidas();
	}

	/**
	 * Metodo utilizado para obter a quantidade de rejeicoes de um post do
	 * usuario.
	 * 
	 * @param indexPost
	 * @return
	 */

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
		atualizaPopularidade();
	}

	/**
	 * Metodo utilizado para adicionar pops ao usuario para fins de testes.
	 * 
	 * @param pops
	 */

	public void adicionaPopsMagico(int pops) {
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

	// Implementacao de Friendship interface;

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

	// Implementacao de Interacao

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
		this.atualizaPopularidade();
		this.popularidade.rejeitar(post);
		post.rejeitar();
	}

	// Feed

	/**
	 * Metodo utilizado para ordenar os posts do usuario pela data de criacao.
	 * Da mais para a menos recente.
	 */

	public void ordenaPosts() {
		Collections.sort(this.posts);
	}

	/**
	 * Metodo utilizado para que o usuario forneca seus posts ao feed. De acordo
	 * com o tipo de popularidade, a quantidade de posts fornecidos ao feed eh
	 * alterada.
	 * 
	 * @return
	 */

	public List<Post> fornecePosts() {
		this.ordenaPosts();
		List<Post> postsARetornar = new ArrayList<Post>();
		int quantidadeDePostsPelaPopularidade = this.popularidade
				.quantidadeDePosts();

		for (int i = 0; i < quantidadeDePostsPelaPopularidade; i++) {
			postsARetornar.add(this.posts.get(i));
		}

		return postsARetornar;
	}

	/**
	 * Metodo utilizado para atualizar o feed.
	 */

	public void atualizaFeed() {
		for (Usuario amigo : this.amigos) {
			for (Post postDoAmigo : amigo.fornecePosts()) {
				this.feed.adicionaPostAoFeed(postDoAmigo);
			}
		}
	}

	/**
	 * Metodo utilizado para ordenar um post. De acordo com o parametro passado,
	 * a ordenacao eh realizada de forma diferente.
	 * 
	 * @param ordenacao
	 */

	public void ordenaFeed(String ordenacao) {
		if (ordenacao.equalsIgnoreCase("data")) {
			this.feed.ordenaPorData();
		} else if (ordenacao.equalsIgnoreCase("popularidade")) {
			this.feed.ordenaPorPopularidade();
		}
	}

	/**
	 * Metodo utilizado para obter a lista de posts do feed.
	 * 
	 * @return
	 */

	public List<Post> getFeed() {
		return this.feed.getPosts();
	}

	/**
	 * Metodo utilizado para fazer donwload dos posts do usuario para a memoria.
	 * 
	 * @throws LogicaException
	 */

	public void baixaPosts() throws LogicaException {

		if (this.posts.isEmpty()) {
			throw new BaixarPostException("O usuario nao possui posts.");
		}

		StringBuilder sb = new StringBuilder();
		String nomeDoArquivo = UtilUsuario
				.converteEmailParaNomeDeArquivo(this.email);

		File arquivo = new File(nomeDoArquivo);
		PrintWriter out;

		try {
			out = new PrintWriter(new FileWriter(arquivo));

			for (int i = 0; i < this.posts.size(); i++) {
				sb.append(posts.get(i).formataPostsParaSalvar(i + 1));
			}

			String postFormatado = sb.toString().trim();

			out.write(postFormatado);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo utilizado para obter a quantidade de posts do usuario.
	 * 
	 * @return
	 */

	public int getTotalPosts() {
		return this.posts.size();
	}

	/**
	 * Metodo utilizado para obter o toString do usuario, o to string eh
	 * retornado seguindo o padrao: Usuario: nome ; Email: email
	 * 
	 */

	@Override
	public String toString() {
		String EOL = System.getProperty("line.separator");
		String saida = "Usuario: " + this.nome + EOL + "Email: " + this.email;
		return saida;
	}

	/**
	 * Metodo HashCode
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/**
	 * Metodo utilizado para comparar se dois usuarios sao iguais.
	 */

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario outroUsuario = (Usuario) obj;
			return this.email.equals(outroUsuario.getEmail());
		}
		return false;
	}

	/**
	 * Metodo utilizado para comparar usuarios por pops, o email eh utilizado
	 * como criterio de desempate.
	 */

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