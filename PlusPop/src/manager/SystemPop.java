package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import post.Post;
import ranking.HashtagTrending;
import ranking.Trending;
import user.Usuario;
import user.UsuarioFactory;
import exceptions.AtualizaRankingException;
import exceptions.AtualizacaoPerfilException;
import exceptions.CadastroDeUsuariosException;
import exceptions.ConsultaDePopsException;
import exceptions.CriaPostException;
import exceptions.CurtePostException;
import exceptions.EntradaException;
import exceptions.FecharSistemaException;
import exceptions.LogicaException;
import exceptions.LoginDeUsuariosException;
import exceptions.LogoutDeUsuariosException;
import exceptions.NotificacoesException;
import exceptions.RejeitaPostException;
import exceptions.RequisicaoPostException;
import exceptions.SenhaProtegidaException;
import exceptions.UsuarioNaoEncontradoException;

public class SystemPop {

	private UsuarioFactory usuarioFactory;
	private Usuario usuarioLogado;
	private List<Usuario> usuariosCadastrados;
	private Trending trending;

	/**
	 * Construtor da classe SystemPop. Classe essa que funciona como Controller,
	 * fazendo toda a comunicacao com a facade;
	 *
	 */

	public SystemPop() {
		this.usuarioFactory = new UsuarioFactory();
		this.usuarioLogado = null;
		this.usuariosCadastrados = new ArrayList<Usuario>();
		this.trending = new Trending();
	}

	/**
	 * Metodo utilizado para carregar os dados do sistema presentes nos arquivos
	 * de dados.
	 */

	public void iniciaSistema() {
		File arquivo = new File("arquivos/usuariosCadastrados.dat");
		if (arquivo.exists()) {
			try {
				FileInputStream fluxo = new FileInputStream(arquivo);
				ObjectInputStream in = new ObjectInputStream(fluxo);
				this.usuariosCadastrados = (List<Usuario>) in.readObject();

				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Metodo utilizado para fechar o sistema, so eh possivel fechar o sistema
	 * caso haja algum usuario logado.
	 * 
	 * @throws LogicaException
	 *             - Lancadada quando nao ha usuario logado.
	 */

	public void fechaSistema() throws LogicaException {
		if (usuarioLogado == null) {
			File arquivo = new File("arquivos/usuariosCadastrados.dat");
			try {
				FileOutputStream fluxo = new FileOutputStream(arquivo);
				ObjectOutputStream out = new ObjectOutputStream(fluxo);

				out.writeObject(this.usuariosCadastrados);

				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new FecharSistemaException();
		}
	}

	/**
	 * Metodo utilizado para cadastro de um novo usuario no sistema.
	 * 
	 * @param nome
	 * @param email
	 * @param senha
	 * @param dataDeNascimento
	 * @param foto
	 * @return
	 * @throws EntradaException
	 * @throws ParseException
	 * @throws EntradaException
	 */

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento, String foto) throws ParseException,
			EntradaException {

		Usuario novoUsuario;

		try {
			novoUsuario = this.usuarioFactory.criaUsuario(nome, email, senha,
					dataDeNascimento, foto);
		} catch (EntradaException e) {
			throw new CadastroDeUsuariosException(e.getMessage());
		}

		if (!this.usuariosCadastrados.contains(novoUsuario)) {
			adicionarUsuario(novoUsuario);
			return novoUsuario.getEmail();
		} else {
			throw new CadastroDeUsuariosException(
					"O usuario ja esta cadastrado no sistema.");
		}
	}

	/**
	 * Metodo utilizado para cadastro de um novo usuario no sistema utilizando
	 * uma foto padrao.
	 * 
	 * @param nome
	 * @param email
	 * @param senha
	 * @param dataDeNascimento
	 * @return
	 * @throws EntradaException
	 * @throws ParseException
	 * @throws EntradaException
	 */

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento) throws ParseException, EntradaException {

		Usuario novoUsuario;

		try {
			novoUsuario = this.usuarioFactory.criaUsuario(nome, email, senha,
					dataDeNascimento);
		} catch (EntradaException e) {
			throw new CadastroDeUsuariosException(e.getMessage());
		}

		if (!this.usuariosCadastrados.contains(novoUsuario)) {
			adicionarUsuario(novoUsuario);
			return novoUsuario.getEmail();

		} else {
			throw new CadastroDeUsuariosException(
					"O usuario ja esta cadastrado no sistema.");
		}
	}

	/**
	 * Metodo utilizado para realizar login de usuario. O usuario necessita
	 * possuir cadastro para realizar login.
	 * 
	 * @param email
	 * @param senha
	 * @return
	 * @throws EntradaException
	 */

	public boolean login(String email, String senha) throws EntradaException {
		boolean usuarioEhCadastrado = false;

		if (!(usuarioLogado == null)) {
			throw new LoginDeUsuariosException("Um usuarix ja esta logadx: "
					+ usuarioLogado.getNome() + ".");
		}

		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				if (usuario.getSenha().equals(senha)) {
					this.usuarioLogado = usuario;
					usuarioEhCadastrado = true;
				} else {
					throw new LoginDeUsuariosException("Senha invalida.");
				}
			}
		}

		if (usuarioEhCadastrado) {
			return true;
		} else {
			throw new LoginDeUsuariosException("Um usuarix com email " + email
					+ " nao esta cadastradx.");
		}
	}

