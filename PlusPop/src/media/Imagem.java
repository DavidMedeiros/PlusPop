package media;

public class Imagem extends MidiaPost {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8648164004800017864L;

	public Imagem(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}

	@Override
	public String toString() {
		return "$arquivo_imagem:" + caminhoDaMidiaFiltrado;
	}
	
}