package interaction;

import java.util.ArrayList;
import java.util.List;

import media.MidiaPost;
import util.UtilPost;
import exceptions.EntradaException;

public class Post {

	private int curtidas;
	private int rejeicoes;
	private int popularidade;
	private String texto;
	private String data;
	private String mensagem;
	private List<MidiaPost> listaDeMidia;
	private List<String> listaDeHashtags;
	private boolean temEpicWin;
	private boolean temEpicFail;

	/**
	 * Construtor da classe Post.
	 * 
	 * @param mensagem
	 * @param data
	 * @throws EntradaException
	 */

	public Post(String mensagem, String data) throws EntradaException {
		this.mensagem = mensagem;
		this.curtidas = 0;
		this.rejeicoes = 0;
		this.popularidade = 0;
		this.texto = "";
		this.data = data;
		this.listaDeMidia = new ArrayList<MidiaPost>();
		this.listaDeHashtags = new ArrayList<String>();
		this.temEpicWin = false;
		this.temEpicFail = false;

		UtilPost.validaMensagem(mensagem);
		separaConteudoDaMensagem(mensagem);
		UtilPost.validaTexto(texto);
		UtilPost.validaDataPost(data);
		if (mensagem.contains("#")) {
			UtilPost.validaHashtags(mensagem);
		}
	}

	/**
	 * Metodo utilizado para filtrar a mensagem quando um post eh criado e
	 * separar o conteudo da mensagem, por texto, midia e hashtags.
	 * 
	 * @param mensagem
	 */

	public void separaConteudoDaMensagem(String mensagem) {
		this.texto = UtilPost.filtraTexto(mensagem);
		UtilPost.filtraMidia(mensagem, listaDeMidia);
		UtilPost.filtraHashtags(mensagem, listaDeHashtags);
	}

	/**
	 * Metodo utilizado para formatar a data de um post, no padrao ano/mes/dia
	 * hora.
	 * 
	 * @param data
	 * @return
	 */

	public String formataData(String data) {
		String[] valoresData = data.split("[/, ]");
		String dia = valoresData[0];
		String mes = valoresData[1];
		String ano = valoresData[2];
		String hora = valoresData[3];

		return ano + "-" + mes + "-" + dia + " " + hora;
	}

	/**
	 * Metodo utilizado para adicionar uma curtida ao post.
	 */

	public void curtir() {
		this.curtidas += 1;
	}

	/**
	 * Metodo utilizado para adicionar uma popularidade.
	 * 
	 * @param pops
	 */

	public void addPopularidade(int pops) {
		this.popularidade = popularidade + pops;
	}

	/**
	 * Metodo utilizado para adicionar uma rejeicao ao post.
	 */

	public void rejeitar() {
		this.rejeicoes += 1;
	}

	/**
	 * Metodo utilizado para remover a popularidade de um post.
	 * 
	 * @param pops
	 */

	public void removePopularidade(int pops) {
		this.popularidade -= pops;
	}

	/**
	 * Metodo utilizado para obter a quantidade de curtidas tem um post.
	 * 
	 * @return
	 */

	public int getCurtidas() {
		return curtidas;
	}

	/**
	 * Metodo utilizado para alterar as curtidas de um post.
	 * 
	 * @param curtidas
	 */

	public void setCurtidas(int curtidas) {
		this.curtidas = curtidas;
	}

	/**
	 * Metodo utilizado para obter a quantidade de rejeicoes de um post.
	 * 
	 * @return
	 */

	public int getRejeicoes() {
		return rejeicoes;
	}

	/**
	 * Metodo utilizado para alterar as rejeicoes de um post.
	 * 
	 * @return
	 */

	public void setRejeicoes(int rejeicoes) {
		this.rejeicoes = rejeicoes;
	}

	/**
	 * Metodo utilizado para obter a popularidade de um post.
	 * 
	 * @return
	 */

	public int getPopularidade() {
		return popularidade;
	}

	/**
	 * Metodo utilizado para alterar a popularidade de um post.
	 * 
	 * @return
	 */

