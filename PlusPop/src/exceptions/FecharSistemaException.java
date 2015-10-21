package exceptions;

public class FecharSistemaException extends LogicaException {

	public FecharSistemaException() {
		super("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	}

}