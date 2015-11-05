package interaction;

public class Normal implements Interacao {
	// TODO: JAVADOC
	@Override
	public void curtir(Post post) {
		post.addPopularidade(10);
	}
	// TODO: JAVADOC
	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(10);
	}
	// TODO: JAVADOC
	@Override
	public String toString() {
		return "Normal Pop";
	}
	
}