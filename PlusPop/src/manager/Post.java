package manager;

import java.util.ArrayList;
import java.util.List;

import exceptions.CriaPostException;
import exceptions.EntradaException;

public class Post {
	
	private int curtidas;
	private int rejeicoes;
	private int popularidade;
	private String data;
	private String mensagem;
	private List<String> conteudoDoPost;
	private List<String> listaDeHashtags;
		
	public Post(String mensagem, String data) throws EntradaException {
		this.mensagem = mensagem;
		this.curtidas = 0;
		this.rejeicoes = 0;
		this.popularidade = 0;
		this.data = data;
		this.conteudoDoPost = new ArrayList<String>();
		this.listaDeHashtags = new ArrayList<String>();
		
		validaMensagem(mensagem);
		validaHashtags(mensagem);
		separaConteudoDaMensagem(mensagem);
		validaTexto();
	}

	public void separaConteudoDaMensagem(String mensagem) {
		filtraTexto(mensagem);
		filtraMidia(mensagem);
		filtraHashtags(mensagem);
	}

	public void filtraTexto(String mensagem){
		String[] palavras = mensagem.split(" ");
		String textoFiltrado = "";
		
		for (String palavra : palavras) {
			if (!(palavra.startsWith("#") || (palavra.startsWith("<") & palavra.endsWith(">")))) {
				textoFiltrado = textoFiltrado + palavra + " ";
			}
		}
		conteudoDoPost.add(textoFiltrado.substring(0, textoFiltrado.length() -1));
	}

	public void filtraHashtags(String mensagem) {
		String[] palavras = mensagem.split(" ");
		for (String palavra : palavras) {
			if (palavra.startsWith("#")) {
				listaDeHashtags.add(palavra);
			}
		}
	}
	
	public void filtraMidia(String mensagem) {
		String[] palavras = mensagem.split(" ");
		
		for (String palavra : palavras) {
			if (palavra.startsWith("<audio>") || palavra.startsWith("<imagem>")) {
				this.conteudoDoPost.add(palavra);
			}
		}
	}
	
	public void validaMensagem(String mensagem) throws EntradaException {
		if (mensagem.equals("") || mensagem == null) {
			throw new CriaPostException("A mensagem não pode ser vazia");
		}
	}
	
	public void validaTexto() throws EntradaException {
		// COLOQUEI MAIOR IGUAL PQ TEM UM Q ELE ESPERA O ERRO, MAS A FRASE TEM EXATOS 200 CARACTERES = vide LINHA 14 
		if (this.conteudoDoPost.get(0).length() >= 200) {
			throw new CriaPostException("O limite maximo da mensagem sao 200 caracteres.");		
		}
	}
	
	public void validaHashtags(String mensagem) throws EntradaException {
		String textoDeHashtags = mensagem.substring(mensagem.indexOf("#"), mensagem.length());
		String[] hashtags = textoDeHashtags.split(" ");
		
		for (String hashtag : hashtags) {
			if (!hashtag.startsWith("#")) {
				throw new CriaPostException("As hashtags devem comecar com '#'. Erro na hashtag: '" + hashtag + "'.");
			}
		}
	}

	public int getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(int curtidas) {
		this.curtidas = curtidas;
	}

	public int getRejeicoes() {
		return rejeicoes;
	}

	public void setRejeicoes(int rejeicoes) {
		this.rejeicoes = rejeicoes;
	}

	public int getPopularidade() {
		return popularidade;
	}

	public void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}

	public List<String> getConteudoDoPost() {
		return conteudoDoPost;
	}

	public void setConteudoDoPost(List<String> conteudoDoPost) {
		this.conteudoDoPost = conteudoDoPost;
	}
	
	public String getMensagem() {
		String mensagem = "";
		
		mensagem = getTexto() + " " + getMidias();
		
		return mensagem;
	}
	
	public String getTexto() {
		return this.conteudoDoPost.get(0);
	}

	public String getMidias() {
		String listaDeMidias = "";
		
		for (String conteudo : conteudoDoPost) {
			if (conteudo.startsWith("<audio>") || conteudo.startsWith("<imagem>")) {
				listaDeMidias = listaDeMidias + conteudo + " ";
			}
		}
		
		return listaDeMidias.substring(0, listaDeMidias.length() - 1);
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<String> getListaDeHashtags() {
		return listaDeHashtags;
	}

	public void setListaDeHashtags(List<String> listaDeHashtags) {
		this.listaDeHashtags = listaDeHashtags;
	}

	public String getPostFormatado() {
		//TODO: FORMATAR DATA recebe assim: 02/08/2015 09:30:00 tem que sair assim: (2015-08-02 09:30:00)
		return this.mensagem + " " + data;
	}

	public String getData() {
		//TODO: FORMATAR DATA recebe assim: 02/08/2015 09:30:00 tem que sair assim: (2015-08-02 09:30:00)
		return data;
	}

	public String getHashtags() {
		String hashtags = "";
		for (String hashtag : listaDeHashtags) {
			hashtags = hashtags + hashtag + ",";
		}
		
		return (hashtags.substring(0, hashtags.length() - 1));
	}
	
}
