package interaction;

import java.text.SimpleDateFormat;
import java.util.Date;

import post.Post;

public class CelebridadePOP implements Interacao {
	
	public static final int QUANTIDADE_DE_POPS_NORMAL = 25;
	public static final int QUANTIDADE_DE_POPS_RECENTE = 10;
	public static final int QUANTIDADE_DE_POSTS_FORNECIDOS = 4;
	
	@Override
	public void curtir(Post post) {
		post.addPopularidade(QUANTIDADE_DE_POPS_NORMAL);

		if (postRecente(post.getDataFormatada())) {
			post.addPopularidade(QUANTIDADE_DE_POPS_RECENTE);
		}
	}

	@Override
	public void rejeitar(Post post) {
		post.removePopularidade(QUANTIDADE_DE_POPS_NORMAL);
		if (!postRecente(post.getDataFormatada())) {
			post.removePopularidade(QUANTIDADE_DE_POPS_RECENTE);
		}
	}

	public boolean postRecente(String dataDoPost) {
		Date dataAtual = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
		String dataCorrente = formataData.format(dataAtual);

		if (dataCorrente.equals(dataDoPost.substring(0, 10))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Celebridade Pop";
	}

	@Override
	public int quantidadeDePosts() {
		return QUANTIDADE_DE_POSTS_FORNECIDOS;
	}
	
	
}