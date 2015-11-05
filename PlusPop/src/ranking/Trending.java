package ranking;

import interaction.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trending {

	public List<HashtagTrending> hashtagsTrending;

	public Trending() {
		this.hashtagsTrending = new ArrayList<HashtagTrending>();
	}

	public void hashtagsTrending(Post post) {
		List<String> hashTagsDoPost = post.getListaDeHashtags();

		for (int i = 0; i < hashTagsDoPost.size(); i++) {
			HashtagTrending novaHashtag = new HashtagTrending(
					hashTagsDoPost.get(i));
			if (this.hashtagsTrending.contains(novaHashtag)) {
				novaHashtag.novaOcorrencia();
			} else {
				this.hashtagsTrending.add(novaHashtag);
			}
		}
	}

	public String getTopHashtags(int quantidadeTrends) {
		Collections.sort(this.hashtagsTrending);

		StringBuilder sb = new StringBuilder();
		String EOL = System.getProperty("line.separator");
		
		if (this.hashtagsTrending.size() == 0) {
			return null;
			
		} else if (this.hashtagsTrending.size() <= quantidadeTrends) {
			for (int i = this.hashtagsTrending.size() - 1; i > -1; i--) {
				sb.append(this.hashtagsTrending.get(i).toString() + EOL);
			}
		} else {
			for (int i = this.hashtagsTrending.size() - 1; i > (this.hashtagsTrending
					.size() - quantidadeTrends - 1); i--) {
				sb.append(this.hashtagsTrending.get(i).toString() + EOL);
			}
		}

		return sb.substring(0, sb.length() - 1);
	}

}