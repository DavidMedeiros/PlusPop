package exceptions;

public class BaixarPostException extends LogicaException {

	public BaixarPostException(String mensagem) {
		super("Erro ao baixar posts. " + mensagem);
	}
}
