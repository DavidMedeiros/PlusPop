package media;

public class Audio extends MidiaPost {
	
	public Audio(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}

	@Override
	public String toString() {
		return "$arquivo_audio:" + caminhoDaMidiaFiltrado;
	}

}