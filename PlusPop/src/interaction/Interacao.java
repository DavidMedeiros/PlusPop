package interaction;

import post.Post;

public interface Interacao {
	
	public void curtir (Post post);
	
	public void rejeitar (Post post);
	
	public int quantidadeDePosts();
	
}