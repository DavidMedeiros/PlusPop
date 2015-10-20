package manager;

import interaction.Post;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import usuario.Usuario;
import usuario.UsuarioFactory;
import util.UtilUsuario;
import exceptions.AtualizacaoPerfilException;
import exceptions.CadastroDeUsuariosException;
import exceptions.EntradaException;
import exceptions.FecharSistemaException;
import exceptions.LogicaException;
import exceptions.LoginDeUsuariosException;
import exceptions.LogoutDeUsuariosException;
import exceptions.NotificacoesException;
import exceptions.SenhaProtegidaException;
import exceptions.UsuarioNaoEncontradoException;

public class SystemPop {

	private UsuarioFactory usuarioFactory;
	private Usuario usuarioLogado;
	private List<Usuario> usuariosCadastrados;

	/**
	 * Construtor da classe SystemPop. Classe essa que funciona como Controller,
	 * fazendo toda a comunicação com a façade;
	 *
	 */

	public SystemPop() {
		this.usuarioFactory = new UsuarioFactory();
		this.usuarioLogado = null;
		this.usuariosCadastrados = new ArrayList<Usuario>();
	}

	/**
	 * Metodo que sera utilizado para carregar/armazenar os dados do sistema nos
	 * arquivos de dados.
	 */

	public void iniciaSistema() {

	}

	/**
	 * Metodo utilizado para fechar o sistema, so eh possível fechar o sistema
	 * caso haja algum usuario logado.
	 * 
	 * @throws LogicaException
	 *             - Lançada quando não há usuario logado.
	 */

	public void fechaSistema() throws LogicaException {
		if (usuarioLogado == null) {
			// fechar sistema
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
	 */

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento, String foto) throws EntradaException,
			ParseException {

		Usuario novoUsuario = this.usuarioFactory.criaUsuario(nome, email,
				senha, dataDeNascimento, foto);

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
	 * uma foto padrão.
	 * 
	 * @param nome
	 * @param email
	 * @param senha
	 * @param dataDeNascimento
	 * @return
	 * @throws EntradaException
	 * @throws ParseException
	 */

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento) throws EntradaException, ParseException {

		Usuario novoUsuario = this.usuarioFactory.criaUsuario(nome, email,
				senha, dataDeNascimento);

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

		if (usuarioLogado == null) {
			throw new LogoutDeUsuariosException(
					"Nenhum usuarix esta logadx no +pop.");
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
		if (!verificaSeHaUsuarioLogado()) {
			throw new AtualizacaoPerfilException(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		} else if (atributo.equals("Nome")) {
			if (valor.equals("") || valor == null) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
			}
			usuarioLogado.setNome(valor);
		} else if (atributo.equals("E-mail")) {
			if (!UtilUsuario.validaEmails(valor)) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
			}
			usuarioLogado.setEmail(valor);
		} else if (atributo.equals("Data de Nascimento")) {
			if (!UtilUsuario.validaFormatoDeData(valor)) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Formato de data esta invalida.");
			}
			if (!UtilUsuario.dataEhValida(valor)) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Data nao existe.");
			}
			usuarioLogado.setDataDeNascimento(valor);
		} else if (atributo.equals("Foto")) {
			if (valor.equals("") || valor == null) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Foto dx usuarix nao pode ser vazia.");
			}
			usuarioLogado.setFoto(valor);
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
		if (!verificaSeHaUsuarioLogado()) {
			throw new AtualizacaoPerfilException(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}

		if (usuarioLogado.getSenha().equals(velhaSenha)) {
			usuarioLogado.setSenha(valor);
		} else {
			throw new AtualizacaoPerfilException(
					"Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
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
		if (usuarioLogado == null) {
			throw new LogicaException(
					"Nao eh possivel criar o post. Nenhum usuarix esta logadx no +pop.");
		}

		Post novoPost = new Post(mensagem, data);
		usuarioLogado.postar(novoPost);

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
	 */

	public boolean verificaSeHaUsuarioLogado() {
		if (usuarioLogado == null) {
			return false;
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
	 * Metodo setter do atributo usuariosCadastrados; Metodo com visibilidade
	 * private, pois nao sera usado, a priore;
	 * 
	 * @param usuariosCadastrados
	 */

	private void setUsuariosCadastrados(ArrayList<Usuario> usuariosCadastrados) {
		this.usuariosCadastrados = usuariosCadastrados;
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
			throw new LogicaException(
					"Requisicao invalida. O indice deve ser maior ou igual a zero.");
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
		this.usuarioLogado.curtir(amigo.getPostIndex(indice));
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
		this.usuarioLogado.rejeitar(amigo.getPostIndex(indice));
		amigo.addNotificacao(getMsgRejeita(amigo, indice));
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

	public String getNextNotificacao() throws NotificacoesException {

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

}