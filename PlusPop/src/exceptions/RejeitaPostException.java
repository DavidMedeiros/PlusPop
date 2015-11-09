package exceptions;

public class RejeitaPostException extends LogicaException {

	public RejeitaPostException(String mensagem) {
		super("Nao foi possivel rejeitar o post. " + mensagem);
	}

}
