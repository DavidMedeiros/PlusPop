package interaction;

import post.Post;

public class Normal implements Interacao {
	@Override
	public void curtir(Post post) {
		post.addPopularidade(10);
	}

	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(10);
	}

	@Override
	public String toString() {
		return "Normal Pop";
	}

	@Override
	public int quantidadeDePosts() {
		return 2;
	}

}