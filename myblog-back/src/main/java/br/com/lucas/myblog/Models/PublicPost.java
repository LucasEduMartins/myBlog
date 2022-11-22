package br.com.lucas.myblog.Models;

public class PublicPost extends Post {
    public PublicPost(){
        this.setType(PostType.PUBLIC);
    }
}
