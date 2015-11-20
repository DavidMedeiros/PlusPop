package ranking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import post.Post;
import exceptions.AtualizaTrendingException;

public class Trending implements Serializable {

	private static final long serialVersionUID = 9195306211275058892L;
	
	public List<HashtagTrending> hashtagsDoSistema;

	/**
	 * Construtor da classe Trending.
	 */

	public Trending() {
		this.hashtagsDoSistema = new ArrayList<HashtagTrending>();
	}

	/**
	 * Metodo utilizado para adicionar as hashtags do post a lista de hashtags
	 * do sistema.
	 * 
	 * @param post
	 */

	public void addHashtagsDoPostAoTrending(Post post) {
		List<String> hashTagsDoPost = post.getListaDeHashtags();

		for (String hashTag : hashTagsDoPost) {
			HashtagTrending novaHashtag = new HashtagTrending(hashTag);
			addHashtag(novaHashtag);
		}
	}

	/**
	 * Metodo utilizado para adicionar um objeto Hashtag a lista de Hashtags do
	 * sistema.
	 * 
	 * @param novaHashtag
	 */

	public void addHashtag(HashtagTrending novaHashtag) {
		if (!(this.hashtagsDoSistema.contains(novaHashtag))) {
			this.hashtagsDoSistema.add(novaHashtag);
		} else {
			for (HashtagTrending hashtag : hashtagsDoSistema) {
				if (novaHashtag.equals(hashtag)) {
					hashtag.novaOcorrencia();
				}
			}
		}
	}

	/**
	 * Metodo utilizado para obter as topHashtags do sistema. Eh necessario
	 * informar o numero de tops que se deseja obter.
	 * 
	 * @param quantidadeTrends
	 * @return
	 * @throws AtualizaTrendingException 
	 */

	public String getTopHashtags(int quantidadeTrends) throws AtualizaTrendingException {

		ordenaHashtags();

		StringBuilder sb = new StringBuilder();

		if (this.hashtagsDoSistema.isEmpty()) {
			throw new AtualizaTrendingException("Nenhuma hashtag no sistema.");

		} else if (this.hashtagsDoSistema.size() <= quantidadeTrends) {
			for (int i = 0; i < this.hashtagsDoSistema.size(); i++) {
				sb.append("(" + (i + 1) + ") "
						+ this.hashtagsDoSistema.get(i).toString() + "; ");
			}
		} else {
			for (int i = 0; i < quantidadeTrends; i++) {
				sb.append("(" + (i + 1) + ") "
						+ this.hashtagsDoSistema.get(i).toString() + "; ");
			}
		}
		String saida = "Trending Topics:  " + sb.substring(0, sb.length() - 1);
		return saida;
	}

	/**
	 * Metodo utilizado para obter a lista de hashtags do sistema.
	 * 
	 * @return
	 */

	public List<HashtagTrending> getHashtagsDoSistema() {
		return hashtagsDoSistema;
	}

	/**
	 * Metodo utilizado para ordenar as hashtags do sistema.
	 */

	public void ordenaHashtags() {
		Collections.sort(this.hashtagsDoSistema);
	}

}