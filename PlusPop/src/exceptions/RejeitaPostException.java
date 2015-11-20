package exceptions;

public class RejeitaPostException extends LogicaException {
 
	private static final long serialVersionUID = 8626244724977590413L;

	public RejeitaPostException(String mensagem) {
		super("Nao foi possivel rejeitar o post. " + mensagem);
	}

}
