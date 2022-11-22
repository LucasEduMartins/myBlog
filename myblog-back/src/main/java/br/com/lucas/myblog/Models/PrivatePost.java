package br.com.lucas.myblog.Models;

public class PrivatePost extends Post{
    public PrivatePost(){
        this.setType(PostType.PRIVATE);
    }
}
