package friendship;

import java.util.ArrayList;
import java.util.List;

public class Notificacao {

	private List<String> notificacoes;
	// TODO: JAVADOC
	public Notificacao() {
		this.notificacoes = new ArrayList<String>();
	}
	// TODO: JAVADOC
	public void addNotificacao(String novaNotificacao) {
		this.notificacoes.add(novaNotificacao);
	}
	// TODO: JAVADOC
	public void removeNotificacao(String notificacao) {
		this.notificacoes.remove(notificacao);
	}
	// TODO: JAVADOC
	public List<String> getNotificacoes() {
		return this.notificacoes;
	}
	// TODO: JAVADOC
	public int size() {
		return this.size();
	}

}