	/**
	 * 
	 * Metodo utilizado para logout de usuario no sistema. Eh necessario um
	 * usuario logado para realizar logout.
	 * 
	 * @return
	 * @throws EntradaException
	 */

	public boolean logout() throws EntradaException {

		try {
			verificaSeHaUsuarioLogado();
		} catch (Exception e) {
			throw new LogoutDeUsuariosException(e.getMessage());
		}

		this.usuarioLogado = null;

		return true;
	}

	/**
	 * Metodo utilizado para atualizar um perfil, ficando a criterio do usuario
	 * logado escolher o tipo de informacao a ser atualizada e o valor.
	 * 
	 * @param atributo
	 * @param valor
	 * @throws EntradaException
	 * @throws ParseException
	 */

	public void atualizaPerfil(String atributo, String valor)
			throws EntradaException, ParseException {

		atributo = atributo.toLowerCase();

		try {
			verificaSeHaUsuarioLogado();
		} catch (Exception e) {
			throw new AtualizacaoPerfilException(e.getMessage());
		}

		if (atributo.equals("nome")) {
			atualizaNomePerfil(valor);
		} else if (atributo.equals("e-mail")) {
			atualizaEmailPerfil(valor);
		} else if (atributo.equals("data de nascimento")) {
			atualizaDataPerfil(valor);
		} else if (atributo.equals("foto")) {
			atualizaFotoPerfil(valor);
		}
	}

	/**
	 * Metodo utilizado para atualizar a senha de um perfil, eh necessario
	 * informar a senha antiga.
	 * 
	 * @param atributo
	 * @param valor
	 * @param velhaSenha
	 * @throws EntradaException
	 */

	public void atualizaPerfil(String atributo, String valor, String velhaSenha)
			throws EntradaException {

		atributo = atributo.toLowerCase();

		try {
			verificaSeHaUsuarioLogado();
		} catch (Exception e) {
			throw new AtualizacaoPerfilException(e.getMessage());
		}

		if (atributo.equals("senha")) {
			atualizaSenhaPerfil(valor, velhaSenha);
		}
	}

	/**
	 * Metodo utilizado para atualizar o nome do perfil, afim de melhorar o
	 * expert.
	 * 
	 * @param valor
	 * @throws EntradaException
	 */

	private void atualizaNomePerfil(String valor) throws EntradaException {
		try {
			usuarioLogado.setNome(valor);
		} catch (Exception e) {
			throw new AtualizacaoPerfilException(e.getMessage());
		}
	}

	/**
	 * Metodo utilizado para atualizar o email do perfil, afim de melhorar o
	 * expert.
	 * 
	 * @param valor
	 * @throws EntradaException
	 */

	private void atualizaEmailPerfil(String valor) throws EntradaException {
		try {
			usuarioLogado.setEmail(valor);
		} catch (Exception e) {
			throw new AtualizacaoPerfilException(e.getMessage());
		}
	}

	/**
	 * Metodo utilizado para atualizar data do perfil, afim de melhorar o
	 * expert.
	 * 
	 * @param valor
	 * @throws EntradaException
	 * @throws ParseException
	 */

	private void atualizaDataPerfil(String valor) throws EntradaException,
			ParseException {

		try {
			usuarioLogado.setDataDeNascimento(valor);
		} catch (Exception e) {
			throw new AtualizacaoPerfilException(e.getMessage());
		}
	}

