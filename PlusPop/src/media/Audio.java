package media;

public class Audio extends MidiaPost {
	//TODO: JAVADOC 
	public Audio(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}
	//TODO: JAVADOC
	@Override
	public String toString() {
		return "$arquivo_audio:" + caminhoDaMidiaFiltrado;
	}

}