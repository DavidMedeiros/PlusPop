package interaction;

import post.Post;

public class Normal implements Interacao {
	
	public static final int QUANTIDADE_DE_POPS = 10;
	public static final int QUANTIDADE_DE_POSTS_FORNECIDOS = 2;
	
	@Override
	public void curtir(Post post) {
		post.addPopularidade(QUANTIDADE_DE_POPS);
	}

	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(QUANTIDADE_DE_POPS);
	}

	@Override
	public String toString() {
		return "Normal Pop";
	}

	@Override
	public int quantidadeDePosts() {
		return QUANTIDADE_DE_POSTS_FORNECIDOS;
	}

}