	/**
	 * Metodo utilizado para atualizar a foto do perfil, afim de melhorar o
	 * expert.
	 * 
	 * @param valor
	 * @throws EntradaException
	 */

	private void atualizaFotoPerfil(String valor) throws EntradaException {
		try {
			usuarioLogado.setFoto(valor);
		} catch (Exception e) {
			throw new AtualizacaoPerfilException(e.getMessage());
		}
	}

	/**
	 * Metodo utilizado para atualizar a senha do perfil, afim de melhorar o
	 * expert.
	 * 
	 * @param valor
	 * @param velhaSenha
	 * @throws EntradaException
	 */

	private void atualizaSenhaPerfil(String valor, String velhaSenha)
			throws EntradaException {

		if (usuarioLogado.getSenha().equals(velhaSenha)) {
			try {
				usuarioLogado.setSenha(valor);
			} catch (EntradaException e) {
				throw new AtualizacaoPerfilException(e.getMessage());
			}
		} else {
			throw new AtualizacaoPerfilException(
					"A senha fornecida esta incorreta.");
		}
	}

	/**
	 * Metodo utilizado para criar um post. O usuario deve passar uma mensagem
	 * que pode conter texto, midia e hashtags.
	 * 
	 * @param mensagem
	 * @param data
	 * @throws LogicaException
	 * @throws EntradaException
	 */

	public void criaPost(String mensagem, String data) throws LogicaException,
			EntradaException {

		try {
			verificaSeHaUsuarioLogado();
		} catch (Exception e) {
			throw new CriaPostException(e.getMessage());
		}

		Post novoPost = usuarioLogado.criarPost(mensagem, data);
		this.trending.addHashtagsDoPostAoTrending(novoPost);

	}

	/**
	 * Metodo utilizado para atualizar o trending tops de hashtags.
	 * 
	 * @param quantidadeTrends
	 * @return
	 * @throws LogicaException
	 */

	public String atualizaTrending(int quantidadeTrends) throws LogicaException {
		return this.trending.getTopHashtags(quantidadeTrends);
	}

	/**
	 * Metodo utilizado para obter o ranking de usuarios.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	private String getRankingDeUsuarios() throws LogicaException {
		return getMaisPops() + getMenosPops();
	}

	/**
	 * Metodo utilizado para atualizar o ranking de usuarios.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	public String atualizaRanking() throws LogicaException {
		return getRankingDeUsuarios();
	}

	/**
	 * Metodo utilizado para ordenar a lista de usuarios cadastrados, a partir
	 * da quantidade de pops.
	 */

	private void ordenaUsuarioCadastrados() {
		Collections.sort(this.usuariosCadastrados);
	}

	/**
	 * Metodo utilizado para obter os 3 usuarios mais populares da rede.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	private String getMaisPops() throws LogicaException {
		StringBuilder sb = new StringBuilder();
		ordenaUsuarioCadastrados();
		int ordem = 1;

		if (this.usuariosCadastrados.isEmpty()) {
			throw new AtualizaRankingException(
					"Nenhum usuarix esta cadastrado no +pop.");
		} else if (this.usuariosCadastrados.size() <= 3) {
			for (int i = this.usuariosCadastrados.size() - 1; i > -1; i--) {
				Usuario usuarioPop = this.usuariosCadastrados.get(i);
				sb.append("(" + ordem + ")" + " " + usuarioPop.toString() + " ");
				sb.append(usuarioPop.getPops());
				sb.append("; ");
				ordem++;
			}
		} else {
			for (int i = this.usuariosCadastrados.size() - 1; i > this.usuariosCadastrados
					.size() - 4; i--) {
				Usuario usuarioPop = this.usuariosCadastrados.get(i);
				sb.append("(" + ordem + ")" + " "
						+ usuarioPop.getNome().toString() + " ");
				sb.append(usuarioPop.getPops());
				sb.append("; ");
				ordem++;
			}
		}
		String saida = "Mais Populares: " + sb.substring(0, sb.length());
		return saida;
	}

	/**
	 * Metodo utilizado para obter os 3 usuarios menos populares da rede.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	private String getMenosPops() throws LogicaException {
		StringBuilder sb = new StringBuilder();
		ordenaUsuarioCadastrados();

		if (this.usuariosCadastrados.isEmpty()) {
			throw new AtualizaRankingException(
					"Nenhum usuarix esta cadastrado no +pop.");
		} else if (this.usuariosCadastrados.size() <= 3) {
			for (int i = 0; i < this.usuariosCadastrados.size(); i++) {
				Usuario usuarioPop = this.usuariosCadastrados.get(i);
				sb.append("(" + (i + 1) + ") "
						+ usuarioPop.getNome().toString() + " ");
				sb.append(usuarioPop.getPops());
				sb.append("; ");
			}
		} else {
			for (int i = 0; i < 3; i++) {
				Usuario usuarioPop = this.usuariosCadastrados.get(i);
				sb.append("(" + (i + 1) + ") "
						+ usuarioPop.getNome().toString() + " ");
				sb.append(usuarioPop.getPops());
				sb.append("; ");
			}
		}

		String saida = "| Menos Populares: " + sb.substring(0, sb.length() - 1);
		return saida;
	}

	/**
	 * Metodo utilizado para adicionar pops ao usuario logado.
	 * 
	 * @param pops
	 * @throws LogicaException
	 */

