package media;

public class Audio extends MidiaPost {
 
	private static final long serialVersionUID = 2445348139069499733L;

	public Audio(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}

	@Override
	public String toString() {
		return "$arquivo_audio:" + caminhoDaMidiaFiltrado;
	}

}