package exceptions;

public class AtualizaTrendingException extends LogicaException {

	private static final long serialVersionUID = -4821220504963400528L;

	public AtualizaTrendingException(String mensagem) {
		super("Nao foi possivel atualizar o trending topics. " + mensagem);
	}
}
