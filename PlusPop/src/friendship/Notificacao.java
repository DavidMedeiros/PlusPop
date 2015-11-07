package friendship;

import java.util.ArrayList;
import java.util.List;

public class Notificacao {

	private List<String> notificacoes;

	/**
	 * Construtor da classe Notificacao. *
	 */

	public Notificacao() {
		this.notificacoes = new ArrayList<String>();
	}

	/**
	 * Metodo utilizado para adicionar uma notificacao a lista de notificacoes.
	 * 
	 * @param novaNotificacao
	 */

	public void addNotificacao(String novaNotificacao) {
		this.notificacoes.add(novaNotificacao);
	}

	/**
	 * Metodo utilizado para remover uma notificacao da lista de notificacoes.
	 * 
	 * @param notificacao
	 */

	public void removeNotificacao(String notificacao) {
		this.notificacoes.remove(notificacao);
	}

	/**
	 * Metodo utilizado para obter a lista de notificacoes.
	 * 
	 * @return
	 */
	public List<String> getNotificacoes() {
		return this.notificacoes;
	}

	/**
	 * Metodo utilizado para obter o tamanho da notificacao.
	 * 
	 * @return
	 */
	public int size() {
		return this.size();
	}

}