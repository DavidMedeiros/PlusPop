package exceptions;

public class AtualizacaoPerfilException extends EntradaException {

	public AtualizacaoPerfilException(String mensagem) {
		super("Erro na atualizacao de perfil. " + mensagem);
	}
}