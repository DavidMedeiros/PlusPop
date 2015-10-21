package interaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CelebridadePOP implements Interacao {

	@Override
	public void curtir(Post post) {
		post.curtir();
		post.addPopularidade(25);
		Date dataAtual = new Date();
		//TODO: DATA ATUAL SERVE PARA O QUE?
		if (postRecente(post.getData())) {
			post.addPopularidade(10);

		}

	}

	@Override
	public void rejeitar(Post post) {
		post.rejeitar();
		post.removePopularidade(25);
		if (!postRecente(post.getData())) {
			post.removePopularidade(10);
		}
	}

	public boolean postRecente(String dataDoPost) {
		Date dataAtual = new Date();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualFormatada = formatter.format(dataDoPost);
		if (dataAtualFormatada.equals(dataDoPost)) {
			return true;
		}
		return false;
	}

}