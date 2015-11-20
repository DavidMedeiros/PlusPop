package exceptions;

public class NotificacoesException extends LogicaException {
 
	private static final long serialVersionUID = -8501418636458387935L;

	public NotificacoesException() {
		super("Nao ha mais notificacoes.");
	}

}