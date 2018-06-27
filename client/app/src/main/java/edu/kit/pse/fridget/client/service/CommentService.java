package edu.kit.pse.fridget.client.service;


import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Comment;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentService {
    /**
     * Diese Klasse synchroniesert die Comments mit dem Server
     */



    //Diese Methode ruft die Kommentare eier CoolNote ab
    @Headers("Authorization:{JWT}")
    @GET("comments?cool-note={cid}")
    Call<List<Comment>> getAllComments(@Path("cid") String coolNoteId);


    //Diese Methode speichert ein Comment
    @Headers("Authorization:{JWT}")
    @POST("/comments")
    Call<Comment> createComment(@Body Comment comment);

    //Diese Methode löscht ein Comment
    @Headers("Authorization:{JWT}")
    @DELETE("/comments/{id}")
    Call<Comment> deleteComment(@Path("id") String commentID);




}
