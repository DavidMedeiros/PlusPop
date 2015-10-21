package media;

public class MidiaPost {
	
	protected String caminhoDaMidia;
	protected String caminhoDaMidiaFiltrado;
	
	public MidiaPost(String caminhoDaMidia) {
		this.caminhoDaMidia = caminhoDaMidia;
		this.caminhoDaMidiaFiltrado = filtraCaminhoDaMidia();
	}
	
	private String filtraCaminhoDaMidia() {
		int inicioDoCaminho = caminhoDaMidia.indexOf(">") + 1;
		int fimDoCaminho = caminhoDaMidia.lastIndexOf("<");
		
		return caminhoDaMidia.substring(inicioDoCaminho, fimDoCaminho);
	}

	public String getCaminho() {
		return caminhoDaMidia;
	}
}