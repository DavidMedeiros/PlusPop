package ranking;

import interaction.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import user.Usuario;

public class Trending {

	public List<HashtagTrending> hashtagsDoSistema;

	public Trending() {
		this.hashtagsDoSistema = new ArrayList<HashtagTrending>();
	}
	//TODO: JAVADOC
	public void addHashtagsDoPostAoTrending(Post post) {
		List<String> hashTagsDoPost = post.getListaDeHashtags();

		for (String hashTag : hashTagsDoPost) {
			HashtagTrending novaHashtag = new HashtagTrending(hashTag);
			addHashtag(novaHashtag);
		}
	}
	//TODO: JAVADOC
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
	//TODO: JAVADOC
	public String getTopHashtags(int quantidadeTrends) {
		
		ordenaHashtags();
		
		StringBuilder sb = new StringBuilder();

		if (this.hashtagsDoSistema.size() == 0) {
			//TODO: EXCEPTION 
			return null;

		} else if (this.hashtagsDoSistema.size() <= quantidadeTrends) {
			for (int i = 0; i < this.hashtagsDoSistema.size(); i++) {
				sb.append("(" + (i+1) + ") " + this.hashtagsDoSistema.get(i).toString() + "; ");
			}
		} else {
			for (int i = 0; i < quantidadeTrends; i++) {
				sb.append("(" + (i+1) + ") " + this.hashtagsDoSistema.get(i).toString() + "; ") ;
			}
		}
		String saida = "Trending Topics:  " + sb.substring(0, sb.length() - 1);
		return saida;
	}
	//TODO: JAVADOC
	public List<HashtagTrending> getHashtagsDoSistema() {
		return hashtagsDoSistema;
	}
	//TODO: JAVADOC
	public void ordenaHashtags() {
		Collections.sort(this.hashtagsDoSistema);
	}

}