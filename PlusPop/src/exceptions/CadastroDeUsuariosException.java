package exceptions;

public class CadastroDeUsuariosException extends EntradaException {
	
	private static final long serialVersionUID = -3765716588608788176L;

	public CadastroDeUsuariosException(String mensagem){
		super("Erro no cadastro de Usuarios. " + mensagem);
	}
}