	public void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}
	// TODO: JAVADOC
	public void setEpicWin(boolean valor) {
		this.temEpicWin = valor;
	}
	// TODO: JAVADOC
	public void setEpicFail(boolean valor) {
		this.temEpicFail = valor;
	}
	// TODO: JAVADOC
	public boolean temEpic(String epic) {
		if (epic.equals("#epicwin")) {
			return temEpicWin;
		} else if (epic.equals("#epicfail")) {
			return temEpicFail;
		}
		
		return false;
	}
	
	/**
	 * Metodo utilizado para obter um determinado conteudo do post.
	 * 
	 * @param indice
	 * @return
	 */

	public String getConteudoDoPost(int indice) {
		if (indice == 0) {
			return texto;
		} else if (indice >= 1 && indice <= listaDeMidia.size()) {
			return listaDeMidia.get(indice - 1).toString();
		} else if (indice > listaDeMidia.size()
				&& indice <= listaDeHashtags.size()) {
			return listaDeHashtags.get(indice - listaDeMidia.size());
		}
		return null;
	}

	/**
	 * Metodo utilizado para obter a lista de conteudos do post.
	 * 
	 * @return
	 */

	public List<String> getConteudoDoPost() {
		List<String> conteudoDoPost = new ArrayList<String>();
		conteudoDoPost.add(texto);

		for (MidiaPost conteudo : listaDeMidia) {
			conteudoDoPost.add(conteudo.getCaminho());
		}

		return conteudoDoPost;
	}

	/**
	 * Metodo utilizado para obter a mensagem de um post, por mensagem
	 * entende-se o texto juntamente com as midias.
	 * 
	 * @return
	 */

	public String getMensagem() {
		if (listaDeMidia.isEmpty()) {
			return getTexto();
		} else {
			return getTexto() + " " + getMidias();
		}
	}

	/**
	 * Metodo utilizado para obter o texto de um post.
	 * 
	 * @return
	 */

	public String getTexto() {
		return this.texto;
	}

	/**
	 * Metodo utilizado para obter as midias de um post.
	 * 
	 * @return
	 */

	public String getMidias() {
		String listaDeMidias = "";

		if (listaDeMidia.isEmpty()) {
			return null;
		}
		for (MidiaPost conteudo : listaDeMidia) {
			listaDeMidias += conteudo.getCaminho() + " ";
		}

		return listaDeMidias.substring(0, (listaDeMidias.length() - 1));
	}

	/**
	 * Metodo utilizado para alterar a mensagem de um post, esta mensagem
	 * refere-se a mensagem original passada na hora de criacao de um post.
	 * Texto e/ou midia e/ou hashtags.
	 * 
	 * @param mensagem
	 */

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * Metodo utilizado para obter a lista de hashtags de um post.
	 * 
	 * @return
	 */

	public List<String> getListaDeHashtags() {
		return listaDeHashtags;
	}

	/**
	 * Metodo utilizado para alterar a lista de hashtags de um post.
	 * 
	 * @param listaDeHashtags
	 */

	public void setListaDeHashtags(List<String> listaDeHashtags) {
		this.listaDeHashtags = listaDeHashtags;
	}

	/**
	 * Metodo utilizado para adicionar uma nova hashtag a lista de hashtags do
	 * post.
	 * 
	 * @param novaHashtag
	 */

	public void addHashTag(String novaHashtag) {
		this.listaDeHashtags.add(novaHashtag);
		addHashTagAMensagem(novaHashtag);
		if (this.listaDeHashtags.contains("#epicwin"))
			this.setEpicWin(true);
		if (this.listaDeHashtags.contains("#epicfail"))
			this.setEpicFail(true);
	}

	// TODO: JAVADOC
	public void addHashTagAMensagem(String novaHashtag) {
		mensagem += " " + novaHashtag;
	}

	/**
	 * Metodo utilizado para obter o post formatado, seguindo o padrao mensagem
	 * original (contendo hashtags e midia) juntamente com a data formatada.
	 * 
	 * @return
	 */

	public String getPostFormatado() {
		return this.mensagem + " (" + formataData(this.data) + ")";
	}

	/**
	 * Metodo utilizado para obter a data nao formatada de um post.
	 * 
	 * @return
	 */

	public String getData() {
		return data;
	}
	
	/**
	 * Metodo utilizado para obter a data formatada de um post.
	 * 
	 * @return
	 */
	public String getDataFormatada() {
		return formataData(data);
	}

	/**
	 * Metodo utilizado para obter as hashtags de um post.
	 * 
	 * @return
	 */

	public String getHashtags() {
		if (listaDeHashtags.isEmpty()) {
			return "";
		}

		String hashtags = "";
		for (String hashtag : listaDeHashtags) {
			hashtags = hashtags + hashtag + ",";
		}

		return (hashtags.substring(0, hashtags.length() - 1));
	}

}