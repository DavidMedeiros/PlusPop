package exceptions;

public class BaixarPostException extends LogicaException {

	private static final long serialVersionUID = 2622258223683868961L;

	public BaixarPostException(String mensagem) {
		super("Erro ao baixar posts. " + mensagem);
	}
}
