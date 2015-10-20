package media;

import media.MidiaPost;

public class Video extends MidiaPost {

	public Video(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}

	@Override
	public String toString() {
		return "$arquivo_video:" + caminhoDaMidia.substring(7, caminhoDaMidia.length() - 8);
	}
	
}