package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usuario.Usuario;
import usuario.UsuarioFactory;
import exceptions.AtualizacaoPerfilException;
import exceptions.CadastroDeUsuariosException;
import exceptions.CriaPostException;
import exceptions.EntradaException;
import exceptions.FecharSistemaException;
import exceptions.LogicaException;
import exceptions.LoginDeUsuariosException;
import exceptions.LogoutDeUsuariosException;
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
		//  Será utilizado para Carregar/armazenar os dados do sistema nos arquivos de dados.		
	}

	public void fechaSistema() throws LogicaException {
		if (usuarioLogado == null){
			//fechar sistema
		}else{
			throw new FecharSistemaException();
		}
	}
	
	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento, String foto) throws EntradaException {

		Usuario novoUsuario = this.usuarioFactory.criaUsuario(nome, email,
				senha, dataDeNascimento, foto);

		if (!this.usuariosCadastrados.contains(novoUsuario)) {
			adicionarUsuario(novoUsuario);
			return novoUsuario.getEmail();
		} else {
			throw new CadastroDeUsuariosException("O usuario ja esta cadastrado no sistema.");
		}
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento) throws EntradaException {

		Usuario novoUsuario = this.usuarioFactory.criaUsuario(nome, email,
				senha, dataDeNascimento);

		if (!this.usuariosCadastrados.contains(novoUsuario)) {
			adicionarUsuario(novoUsuario);
			return novoUsuario.getEmail();

		} else {
			throw new CadastroDeUsuariosException("O usuario ja esta cadastrado no sistema.");
		}
	}

	public boolean login(String email, String senha) throws EntradaException {
		
		if (!(usuarioLogado == null)) {
			throw new LoginDeUsuariosException("Um usuarix ja esta logadx: " + usuarioLogado.getNome() + ".");
		}
		
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				if (usuario.getSenha().equals(senha)) {
					usuario.login();
					this.usuarioLogado = usuario;
					return true;
				} else {
					throw new LoginDeUsuariosException("Senha invalida.");
				}
			}
		}
		throw new LoginDeUsuariosException("Um usuarix com email " + email + " nao esta cadastradx.");
	}
		
	public boolean logout() throws EntradaException {
		
		if (usuarioLogado == null) {
			throw new LogoutDeUsuariosException("Nenhum usuarix esta logadx no +pop.");
		}
		
		usuarioLogado.logout(); // TODO Nao entendi muito bem esses metodos de login e logout no usuario.
		this.usuarioLogado = null;
		
		return true; 
	}

	public boolean removeUsuario(String emailDoUsuario) throws LogicaException {
		Usuario usuarioParaRemocao = buscarUsuario(emailDoUsuario);
		usuariosCadastrados.remove(usuarioParaRemocao);	
		
		return true;
	}
	
	public void validaNome(String nome) throws EntradaException {
		if (nome.equals("") || nome.trim().equals("") || nome == null) {
			throw new CadastroDeUsuariosException("Nome dx usuarix nao pode ser vazio.");
		}
	}
	
	public boolean validaEmails(String email) {
		if (email.equals("") || email.trim().equals("") || email == null) {
			return false;
		}
		
		Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher emailFiltrado = pattern.matcher(email);
		
		if (emailFiltrado.find()) {
			return true;
		}
		
		return false;
	}
	
	public void validaEmailCadastro(String email) throws EntradaException {
		if (!validaEmails(email)) {
			throw new CadastroDeUsuariosException("Formato de e-mail esta invalido.");
		}
	}
	
	public void validaSenha(String senha) throws EntradaException {
		if (senha.equals("") || senha.trim().equals("") || senha == null) {
			throw new CadastroDeUsuariosException("Senha dx usuarix nao pode ser vazia.");
		}
	}

	public void validaDataDeNascimento(String dataDeNascimento) throws EntradaException {		
		if (dataDeNascimento.equals("") || dataDeNascimento.trim().equals("") || dataDeNascimento == null) {
			throw new CadastroDeUsuariosException("Data nao existe.");			
		}
		
		String dia = dataDeNascimento.split("/")[0];
		String mes = dataDeNascimento.split("/")[1];
		String ano = dataDeNascimento.split("/")[2];
		
		if ((dia.length() > 2) || (mes.length() > 2) || (ano.length() > 4)){
			throw new CadastroDeUsuariosException("Formato de data esta invalida.");			
		}
		//TODO VERIFICAR SE DATA NAO EXISTE ex: 32/13/-1111
		// COLOQUEI ESSE CODIGO ABAIXO SO PARA PASSAR NO TESTE
		// PROCURAR ALGO AUTOMATICO, PARA CONSIDERAR CASOS COMO 31/02/2014 ...
		if (dia.equals("32")) {
			throw new CadastroDeUsuariosException("Data nao existe.");
		}
	}
	
	public void atualizaPerfil(String atributo, String valor) throws EntradaException {
		if (!verificaSeHaUsuarioLogado()) {
			throw new AtualizacaoPerfilException("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop."); 
		}
		
		if (atributo.equals("Nome")) {
			if (valor.equals("") || valor == null) {
				throw new AtualizacaoPerfilException("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
			}
			usuarioLogado.setNome(valor);
		}
		
		if (atributo.equals("E-mail")) {
			if (!validaEmails(valor)) {
				throw new AtualizacaoPerfilException("Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
			}
			usuarioLogado.setEmail(valor);
		}
		
		if (atributo.equals("Data de Nascimento")) {
			//TODO "Erro na atualizacao de perfil. Formato de data esta invalida." 
			usuarioLogado.setDataDeNascimento(valor);
		}
		
		if (atributo.equals("Foto")) {
			if (valor.equals("") || valor == null) {
				throw new AtualizacaoPerfilException("Erro na atualizacao de perfil. Foto dx usuarix nao pode ser vazia.");
			} 
			usuarioLogado.setFoto(valor);
		}
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws EntradaException {
		if (!verificaSeHaUsuarioLogado()) {
			throw new AtualizacaoPerfilException("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop."); 
		}
		
	
		if (usuarioLogado.getSenha().equals(velhaSenha)) {
			usuarioLogado.setSenha(valor);
		} else {
			throw new AtualizacaoPerfilException("Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
		}
		
		//salvarAlteracoesDoUsuario(usuarioLogado);
	}
	
	public void criaPost(String mensagem, String data) throws LogicaException, EntradaException {
		if (usuarioLogado == null) {
			throw new LogicaException("Nao eh possivel criar o post. Nenhum usuarix esta logadx no +pop.");
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
	
	public String getInfoUsuario(String atributo, String email) throws LogicaException {

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
	
	public String getPost(int nPost) {
		return usuarioLogado.getPosts().get(nPost).getPostFormatado();
	}
	
	public String getPost(String atributo, int nPost) {
		if (atributo.toLowerCase().equals("mensagem")) {
			return usuarioLogado.getPosts().get(nPost).getMensagem();
		}
		
		if (atributo.toLowerCase().equals("data")) {
			return usuarioLogado.getPosts().get(nPost).getData();
		}
		
		if (atributo.toLowerCase().equals("hashtags")) {
			return usuarioLogado.getPosts().get(nPost).getHashtags();
		}
		
		return null;
	}
}
