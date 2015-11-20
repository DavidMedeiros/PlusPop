package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CadastroDeUsuariosException;
import exceptions.EntradaException;

public class UtilUsuario {

	/**
	 * Metodo utilizado para validar algum nome.
	 * 
	 * @param nome
	 * @throws EntradaException
	 */

	public static void validaNome(String nome) throws EntradaException {
		if (nome == null || nome.equals("") || nome.trim().equals("")) {
			throw new EntradaException("Nome dx usuarix nao pode ser vazio.");
		}
	}

	/**
	 * 
	 * Metodo utilizado para validar emails.
	 * 
	 * @param email
	 * @throws EntradaException
	 */

	public static void validaStringEmail(String email)
			throws EntradaException {
		if (!validaEmails(email)) {
			throw new EntradaException("Formato de e-mail esta invalido.");
		}
	}

	/**
	 * Metodo utilizado para validar uma senha.
	 * 
	 * @param senha
	 * @throws EntradaException
	 */

	public static void validaSenha(String senha)
			throws EntradaException {
		if (senha == null || senha.equals("") || senha.trim().equals("")) {
			throw new EntradaException("Senha dx usuarix nao pode ser vazia.");
		}
	}

	/**
	 * Metodo utilizado para validar uma data de nascimento. 
	 * 
	 * @param dataDeNascimento
	 * @throws EntradaException
	 */

	public static void validaDataNascimento(String dataDeNascimento)
			throws EntradaException {
		
		
		if (dataDeNascimento == null || dataDeNascimento.equals("")
				|| dataDeNascimento.trim().equals("")) {
			throw new EntradaException("Data nao existe.");
		}

		if (!validaFormatoDeData(dataDeNascimento)) {
			throw new EntradaException("Formato de data esta invalida.");
		}

		if (!dataEhValida(dataDeNascimento)) {
			throw new EntradaException("Data nao existe.");
		}
	}

	/**
	 * Metodo utilizado para validar emails, encapsulado para melhorar a legibilidade.
	 * 
	 * @param email
	 * @return
	 */

	public static boolean validaEmails(String email) {
		if (email == null || email.equals("") || email.trim().equals("")) {
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

	/**
	 * Metodo utilizado para verifiar se uma foto eh valida.
	 * 
	 * @param foto
	 * @throws EntradaException
	 */

	public static void validaFoto(String foto) throws EntradaException {
		if (foto == null || foto.equals("") || foto.trim().equals("")) {
			throw new EntradaException("Foto dx usuarix nao pode ser vazia.");
		}
	}

	/**
	 * Metodo utilizado para verificar se uma data eh valida.
	 * 
	 * @param dataStr
	 * @return
	 */

	public static boolean dataEhValida(String dataStr) {
		DateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
		dataFormatter.setLenient(false);
		try {
			Date date = dataFormatter.parse(dataStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Metodo utilizado para verificar se o formato de data eh valido.
	 * 
	 * @param data
	 * @return
	 */

	public static boolean validaFormatoDeData(String data) {
		if (!data.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Metodo utilizado para converter um email para um formato de nome de arquivo.
	 * ex: email: david@gmail.com  -> ./arquivos/posts_david[at]gmailcom.txt
	 * @param email
	 * @return
	 */
	
	public static String converteEmailParaNomeDeArquivo(String email) {
		StringBuilder sb = new StringBuilder();
		
		String emailToString = email.replace("@", "[at]").replace(".", "");
		sb.append("./arquivos/posts_" + emailToString + ".txt");

		return sb.toString();
	}
}