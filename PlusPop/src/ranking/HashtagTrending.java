package ranking;

public class HashtagTrending implements Comparable {

	public String hashtag;
	public int ocorrencia;

	public HashtagTrending(String hashtag) {
		this.hashtag = hashtag;
		this.ocorrencia = 1;
	}

	public void novaOcorrencia() {
		this.ocorrencia += 1;
	}

	public int getOcorrencia() {
		return this.ocorrencia;
	}

	public String getHashtag() {
		return this.hashtag;
	}

	@Override
	public int compareTo(Object hashtag) {
		if (hashtag instanceof HashtagTrending) {
			HashtagTrending novaHashtag = (HashtagTrending) hashtag;
			if (this.ocorrencia > novaHashtag.getOcorrencia()) {
				return 1;
			} else if (this.ocorrencia == novaHashtag.getOcorrencia()) {
				return this.hashtag.compareTo(novaHashtag.getHashtag());
			} else { // this.ocorrencia < novaHashtag.getOcorrencia()
				return -1;
			}
		}
		return (Integer) null;
		
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
		return this.hashtag.toString();
	}

}