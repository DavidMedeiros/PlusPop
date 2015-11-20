package exceptions;

public class RequisicaoPostException extends LogicaException {

	private static final long serialVersionUID = -5354976372061090119L;

	public RequisicaoPostException() {
		super("Requisicao invalida. O indice deve ser maior ou igual a zero.");
	}
	

}
