package exceptions;

public class CurtePostException extends LogicaException {

	private static final long serialVersionUID = -3409340223998743650L;

	public CurtePostException(String mensagem) {
		super("Nao foi possivel curtir o post. " + mensagem);
	}

}
