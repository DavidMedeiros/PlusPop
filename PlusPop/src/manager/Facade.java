package manager;

import java.text.ParseException;

import usuario.Usuario;
import easyaccept.EasyAccept;
import exceptions.EntradaException;
import exceptions.LogicaException;

public class Facade {

	private SystemPop systemPop;

	public Facade() {
		this.systemPop = new SystemPop();
	}

	public void iniciaSistema() {
		this.systemPop.iniciaSistema();
	}

	public void fechaSistema() throws LogicaException {
		this.systemPop.fechaSistema();
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento, String foto) throws EntradaException,
			ParseException {

		validaNomeCadastro(nome);
		validaDataCadastro(dataDeNascimento);
		validaEmailCadastro(email);
		validaSenhaCadastro(senha);

		return this.systemPop.cadastraUsuario(nome, email, senha,
				dataDeNascimento, foto);
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataDeNascimento) throws EntradaException, ParseException {

		validaNomeCadastro(nome);
		validaDataCadastro(dataDeNascimento);
		validaEmailCadastro(email);
		validaSenhaCadastro(senha);

		return this.systemPop.cadastraUsuario(nome, email, senha,
				dataDeNascimento);
	}

	public boolean login(String email, String senha) throws EntradaException {
		return this.systemPop.login(email, senha);
	}

	public boolean logout() throws EntradaException {
		return this.systemPop.logout();
	}

	private void validaNomeCadastro(String nome) throws EntradaException {
		this.systemPop.validaNomeCadastro(nome);
	}

	private void validaEmailCadastro(String email) throws EntradaException {
		this.systemPop.validaEmailCadastro(email);
	}

	private void validaSenhaCadastro(String senha) throws EntradaException {
		this.systemPop.validaSenhaCadastro(senha);
	}

	private void validaDataCadastro(String dataDeNascimento)
			throws EntradaException {
		this.systemPop.validaDataCadastro(dataDeNascimento);
	}

	public void atualizaPerfil(String atributo, String valor)
			throws EntradaException, ParseException {
		systemPop.atualizaPerfil(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha)
			throws EntradaException {
		systemPop.atualizaPerfil(atributo, valor, velhaSenha);
	}

	public void criaPost(String mensagem, String data) throws EntradaException,
			LogicaException {
		systemPop.criaPost(mensagem, data);
	}

	public Usuario buscarUsuario(String email) throws LogicaException {
		return this.systemPop.buscarUsuario(email);
	}

	public boolean removeUsuario(String emailDoUsuario) throws LogicaException {
		return this.systemPop.removeUsuario(emailDoUsuario);
	}
	
	public String getInfoUsuario(String atributo) throws LogicaException {
		return this.systemPop.getInfoUsuario(atributo);
	}

	public String getInfoUsuario(String atributo, String email)
			throws LogicaException {
		return this.systemPop.getInfoUsuario(atributo, email);
	}

	public String getPost(int post) {
		return this.systemPop.getPost(post);
	}

	public String getPost(String atributo, int post) {
		return this.systemPop.getPost(atributo, post);
	}
	
	public String getConteudoPost(int indice, int post) throws LogicaException {
		return this.systemPop.getConteudoPost(indice, post);
	}

	public static void main(String[] args) {
		args = new String[] { "manager.Facade",
				"teste_aceitacao/usecase_1.txt",
				"teste_aceitacao/usecase_2.txt",
				"teste_aceitacao/usecase_3.txt" };

		EasyAccept.main(args);
	}

}