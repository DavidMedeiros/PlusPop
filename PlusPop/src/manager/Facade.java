package manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usuario.Usuario;
import easyaccept.EasyAccept;
import exceptions.CadastroDeUsuariosException;
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

	public String cadastraUsuario(String nome, String email, String senha, String dataDeNascimento, String foto) throws EntradaException {

		validaNome(nome);
		validaDataDeNascimento(dataDeNascimento);
		validaEmailCadastro(email);
		validaSenha(senha);

		return this.systemPop.cadastraUsuario(nome, email, senha, dataDeNascimento, foto);
	}
	
	public String cadastraUsuario(String nome, String email, String senha,	String dataDeNascimento) throws EntradaException {

		validaNome(nome);
		validaDataDeNascimento(dataDeNascimento);
		validaEmailCadastro(email);
		validaSenha(senha);
		
		return this.systemPop.cadastraUsuario(nome, email, senha, dataDeNascimento);
	}
	
	public boolean login(String email, String senha) throws EntradaException {
		return this.systemPop.login(email, senha);
	}

	public boolean logout() throws EntradaException {
		return this.systemPop.logout();
	}
	
	public boolean removeUsuario(String emailDoUsuario) throws LogicaException {
		return this.systemPop.removeUsuario(emailDoUsuario);
	}

	public void validaNome(String nome) throws EntradaException {
		this.systemPop.validaNome(nome);
	}
	
	public void validaEmailCadastro(String email) throws EntradaException {
		this.systemPop.validaEmailCadastro(email);
	}
	
	private void validaSenha(String senha) throws EntradaException {
		this.systemPop.validaSenha(senha);
	}
	
	private void validaDataDeNascimento(String dataDeNascimento) throws EntradaException {
		this.systemPop.validaDataDeNascimento(dataDeNascimento);
	}

	public void atualizaPerfil(String atributo, String valor) throws EntradaException {
		systemPop.atualizaPerfil(atributo, valor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws EntradaException {
		systemPop.atualizaPerfil(atributo, valor, velhaSenha);
	}

	public void criaPost(String mensagem, String data) throws EntradaException, LogicaException {
		systemPop.criaPost(mensagem, data);
	}
	
	public Usuario buscarUsuario(String email) throws LogicaException {
		return this.systemPop.buscarUsuario(email);
	}
	
	public String getInfoUsuario(String atributo) throws LogicaException {
		return this.systemPop.getInfoUsuario(atributo);
	}
	
	public String getInfoUsuario(String atributo, String email) throws LogicaException {
		return this.systemPop.getInfoUsuario(atributo, email);
	}
	
	public static void main(String[] args) {
	 	args = new String[] {"manager.Facade", "teste_aceitacao/usecase_1.txt", "teste_aceitacao/usecase_2.txt", "teste_aceitacao/usecase_3.txt"};

	 	EasyAccept.main(args);
	 }
	
}