	public void adicionaPops(int pops) throws LogicaException {
		verificaSeHaUsuarioLogado();
		usuarioLogado.adicionaPopsMagico(pops);
	}

	/**
	 * Metodo utilizado para obter a popularidade do usuario logado. Ex: Icone
	 * Pop.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	public String getPopularidade() throws LogicaException {
		verificaSeHaUsuarioLogado();
		return usuarioLogado.getPopularidade();
	}

	/**
	 * Metodo utilizado para obter a quantidade de pops de um post.
	 * 
	 * @param post
	 * @return
	 * @throws LogicaException
	 */

	public int getPopsPost(int post) throws LogicaException {
		verificaSeHaUsuarioLogado();
		return usuarioLogado.getPopsPost(post);
	}

	/**
	 * Metodo utilizado para obter a popularidade de um usuario. Ex: Icone Pop.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	public int getPopsUsuario(String emailDoUsuario) throws LogicaException {
		if (!(usuarioLogado == null)) {
			throw new ConsultaDePopsException("Um usuarix ainda esta logadx.");
		} else {
			Usuario usuario = buscarUsuario(emailDoUsuario);
			return usuario.getPops();
		}
	}

	/**
	 * Metodo utilizado para obter a quantidade de pops de um usuario.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	public int getPopsUsuario() throws LogicaException {
		verificaSeHaUsuarioLogado();
		return usuarioLogado.getPops();
	}

	/**
	 * Metodo utilizado para obter a quantidade de curtidas de um post.
	 * 
	 * @param post
	 * @return
	 * @throws LogicaException
	 */

	public int getQtdCurtidasDoPost(int post) throws LogicaException {
		verificaSeHaUsuarioLogado();

		if (post < 0) {
			throw new RequisicaoPostException();
		} else if (post >= usuarioLogado.getPosts().size()) {
			throw new LogicaException("Post #" + post
					+ " nao existe. Usuarix possui apenas "
					+ usuarioLogado.getPosts().size() + " post(s).");
		} else {
			return usuarioLogado.getCurtidasDoPost(post);
		}
	}

	/**
	 * Metodo utilizado para obter a quantidade de rejeicoes de um post.
	 * 
	 * @param post
	 * @return
	 * @throws LogicaException
	 */

	public int getQtdRejeicoesDoPost(int post) throws LogicaException {
		verificaSeHaUsuarioLogado();

		if (post < 0) {
			throw new RequisicaoPostException();
		} else if (post >= usuarioLogado.getPosts().size()) {
			throw new LogicaException("Post #" + post
					+ " nao existe. Usuarix possui apenas "
					+ usuarioLogado.getPosts().size() + " post(s).");
		} else {
			return usuarioLogado.getRejeicoesDoPost(post);
		}
	}

	/**
	 * Metodo utilizado para verificar se o usuario com o email pesquisado esta
	 * cadastrado no sistema.
	 * 
	 * @param email
	 * @return
	 * @throws LogicaException
	 */

	public Usuario buscarUsuario(String email) throws LogicaException {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		throw new UsuarioNaoEncontradoException(email);
	}

	/**
	 * Metodo utilizado para remover um usuario dos usuarios cadastrados no
	 * sistema.
	 * 
	 * @param emailDoUsuario
	 * @return
	 * @throws LogicaException
	 */

