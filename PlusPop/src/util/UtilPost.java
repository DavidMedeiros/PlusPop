package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import media.Audio;
import media.Imagem;
import media.MidiaPost;
import media.Video;
import exceptions.CriaPostException;
import exceptions.EntradaException;

public class UtilPost {

	/**
	 * Metodo utilizado para filtrar o texto da mensagem passada e adiciona-lo
	 * ao conteudo do post, assim, eliminando midias e hashtags.
	 * 
	 * @param mensagem
	 * @param conteudoDoPost
	 */

	public static String filtraTexto(String mensagem) {
		String[] palavras = mensagem.split(" ");
		String textoFiltrado = "";

		for (String palavra : palavras) {
			if (!(palavra.startsWith("#") || (palavra.startsWith("<") && palavra
					.endsWith(">")))) {
				textoFiltrado = textoFiltrado + palavra + " ";
			}
		}
		if (textoFiltrado.equals(""))
			return "";
		return textoFiltrado.substring(0, textoFiltrado.length() - 1);
	}

	/**
	 * Metodo utilizado para filtrar as hashtags da mensagem passada e
	 * adiciona-la ao conteudo do post, assim, eliminando midias e textos.
	 * 
	 * @param mensagem
	 * @param listaDeHashtags
	 */

	public static void filtraHashtags(String mensagem,
			List<String> listaDeHashtags) {
		String[] palavras = mensagem.split(" ");
		for (String palavra : palavras) {
			if (palavra.startsWith("#")) {
				listaDeHashtags.add(palavra);
			}
		}
	}

	/**
	 * Metodo utilizado para filtrar a midia da mensagem passada e adiciona-la
	 * ao conteudo do post, assim, eliminando texto e hashtags.
	 * 
	 * @param mensagem
	 * @param conteudoDoPost
	 */

	public static void filtraMidia(String mensagem, List<MidiaPost> conteudoDoPost) {
		String[] palavras = mensagem.split(" ");

		for (String palavra : palavras) {
			if (palavra.startsWith("<audio>")) {
				MidiaPost midia = new Audio(palavra);
				conteudoDoPost.add(midia);
			} else if (palavra.startsWith("<imagem>")) {
				MidiaPost midia = new Imagem(palavra);
				conteudoDoPost.add(midia);
			} else if (palavra.startsWith("<video>")) {
				MidiaPost midia = new Video(palavra);
				conteudoDoPost.add(midia);
			}
		}
	}

	/**
	 * Metodo utilizado para validar uma mensagem passada.
	 * 
	 * @param mensagem
	 * @throws EntradaException
	 */

	public static void validaMensagem(String mensagem) throws EntradaException {
		if (mensagem.equals("") || mensagem.trim().equals("")  || mensagem == null) {
			throw new CriaPostException("A mensagem nao pode ser vazia");
		}
	}

	/**
	 * Metodo utilizado para validar o texto do conteudo do post.
	 * 
	 * @param conteudoDoPost
	 * @throws EntradaException
	 */

	public static void validaTexto(String texto)
			throws EntradaException {
		if (texto.length() >= 200) {
			throw new CriaPostException(
					"O limite maximo da mensagem sao 200 caracteres.");
		}
	}

	/**
	 * Metodo utilizado para validar as hashtags da mensagem.
	 * 
	 * @param mensagem
	 * @throws EntradaException
	 */

	public static void validaHashtags(String mensagem) throws EntradaException {
		String textoDeHashtags = separaTextoHashtags(mensagem);
		String[] hashtags = textoDeHashtags.split(" ");

		for (String hashtag : hashtags) {
			if (!hashtag.startsWith("#")) {
				throw new CriaPostException(
						"As hashtags devem comecar com '#'. Erro na hashtag: '"
								+ hashtag + "'.");
			}
		}
	}

	/**
	 * Recebe mensagem original e separa em uma mensagem com hashtags, para que possa ser analisada.
	 *  
	 * @param mensagem
	 * @return
	 */
	
	private static String separaTextoHashtags(String mensagem) {
		String textoDeHashtags = mensagem.substring(mensagem.indexOf("#"),
				mensagem.length());
		
		return textoDeHashtags;
	}
	
	/**
	 * Valida se a data de criacao do post esta correta.
	 * @param dataDeNascimento
	 * @throws EntradaException
	 */
	
	public static void validaDataPost(String dataPost)
			throws EntradaException {
		if (dataPost.equals("") || dataPost.trim().equals("")
				|| dataPost == null) {
			throw new CriaPostException("Data nao existe.");
		}

		if (!dataEhValida(dataPost)) {
			throw new CriaPostException("Data nao existe.");
		}
	}

	/**
	 * Metodo utilizado para verificar se uma data eh valida
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

}