package interaction;

public class IconePOP implements Interacao {
	// TODO: JAVADOC
	@Override
	public void curtir(Post post) {
		post.addPopularidade(50);
		if (!(post.getListaDeHashtags().contains("#epicwin"))) {
			post.addHashTag("#epicwin");
		}
	}
	// TODO: JAVADOC
	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(50);
		if (!(post.getListaDeHashtags().contains("#epicfail"))) {
			post.addHashTag("#epicfail");
		}
	}
	// TODO: JAVADOC
	@Override
	public String toString() {
		return "Icone Pop";
	}
}