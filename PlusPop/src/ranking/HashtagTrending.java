package ranking;

public class HashtagTrending implements Comparable<HashtagTrending> {

	public String hashtag;
	public int ocorrencia;
	//TODO: JAVADOC
	public HashtagTrending(String hashtag) {
		this.hashtag = hashtag;
		this.ocorrencia = 1;
	}
	//TODO: JAVADOC
	public void novaOcorrencia() {
		this.ocorrencia += 1;
	}
	//TODO: JAVADOC
	public int getOcorrencia() {
		return this.ocorrencia;
	}
	//TODO: JAVADOC
	public String getHashtag() {
		return this.hashtag;
	}
	//TODO: JAVADOC
	@Override
	public int compareTo(HashtagTrending outraHashtagTrending) {
		if (this.ocorrencia > outraHashtagTrending.getOcorrencia()){
			return -1;
		} else if (this.ocorrencia == outraHashtagTrending.getOcorrencia()){
			return outraHashtagTrending.getHashtag().compareTo(this.hashtag);
		} else{ // this.ocorrencia < novaHashtag.getOcorrencia()
			return 1;
		}
	}
	//TODO: JAVADOC
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		return result;
	}
	//TODO: JAVADOC
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HashtagTrending) {
			HashtagTrending novoObj = (HashtagTrending) obj;
			return this.hashtag.equals(novoObj.getHashtag());
		}
		return false;
	}
	//TODO: JAVADOC
	public String toString() {
		return this.hashtag + ": " + this.ocorrencia;
	}



}