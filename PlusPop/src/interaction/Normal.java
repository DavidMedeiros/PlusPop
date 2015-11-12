package interaction;

import post.Post;

public class Normal implements Interacao {
	
	public static final int QUANTIDADE_DE_POPS = 10;
	
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
		return 2;
	}

}