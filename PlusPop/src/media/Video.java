package media;

import media.MidiaPost;

public class Video extends MidiaPost {
	//TODO: JAVADOC
	public Video(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}
	//TODO: JAVADOC
	@Override
	public String toString() {
		return "$arquivo_video:" + caminhoDaMidiaFiltrado;
	}
	
}