package media;

public class Imagem extends MidiaPost {
	//TODO: JAVADOC
	public Imagem(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}
	//TODO: JAVADOC
	@Override
	public String toString() {
		return "$arquivo_imagem:" + caminhoDaMidiaFiltrado;
	}
	
}