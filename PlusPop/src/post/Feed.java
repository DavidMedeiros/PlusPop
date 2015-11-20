package post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Feed implements Serializable {

	private static final long serialVersionUID = 3123381443407710780L;
	private List<Post> posts;

	/**
	 * Construtor da Classe Feed, responsavel por armazenar e ordenar uma lista
	 * de posts.
	 */

	public Feed() {
		this.posts = new ArrayList<Post>();
	}

	/**
	 * Metodo utilizado para adicionar um novo post a lista de posts do Feed.
	 * 
	 * @param novoPost
	 */

	public void adicionaPostAoFeed(Post novoPost) {
		this.posts.add(novoPost);
	}

	/**
	 * Metodo utilizado para ordenar a lista de posts pela data de criacao do
	 * post.
	 */

	public void ordenaPorData() {
		// Ordena do post mais recente para o post mais antigo.
		Collections.sort(this.posts);
		// Ordena do post mais antigo para o mais recente.
		Collections.reverse(this.posts);
	}

	/**
	 * Metodo utilizado para ordenar a lista de posts por popularidade dos
	 * posts.
	 */

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

	/**
	 * Metodo utilizado para obter a lista de posts do feed.
	 * 
	 * @return
	 */

	public List<Post> getPosts() {
		return posts;
	}
}
