package exceptions;

public class AtualizaRankingException extends LogicaException {

	private static final long serialVersionUID = 101702170195064666L;

	public AtualizaRankingException(String mensagem) {
		super("Nao foi possivel atualizar ranking de usuarixs. " + mensagem);
	}

}