	public boolean removeUsuario(String emailDoUsuario) throws LogicaException {
		Usuario usuarioParaRemocao = buscarUsuario(emailDoUsuario);
		usuariosCadastrados.remove(usuarioParaRemocao);

		return true;
	}

	/**
	 * Metodo utilizado para verificar se ha usuario logado no sistema.
	 * 
	 * @return
	 * @throws LogicaException
	 */

	public boolean verificaSeHaUsuarioLogado() throws LogicaException {

		if (usuarioLogado == null) {
			throw new LogicaException("Nenhum usuarix esta logadx no +pop.");
		}
		return true;
	}

	/**
	 * Metodo utilizado para obter a lista de usuarios cadastrados no sistema.
	 * 
	 * @return
	 */

	public List<Usuario> getUsuariosCadastrados() {
		return usuariosCadastrados;
	}

	/**
	 * Metodo utilizado para obter o usuario logado.
	 * 
	 * @return
	 */

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * Metodo utilizado para alterar o usuario logado.
	 * 
	 * @param usuarioLogado
	 */

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * Metodo utilizado para adicionar um usuario a lista de usuarios
	 * cadastrados no sistema.
	 * 
	 * @param novoUsuario
	 */

	public void adicionarUsuario(Usuario novoUsuario) {
		this.usuariosCadastrados.add(novoUsuario);
	}

	/**
	 * Metodo utilizado para obter informacoes do usuario logado.
	 * 
	 * @param atributo
	 * @return
	 * @throws LogicaException
	 */

	public String getInfoUsuario(String atributo) throws LogicaException {

		if (atributo.equals("Nome")) {
			return this.usuarioLogado.getNome();
		} else if (atributo.equals("Email")) {
			return this.usuarioLogado.getEmail();
		} else if (atributo.equals("Senha")) {
			throw new SenhaProtegidaException();
		} else if (atributo.equals("Data de Nascimento")) {
			return this.usuarioLogado.getDataDeNascimento();
		} else if (atributo.equals("Foto")) {
			return this.usuarioLogado.getFoto();
		} else {
			return null;
		}
	}

	/**
	 * Metodo utilizado para obter informacoes de algum usuario cadastrado no
	 * sistema.
	 * 
	 * @param atributo
	 * @return
	 * @throws LogicaException
	 */

	public String getInfoUsuario(String atributo, String email)
			throws LogicaException {

		Usuario usuario = buscarUsuario(email);

		if (atributo.equals("Nome")) {
			return usuario.getNome();
		} else if (atributo.equals("Email")) {
			return usuario.getEmail();
		} else if (atributo.equals("Senha")) {
			throw new SenhaProtegidaException();
		} else if (atributo.equals("Data de Nascimento")) {
			return usuario.getDataDeNascimento();
		} else if (atributo.equals("Foto")) {
			return usuario.getFoto();
		} else {
			return null;
		}
	}

	/**
	 * Metodo utilizado para obter algum post na lista de posts do usuario. O
	 * post vem no formato: mensagem + data formatada.
	 * 
	 * @param post
	 * @return
	 */

	public String getPost(int post) {
		return usuarioLogado.getPostFormatado(post);
	}

	/**
	 * Metodo utilizado para obter informacoes do post, como data, mensagem,
	 * hashtags...
	 * 
	 * @param atributo
	 * @param post
	 * @return
	 */

	public String getPost(String atributo, int post) {
		if (atributo.toLowerCase().equals("mensagem")) {
			return usuarioLogado.getMensagemPost(post);
		}

		if (atributo.toLowerCase().equals("data")) {
			return usuarioLogado.getDataPost(post);
		}

		if (atributo.toLowerCase().equals("hashtags")) {
			return usuarioLogado.getHashtagPost(post);
		}

		return null;
	}

	/**
	 * Metodo utilizado para obter o conteudo de um post,
	 * mensagem/midia/hashtags
	 * 
	 * @param indice
	 * @param post
	 * @return
	 * @throws LogicaException
	 */

	public String getConteudoPost(int indice, int post) throws LogicaException {

		List<String> conteudosDoPost = usuarioLogado.getConteudoPost(post);

		if (indice < 0) {
			throw new RequisicaoPostException();
		}

		if (indice >= conteudosDoPost.size()) {
			throw new LogicaException("Item #" + indice
					+ " nao existe nesse post, ele possui apenas "
					+ conteudosDoPost.size() + " itens distintos.");
		}

		return usuarioLogado.getConteudoPost(post, indice);
	}

