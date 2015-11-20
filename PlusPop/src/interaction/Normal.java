package interaction;

import java.io.Serializable;

import post.Post;

public class Normal implements Interacao, Serializable{
	
	private static final long serialVersionUID = -5854941684563562545L;
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