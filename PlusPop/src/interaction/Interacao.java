package interaction;

import java.io.Serializable;

import post.Post;

public interface Interacao extends Serializable {
	
	public void curtir (Post post);
	
	public void rejeitar (Post post);
	
	public int quantidadeDePosts();
	
}