	/**
	 * Metodo utilizado para curtir o post de algum amigo.
	 * 
	 * @param emailAmigo
	 * @param indice
	 * @throws LogicaException
	 */

	public void curtirPost(String emailAmigo, int indice)
			throws LogicaException {

		Usuario amigo = buscarUsuario(emailAmigo);

		if (usuarioLogado.buscaAmigo(emailAmigo) == null) {
			throw new CurtePostException("O usuario nao eh seu amigo.");
		}

		Post postDoAmigo = amigo.getPostIndex(indice);
		adicionaHashtagEpicAoTrending("#epicwin", postDoAmigo);
		usuarioLogado.curtir(postDoAmigo);
		amigo.atualizaPopularidade();
		amigo.addNotificacao(getMsgCurte(amigo, indice));
	}

	/**
	 * Metodo utilizado para melhorar o Expert, tendo como objetivo fornecer a
	 * mensagem formatada para a notificacao de post curtido.
	 * 
	 * @param amigo
	 * @param indice
	 * @return
	 */

	private String getMsgCurte(Usuario amigo, int indice) {
		String saida = usuarioLogado.getNome() + " curtiu seu post de "
				+ amigo.getDataPost(indice) + ".";
		return saida;
	}

	/**
	 * Metodo utilizado para rejeitar o post de algum amigo.
	 * 
	 * @param emailAmigo
	 * @param indice
	 * @throws LogicaException
	 */

	public void rejeitarPost(String emailAmigo, int indice)
			throws LogicaException {
		Usuario amigo = buscarUsuario(emailAmigo);

		if (usuarioLogado.buscaAmigo(emailAmigo) == null) {
			throw new RejeitaPostException("O usuario nao eh seu amigo.");
		}

		Post postDoAmigo = amigo.getPostIndex(indice);

		adicionaHashtagEpicAoTrending("#epicfail", postDoAmigo);
		usuarioLogado.rejeitar(postDoAmigo);
		amigo.atualizaPopularidade();
		amigo.addNotificacao(getMsgRejeita(amigo, indice));
	}

	/**
	 * Metodo utilizado para adicionar hashtag #epicWin ou #epicFail ao
	 * trending.
	 * 
	 * @param epic
	 * @param postDoAmigo
	 */

	private void adicionaHashtagEpicAoTrending(String epic, Post postDoAmigo) {
		if (this.usuarioLogado.getPopularidade().equals("Icone Pop")) {
			HashtagTrending hashtagEpic = new HashtagTrending(epic);
			if (!(postDoAmigo.temEpic(epic))) {
				trending.addHashtag(hashtagEpic);
			}
		}
	}

	/**
	 * Metodo utilizado para melhorar o Expert, tendo como objetivo fornecer a
	 * mensagem formatada para a notificacao de post rejeitado.
	 * 
	 * @param amigo
	 * @param indice
	 * @return
	 */

	private String getMsgRejeita(Usuario amigo, int indice) {
		String saida = usuarioLogado.getNome() + " rejeitou seu post de "
				+ amigo.getDataPost(indice) + ".";
		return saida;
	}

	/**
	 * Metodo utilizado para enviar uma solicitacao de amizade para um outro
	 * usuario cadastrado no sistema.
	 * 
	 * @param emailPossivelAmigo
	 * @throws LogicaException
	 */

	public void adicionaAmigo(String emailPossivelAmigo) throws LogicaException {
		Usuario possivelAmigo = buscarUsuario(emailPossivelAmigo);

		if (usuarioLogado.buscaAmigo(emailPossivelAmigo) != null) {
			throw new LogicaException("Um usuarix com email "
					+ emailPossivelAmigo + " ja eh seu amigo.");
		}

		possivelAmigo.addNotificacao(usuarioLogado.getNome()
				+ " quer sua amizade.");
		possivelAmigo.addSolicitacao(usuarioLogado);
	}

	/**
	 * Metodo utilizado para desfazer amizades.
	 * 
	 * @param emailAmigoExcluido
	 * @throws LogicaException
	 */

