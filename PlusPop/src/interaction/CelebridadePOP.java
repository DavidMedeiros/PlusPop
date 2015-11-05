package interaction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CelebridadePOP implements Interacao {

	@Override
	public void curtir(Post post) {
		post.addPopularidade(25);

		if (postRecente(post.getDataFormatada())) {
			post.addPopularidade(10);
		}
	}

	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(25);
		if (!postRecente(post.getDataFormatada())) {
			post.removePopularidade(10);
		}
	}

	public boolean postRecente(String dataDoPost) {
		Date dataAtual = new Date();
		SimpleDateFormat formataDataAtual = new SimpleDateFormat("yyyy-MM-dd");
		String dataCorrente = formataDataAtual.format(dataAtual);
		
		if(dataCorrente.equals(dataDoPost.substring(0, 10))){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Celebridade Pop";
	}
}