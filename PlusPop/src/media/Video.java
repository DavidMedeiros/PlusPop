package media;

import media.MidiaPost;

public class Video extends MidiaPost {

	private static final long serialVersionUID = 2300193963014724105L;

	public Video(String caminhoDaMidia) {
		super(caminhoDaMidia);
	}

	@Override
	public String toString() {
		return "$arquivo_video:" + caminhoDaMidiaFiltrado;
	}

}