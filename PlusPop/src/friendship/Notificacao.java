package friendship;

import java.util.ArrayList;
import java.util.List;

public class Notificacao {

	private List<String> notificacoes;

	public Notificacao() {
		this.notificacoes = new ArrayList<String>();
	}

	public void addNotificacao(String novaNotificacao) {
		this.notificacoes.add(novaNotificacao);
	}
	
	public void removeNotificacao(String notificacao) {
		this.notificacoes.remove(notificacao);
	}
	
	public List<String> getNotificacoes() {
		return this.notificacoes;
	}

	public int size() {
		return this.size();
	}

}