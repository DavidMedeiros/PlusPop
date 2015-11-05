package exceptions;

public class CurtePostException extends LogicaException {

	public CurtePostException(String mensagem) {
		super("Nao foi possivel curtir o post. " + mensagem);
	}

}
