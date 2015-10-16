package util;

import java.util.List;

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

	public static void filtraTexto(String mensagem, List<String> conteudoDoPost) {
		String[] palavras = mensagem.split(" ");
		String textoFiltrado = "";

		for (String palavra : palavras) {
			if (!(palavra.startsWith("#") || (palavra.startsWith("<") & palavra
					.endsWith(">")))) {
				textoFiltrado = textoFiltrado + palavra + " ";
			}
		}
		conteudoDoPost.add(textoFiltrado.substring(0,
				textoFiltrado.length() - 1));
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

	public static void filtraMidia(String mensagem, List<String> conteudoDoPost) {
		String[] palavras = mensagem.split(" ");

		for (String palavra : palavras) {
			if (palavra.startsWith("<audio>") || palavra.startsWith("<imagem>")) {
				conteudoDoPost.add(palavra);
			}
		}
	}

	/**
	 * Metodo utilizado para obter o conteudo do post, filtrando os caminhos de
	 * audio e imagem para impressao.
	 * 
	 * @param indice
	 * @param conteudoDoPost
	 * @return
	 */

	public static String obtemConteudoDoPost(int indice,
			List<String> conteudoDoPost) {
		String conteudo = conteudoDoPost.get(indice);

		if (conteudo.startsWith("<imagem>")) {
			return "$arquivo_imagem:"
					+ conteudo.substring(8, conteudo.length() - 9);
		}
		if (conteudo.startsWith("<audio>")) {
			return "$arquivo_audio:"
					+ conteudo.substring(7, conteudo.length() - 8);
		} else {
			return conteudo;
		}
	}

	/**
	 * Metodo utilizado para validar uma mensagem passada.
	 * 
	 * @param mensagem
	 * @throws EntradaException
	 */

	public static void validaMensagem(String mensagem) throws EntradaException {
		if (mensagem.equals("") || mensagem == null) {
			throw new CriaPostException("A mensagem não pode ser vazia");
		}
	}

	/**
	 * Metodo utilizado para validar o texto do conteudo do post.
	 * 
	 * @param conteudoDoPost
	 * @throws EntradaException
	 */

	public static void validaTexto(List<String> conteudoDoPost)
			throws EntradaException {
		if (conteudoDoPost.get(0).length() >= 200) {
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
		String textoDeHashtags = mensagem.substring(mensagem.indexOf("#"),
				mensagem.length());
		String[] hashtags = textoDeHashtags.split(" ");

		for (String hashtag : hashtags) {
			if (!hashtag.startsWith("#")) {
				throw new CriaPostException(
						"As hashtags devem comecar com '#'. Erro na hashtag: '"
								+ hashtag + "'.");
			}
		}
	}

}
