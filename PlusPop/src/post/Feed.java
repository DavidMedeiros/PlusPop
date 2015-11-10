package post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Feed {
	
	private List<Post> posts;
	
	public Feed() {
		this.posts = new ArrayList<Post>();
	}
	
	public void adicionaPostAoFeed(Post novoPost) {
			this.posts.add(novoPost);
	}
	
	public void ordenaPorData() {
		Collections.sort(this.posts);
	}
	
	public void ordenaPorPopularidade() {
		Collections.sort(this.posts, new Comparator<Post>() {

			@Override
			public int compare(Post post1, Post post2) {
				if (post1.getPopularidade() > post2.getPopularidade()) {
					return 1;
				} else if (post1.getPopularidade() < post2.getPopularidade()) {
					return -1;
				} else {
				return 0;
				}
			}
			
		});
	}
	
	public void atualizaFeedPorData() {
		this.ordenaPorData();
	}
	
	public void atualizaPorPopularidade() {
		this.ordenaPorPopularidade();
	}
	
	public void atualizaFeed() {
		this.atualizaFeedPorData();
		this.atualizaPorPopularidade();
	}

}
