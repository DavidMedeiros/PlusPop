package usuario;

import java.text.ParseException;

import exceptions.EntradaException;

public class UsuarioFactory {

	/**
	 * A classe UsuarioFactory tem como objetivo criar usuarios.
	 * 
	 * @param nome
	 * @param email
	 * @param senha
	 * @param dataDeNascimento
	 * @param foto
	 * @return
	 * @throws ParseException
	 * @throws EntradaException
	 */

	public Usuario criaUsuario(String nome, String email, String senha,
			String dataDeNascimento, String foto) throws ParseException,
			EntradaException {
		Usuario novoUsuario = new Usuario(nome, email, senha, dataDeNascimento,
				foto);
		return novoUsuario;
	}

	/**
	 * Metodo utilizado para criar um usuario quando nao eh passado uma foto.
	 * Sendo assim, a foto padrao do sistema sera utilizada.
	 * 
	 * @param nome
	 * @param email
	 * @param senha
	 * @param dataDeNascimento
	 * @return
	 * @throws ParseException
	 * @throws EntradaException
	 */

	public Usuario criaUsuario(String nome, String email, String senha,
			String dataDeNascimento) throws ParseException, EntradaException {
		Usuario novoUsuario = new Usuario(nome, email, senha, dataDeNascimento);
		return novoUsuario;
	}

}