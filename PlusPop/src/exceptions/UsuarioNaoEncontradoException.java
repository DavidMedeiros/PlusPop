package exceptions;

public class UsuarioNaoEncontradoException extends LogicaException {
 
	private static final long serialVersionUID = -323243460496344410L;

	public UsuarioNaoEncontradoException(String email){
		super("Um usuarix com email " + email + " nao esta cadastradx.");
	}
}