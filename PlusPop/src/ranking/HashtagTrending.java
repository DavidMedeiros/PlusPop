package ranking;

public class HashtagTrending implements Comparable<HashtagTrending> {

	public String hashtag;
	public int ocorrencia;

	/**
	 * Construtor da classe HashtagTrending
	 * 
	 * @param hashtag
	 */

	public HashtagTrending(String hashtag) {
		this.hashtag = hashtag;
		this.ocorrencia = 1;
	}

	/**
	 * Metodo utilizado para adicionar uma nova ocorrencia a Hashtag.
	 */

	public void novaOcorrencia() {
		this.ocorrencia += 1;
	}

	/**
	 * Metodo utilizado para obter a quantidade de ocorrencias de uma Hashtag.
	 * 
	 * @return
	 */

	public int getOcorrencia() {
		return this.ocorrencia;
	}

	/**
	 * Metodo utilizado para obter uma hashtag
	 * 
	 * @return
	 */

	public String getHashtag() {
		return this.hashtag;
	}

	/**
	 * Metodo para comparacao entre hashtags.
	 */

	@Override
	public int compareTo(HashtagTrending outraHashtagTrending) {
		if (this.ocorrencia > outraHashtagTrending.getOcorrencia()) {
			return -1;
		} else if (this.ocorrencia == outraHashtagTrending.getOcorrencia()) {
			return outraHashtagTrending.getHashtag().compareToIgnoreCase(
					this.hashtag);
		} else { // this.ocorrencia < novaHashtag.getOcorrencia()
			return 1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HashtagTrending) {
			HashtagTrending novoObj = (HashtagTrending) obj;
			return this.hashtag.equals(novoObj.getHashtag());
		}
		return false;
	}

	public String toString() {
		return this.hashtag + ": " + this.ocorrencia;
	}

}