package media;

public class MidiaPost {

	protected String caminhoDaMidia;
	protected String caminhoDaMidiaFiltrado;

	/**
	 * Construtor da classe MidiaPost.
	 * 
	 * @param caminhoDaMidia
	 */

	public MidiaPost(String caminhoDaMidia) {
		this.caminhoDaMidia = caminhoDaMidia;
		this.caminhoDaMidiaFiltrado = filtraCaminhoDaMidia();
	}

	/**
	 * Metodo utilizado para filtrar o caminho da midia, removendo as marcacoes.
	 * 
	 * @return
	 */

	private String filtraCaminhoDaMidia() {
		int inicioDoCaminho = caminhoDaMidia.indexOf(">") + 1;
		int fimDoCaminho = caminhoDaMidia.lastIndexOf("<");

		return caminhoDaMidia.substring(inicioDoCaminho, fimDoCaminho);
	}

	/**
	 * Metodo utilizado para obter o caminho da midia.
	 * 
	 * @return
	 */
	
	public String getCaminho() {
		return caminhoDaMidia;
	}
}