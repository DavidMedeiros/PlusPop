package post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Feed implements Serializable{
 
	private static final long serialVersionUID = 3123381443407710780L;
	private List<Post> posts;
	
	public Feed() {
		this.posts = new ArrayList<Post>();
	}
	
	public void adicionaPostAoFeed(Post novoPost) {
			this.posts.add(novoPost);
	}
	
	public void ordenaPorData() {
		// Do post mais recente para o post mais antigo.
		Collections.sort(this.posts);
		// Do post mais antigo para o mais recente.
		Collections.reverse(this.posts);
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

	public List<Post> getPosts() {
		return posts;
	}
}
