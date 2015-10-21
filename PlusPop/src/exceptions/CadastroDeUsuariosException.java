package exceptions;

public class CadastroDeUsuariosException extends EntradaException {
	
	public CadastroDeUsuariosException(String mensagem){
		super("Erro no cadastro de Usuarios. " + mensagem);
	}
}