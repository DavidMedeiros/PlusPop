package exceptions;

public class CriaPostException extends EntradaException {

	public CriaPostException(String mensagem) {
		super("Nao eh possivel criar o post. " + mensagem);
	}
}