package media;

public class MidiaPost {
	
	protected String caminhoDaMidia;
	protected String caminhoDaMidiaFiltrado;
	//TODO: JAVADOC
	public MidiaPost(String caminhoDaMidia) {
		this.caminhoDaMidia = caminhoDaMidia;
		this.caminhoDaMidiaFiltrado = filtraCaminhoDaMidia();
	}
	//TODO: JAVADOC
	private String filtraCaminhoDaMidia() {
		int inicioDoCaminho = caminhoDaMidia.indexOf(">") + 1;
		int fimDoCaminho = caminhoDaMidia.lastIndexOf("<");
		
		return caminhoDaMidia.substring(inicioDoCaminho, fimDoCaminho);
	}
	//TODO: JAVADOC
	public String getCaminho() {
		return caminhoDaMidia;
	}
}