package usuario;

import java.text.ParseException;

public class UsuarioFactory {

	public Usuario criaUsuario(String nome, String email, String senha, String dataDeNascimento, String foto) throws ParseException {
		Usuario novoUsuario = new Usuario(nome, email, senha, dataDeNascimento, foto);
		return novoUsuario;
	}
	
	public Usuario criaUsuario(String nome, String email, String senha, String dataDeNascimento) throws ParseException {
		Usuario novoUsuario = new Usuario(nome, email, senha, dataDeNascimento);
		return novoUsuario;
	}
	
}