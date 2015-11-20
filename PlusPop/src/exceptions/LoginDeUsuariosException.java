package exceptions;

public class LoginDeUsuariosException extends EntradaException {

	private static final long serialVersionUID = -2507075978701347227L;

	public LoginDeUsuariosException(String mensagem){
		super("Nao foi possivel realizar login. " + mensagem);
	}
}