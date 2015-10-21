package exceptions;

public class UsuarioNaoEncontradoException extends LogicaException {
	public UsuarioNaoEncontradoException(String email){
		super("Um usuarix com email " + email + " nao esta cadastradx.");
	}
}