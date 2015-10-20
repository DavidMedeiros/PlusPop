package media;

public class Imagem extends MidiaPost {

	public Imagem(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}

	@Override
	public String toString() {
		return "$arquivo_imagem:" + caminhoDaMidiaFiltrado;
	}
	
}