	public void removeAmigo(String emailAmigoExcluido) throws LogicaException {
		Usuario amigoExcluido = buscarUsuario(emailAmigoExcluido);

		if (usuarioLogado.buscaAmigo(emailAmigoExcluido) == null) {
			throw new LogicaException("Um usuarix com email "
					+ emailAmigoExcluido + " nao eh seu amigo.");
		}

		usuarioLogado.removeAmigo(amigoExcluido);
		amigoExcluido.removeAmigo(usuarioLogado);
		amigoExcluido.addNotificacao(usuarioLogado.getNome()
				+ " removeu a sua amizade.");
	}

	/**
	 * Metodo utilizado para aceitar uma solicitacao de amizade.
	 * 
	 * @param emailDoUsuarioAceito
	 * @throws LogicaException
	 */

	public void aceitaAmizade(String emailDoUsuarioAceito)
			throws LogicaException {
		Usuario usuarioAceito = buscarUsuario(emailDoUsuarioAceito);

		usuarioLogado.aceitaAmigo(usuarioAceito);
		usuarioAceito.aceitaAmigo(usuarioLogado);
		usuarioAceito.addNotificacao(usuarioLogado.getNome()
				+ " aceitou sua amizade.");
	}

	/**
	 * Metodo utilizado para rejeitar uma solicitaao de amizade.
	 * 
	 * @param emailDoUsuarioRejeitado
	 * @throws LogicaException
	 */

	public void rejeitaAmizade(String emailDoUsuarioRejeitado)
			throws LogicaException {
		Usuario usuarioRejeitado = buscarUsuario(emailDoUsuarioRejeitado);

		if (!usuarioLogado.getSolicitacoesDeAmizade()
				.contains(usuarioRejeitado)) {
			throw new LogicaException(usuarioRejeitado.getNome()
					+ " nao lhe enviou solicitacoes de amizade.");
		}

		usuarioRejeitado.addNotificacao(usuarioLogado.getNome()
				+ " rejeitou sua amizade.");
	}

	/**
	 * Metodo utilizado para obter a quantidade de notificacoes do usuario
	 * logado no sistema.
	 * 
	 * @return
	 */

	public int getNotificacoes() {
		return this.usuarioLogado.getNotificacoes().size();
	}

	/**
	 * Metodo utilizado para obter as notificacoes e remove-las.
	 * 
	 * @return
	 * @throws NotificacoesException
	 */

	public String getNextNotificacao() throws LogicaException {

		if (this.usuarioLogado.getNotificacoes().size() == 0) {
			throw new NotificacoesException();
		}

		String notificacao = usuarioLogado.getNotificacoes().get(0);
		usuarioLogado.getNotificacoes().remove(0);

		return notificacao;
	}

	/**
	 * Metodo utilizado para obter a quantidade de amigos.
	 * 
	 * @return
	 */

	public int getQtdAmigos() {
		return this.usuarioLogado.getAmigos().size();
	}

	/**
	 * Metodo utilizado para ordenar o feed, a ordenacao eh passada por
	 * parametro.
	 * 
	 * @param ordenacao
	 */

	public void ordenaFeed(String ordenacao) {
		this.usuarioLogado.ordenaFeed(ordenacao);
	}

	/**
	 * Metodo utilizado para obter o feed de noticias, atualizado por posts
	 * recentes.
	 * 
	 * @param indiceDoPost
	 * @return
	 */

	public Post getPostFeedNoticiasRecentes(int indiceDoPost) {
		ordenaFeed("Data");
		return this.usuarioLogado.getFeed().get(indiceDoPost);
	}

	/**
	 * Metodo utilizado para obter o feed de noticias, atualizado por
	 * popularidade dos posts.
	 * 
	 * @param indiceDoPost
	 * @return
	 */

	public Post getPostFeedNoticiasMaisPopulares(int indiceDoPost) {
		ordenaFeed("Popularidade");
		return this.usuarioLogado.getFeed().get(indiceDoPost);
	}

	/**
	 * Metodo utilizado para atualizar os posts do feed.
	 */

	public void atualizaFeed() {
		this.usuarioLogado.atualizaFeed();
	}

	/**
	 * Metodo utilizado para fazer o download de posts para o sistema.
	 * 
	 * @throws LogicaException
	 */

	public void baixaPosts() throws LogicaException {
		usuarioLogado.baixaPosts();
	}

	/**
	 * Metodo utilizado para obter a quantidade de posts do usuario logado.
	 * 
	 * @return
	 */

	public int getTotalPosts() {
		return usuarioLogado.getTotalPosts();
	}
}