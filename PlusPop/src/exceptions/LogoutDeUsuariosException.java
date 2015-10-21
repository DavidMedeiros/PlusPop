package exceptions;

public class LogoutDeUsuariosException extends EntradaException {
	public LogoutDeUsuariosException(String mensagem){
		super("Nao eh possivel realizar logout. " + mensagem);
	}
}