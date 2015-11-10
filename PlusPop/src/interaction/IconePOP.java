package interaction;

import post.Post;

public class IconePOP implements Interacao {
	@Override
	public void curtir(Post post) {
		post.addPopularidade(50);
		if (!(post.getListaDeHashtags().contains("#epicwin"))) {
			post.addHashTag("#epicwin");
		}
	}

	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(50);
		if (!(post.getListaDeHashtags().contains("#epicfail"))) {
			post.addHashTag("#epicfail");
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