package exceptions;

public class AtualizacaoPerfilException extends EntradaException {

	private static final long serialVersionUID = -1157783071522531820L;

	public AtualizacaoPerfilException(String mensagem) {
		super("Erro na atualizacao de perfil. " + mensagem);
	}
}