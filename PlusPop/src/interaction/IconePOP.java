package interaction;

public class IconePOP implements Interacao {

	@Override
	public void curtir(Post post) {
		post.addPopularidade(50);
		post.addHashTag("#epicwin");	
	}

	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(50);
		post.addHashTag("#epicfail");
	}

	@Override
	public String toString() {
		return "Icone Pop";
	}
}