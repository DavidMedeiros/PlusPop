package exceptions;

public class ConsultaDePopsException extends LogicaException {

	public ConsultaDePopsException(String mensagem) {
		super("Erro na consulta de Pops. " + mensagem);
	}

}