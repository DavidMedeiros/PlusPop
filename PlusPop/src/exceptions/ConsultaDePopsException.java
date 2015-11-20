package exceptions;

public class ConsultaDePopsException extends LogicaException {

	private static final long serialVersionUID = 4589771227700250114L;

	public ConsultaDePopsException(String mensagem) {
		super("Erro na consulta de Pops. " + mensagem);
	}

}
