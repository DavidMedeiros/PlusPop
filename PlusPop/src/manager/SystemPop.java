package manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usuario.Usuario;
import usuario.UsuarioFactory;
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
	 * Construtor da classe SystemPop; A classe contem os atributos:
	 * usuariosCadastrados, usuarioLogado e usuarioFactory; No construtor, o
	 * usuarioLogado eh inicializado como null, ja que inicialmente, nao ha
	 * usuario logado.
	 *
	 */

	public SystemPop() {
		this.usuarioFactory = new UsuarioFactory();
		this.usuarioLogado = null;
		this.usuariosCadastrados = new ArrayList<Usuario>();
	}

	public void iniciaSistema() {
		// Será utilizado para Carregar/armazenar os dados do sistema nos
		// arquivos de dados.
	}

	public void fechaSistema() throws LogicaException {
		if (usuarioLogado == null) {
			// fechar sistema
		} else {
			throw new FecharSistemaException();
		}
	}

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

	public boolean login(String email, String senha) throws EntradaException {
		boolean usuarioEhCadastrado = false;
		if (!(usuarioLogado == null)) {
			throw new LoginDeUsuariosException("Um usuarix ja esta logadx: "
					+ usuarioLogado.getNome() + ".");
		}

		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				if (usuario.getSenha().equals(senha)) {
					usuario.login();
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

	public boolean logout() throws EntradaException {

		if (usuarioLogado == null) {
			throw new LogoutDeUsuariosException(
					"Nenhum usuarix esta logadx no +pop.");
		}

		usuarioLogado.logout(); // TODO Nao entendi muito bem esses metodos de
								// login e logout no usuario.
		this.usuarioLogado = null;

		return true;
	}

	public void validaNomeCadastro(String nome) throws EntradaException {
		if (nome.equals("") || nome.trim().equals("") || nome == null) {
			throw new CadastroDeUsuariosException(
					"Nome dx usuarix nao pode ser vazio.");
		}
	}

	public void validaEmailCadastro(String email) throws EntradaException {
		if (!validaEmails(email)) {
			throw new CadastroDeUsuariosException(
					"Formato de e-mail esta invalido.");
		}
	}

	public void validaSenhaCadastro(String senha) throws EntradaException {
		if (senha.equals("") || senha.trim().equals("") || senha == null) {
			throw new CadastroDeUsuariosException(
					"Senha dx usuarix nao pode ser vazia.");
		}
	}

	public void validaDataCadastro(String dataDeNascimento)
			throws EntradaException {
		if (dataDeNascimento.equals("") || dataDeNascimento.trim().equals("")
				|| dataDeNascimento == null) {
			throw new CadastroDeUsuariosException("Data nao existe.");
		}

		if (!diaEhValido(dataDeNascimento)) {
			throw new CadastroDeUsuariosException(
					"Formato de data esta invalida.");
		}

		if (!dataEhValida(dataDeNascimento)) {
			throw new CadastroDeUsuariosException("Data nao existe.");
		}
	}

	private boolean validaEmails(String email) {
		if (email.equals("") || email.trim().equals("") || email == null) {
			return false;
		}

		Pattern pattern = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher emailFiltrado = pattern.matcher(email);

		if (emailFiltrado.find()) {
			return true;
		}

		return false;
	}

	private boolean validaFormatoDeData(String data) {
		if (!data.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			return false;
		}

		String[] valores = data.split("/");
		int dia = Integer.parseInt(valores[0]);
		int mes = Integer.parseInt(valores[1]);
		int ano = Integer.parseInt(valores[2]);
		;

		if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1) {
			return false;
		}

		return true;
	}

	private boolean dataEhValida(String dataStr) {
		DateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
		dataFormatter.setLenient(false);
		try {
			Date date = dataFormatter.parse(dataStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private boolean diaEhValido(String dataStr) {
		String dia = dataStr.split("/")[0];

		if (dia.length() > 2)
			return false;

		return true;
	}

	public void atualizaPerfil(String atributo, String valor)
			throws EntradaException, ParseException {
		if (!verificaSeHaUsuarioLogado()) {
			throw new AtualizacaoPerfilException(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}

		if (atributo.equals("Nome")) {
			if (valor.equals("") || valor == null) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
			}
			usuarioLogado.setNome(valor);
		}

		if (atributo.equals("E-mail")) {
			if (!validaEmails(valor)) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
			}
			usuarioLogado.setEmail(valor);
		}

		if (atributo.equals("Data de Nascimento")) {
			if (!validaFormatoDeData(valor)) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Formato de data esta invalida.");
			}
			if (!dataEhValida(valor)) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Data nao existe.");
			}
			usuarioLogado.setDataDeNascimento(valor);
		}

		if (atributo.equals("Foto")) {
			if (valor.equals("") || valor == null) {
				throw new AtualizacaoPerfilException(
						"Erro na atualizacao de perfil. Foto dx usuarix nao pode ser vazia.");
			}
			usuarioLogado.setFoto(valor);
		}
	}

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

	public void criaPost(String mensagem, String data) throws LogicaException,
			EntradaException {
		if (usuarioLogado == null) {
			throw new LogicaException(
					"Nao eh possivel criar o post. Nenhum usuarix esta logadx no +pop.");
		}

		Post novoPost = new Post(mensagem, data);
		usuarioLogado.postar(novoPost);

	}

	public Usuario buscarUsuario(String email) throws LogicaException {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		throw new UsuarioNaoEncontradoException(email);
	}

	public boolean removeUsuario(String emailDoUsuario) throws LogicaException {
		Usuario usuarioParaRemocao = buscarUsuario(emailDoUsuario);
		usuariosCadastrados.remove(usuarioParaRemocao);

		return true;
	}

	public boolean verificaSeHaUsuarioLogado() {
		if (usuarioLogado == null) {
			return false;
		}
		return true;
	}

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

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void adicionarUsuario(Usuario novoUsuario) {
		this.usuariosCadastrados.add(novoUsuario);
	}

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

	public String getPost(int post) {
		return usuarioLogado.getPosts().get(post).getPostFormatado();
	}

	public String getPost(String atributo, int post) {
		if (atributo.toLowerCase().equals("mensagem")) {
			return usuarioLogado.getPosts().get(post).getMensagem();
		}

		if (atributo.toLowerCase().equals("data")) {
			return usuarioLogado.getPosts().get(post).getData();
		}

		if (atributo.toLowerCase().equals("hashtags")) {
			return usuarioLogado.getPosts().get(post).getHashtags();
		}

		return null;
	}

	public String getConteudoPost(int indice, int post) throws LogicaException {

		List<String> conteudosDoPost = usuarioLogado.getPosts().get(post)
				.getConteudoDoPost();

		if (indice < 0) {
			throw new LogicaException(
					"Requisicao invalida. O indice deve ser maior ou igual a zero.");
		}

		if (indice >= conteudosDoPost.size()) {
			throw new LogicaException("Item #" + indice
					+ " nao existe nesse post, ele possui apenas "
					+ conteudosDoPost.size() + " itens distintos.");
		}

		return usuarioLogado.getPosts().get(post).getConteudoDoPost(indice);
	}

	public void curtirPost(String emailAmigo, int indice)
			throws LogicaException {
		Usuario amigo = buscarUsuario(emailAmigo);
		amigo.getPosts().get(indice).curtir();
		amigo.addNotificacao(usuarioLogado.getNome() + " curtiu seu post de "
				+ amigo.getPosts().get(indice).getData() + ".");
	}

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

	public void aceitaAmizade(String emailDoUsuarioAceito)
			throws LogicaException {
		Usuario usuarioAceito = buscarUsuario(emailDoUsuarioAceito);

		usuarioLogado.aceitaAmigo(usuarioAceito);
		usuarioAceito.aceitaAmigo(usuarioLogado);
		usuarioAceito.addNotificacao(usuarioLogado.getNome()
				+ " aceitou sua amizade.");
		// Necessário remover solicitação de amizade?
	}

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

	public int getNotificacoes() {
		return this.usuarioLogado.getNotificacoes().size();
	}

	public String getNextNotificacao() throws NotificacoesException {

		if (this.usuarioLogado.getNotificacoes().size() == 0) {
			throw new NotificacoesException();
		}

		String notificacao = usuarioLogado.getNotificacoes().get(0);
		usuarioLogado.getNotificacoes().remove(0);

		return notificacao;
	}

	public int getQtdAmigos() {
		return this.usuarioLogado.getAmigos().size();
	}

}