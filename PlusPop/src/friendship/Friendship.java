package friendship;

import usuario.Usuario;

public interface Friendship {

	public void aceitaAmigo(Usuario novoAmigo);
	
	public void removeAmigo(Usuario amigoRemovido);

	public void addSolicitacao(Usuario usuario);
	
	public Usuario buscaAmigo(String emailDoAmigo);
}
