package exceptions;

public class LoginDeUsuariosException extends EntradaException {
	public LoginDeUsuariosException(String mensagem){
		super("Nao foi possivel realizar login. " + mensagem);
	}
}
