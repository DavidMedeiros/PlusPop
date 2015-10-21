package interaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IconePOP implements Interacao {

	@Override
	public void curtir(Post post) {
		post.curtir();
		post.addPopularidade(50);
		if (postRecente(post.getData())) {
			post.addHashTag("#epicwin");
		}
		
	}

	@Override
	public void rejeitar(Post post) {
		post.rejeitar();
		post.removePopularidade(50);
		if (!postRecente(post.getData())) {
			post.addHashTag("#epicfail");
		}
		
	}

	public boolean postRecente(String dataDoPost) {
		Date dataAtual = new Date();
		// TODO: Data atual serve para o que?
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualFormatada= formatter.format(dataDoPost);
		if (dataAtualFormatada.equals(dataDoPost)) {
			return true;
		}
		return false;
	}
	
}