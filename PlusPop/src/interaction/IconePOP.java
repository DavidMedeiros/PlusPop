package interaction;

import post.Post;

public class IconePOP implements Interacao {
	
	public static final int QUANTIDADE_DE_POPS_NORMAL = 50;
	public static final String HASHTAG_CURTIR = "#epicwin";
	public static final String HASHTAG_REJEITAR = "#epicfail";
	
	@Override
	public void curtir(Post post) {
		post.addPopularidade(QUANTIDADE_DE_POPS_NORMAL);
		if (!(post.getListaDeHashtags().contains(HASHTAG_CURTIR))) {
			post.addHashTag(HASHTAG_CURTIR);
		}
	}

	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(QUANTIDADE_DE_POPS_NORMAL);
		if (!(post.getListaDeHashtags().contains(HASHTAG_REJEITAR))) {
			post.addHashTag(HASHTAG_REJEITAR);
		}
	}

	@Override
	public String toString() {
		return "Icone Pop";
	}

	@Override
	public int quantidadeDePosts() {
		return 6;
	}
}