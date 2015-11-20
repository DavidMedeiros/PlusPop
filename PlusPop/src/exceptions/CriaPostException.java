package exceptions;

public class CriaPostException extends EntradaException {

	private static final long serialVersionUID = -4039961302071214592L;

	public CriaPostException(String mensagem) {
		super("Nao eh possivel criar o post. " + mensagem);
	}
}