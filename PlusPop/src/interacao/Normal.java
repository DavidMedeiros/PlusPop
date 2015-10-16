package interacao;


public class Normal extends Popularidade implements Interacao {

	@Override
	public void curtir(Post post) {
		post.curtir();
		post.addPopularidade(10);
	}

	@Override
	public void rejeitar(Post post) {
		post.rejeitar();
		post.removePopularidade(10);
	}

}
