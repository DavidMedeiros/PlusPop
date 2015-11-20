package exceptions;

public class FecharSistemaException extends LogicaException {

	private static final long serialVersionUID = -2401877227277376135L;

	public FecharSistemaException() {
		super("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	}

}