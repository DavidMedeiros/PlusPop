package exceptions;

public class AtualizaTrendingException extends LogicaException {

	public AtualizaTrendingException(String mensagem) {
		super("Nao foi possivel atualizar o trending topics. " + mensagem);
	}
}
