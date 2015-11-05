package exceptions;

public class AtualizaRankingException extends LogicaException {

	public AtualizaRankingException(String mensagem) {
		super("Nao foi possivel atualizar ranking de usuarixs. " + mensagem);
	}

}
