package exceptions;

public class LogoutDeUsuariosException extends EntradaException {
 
	private static final long serialVersionUID = -3080605255408981936L;

	public LogoutDeUsuariosException(String mensagem){
		super("Nao eh possivel realizar logout. " + mensagem);
	}
}