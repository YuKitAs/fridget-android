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
import retrofit2.http.Query;

public interface CommentService {
    /**
     * Diese Klasse synchroniesert die Comments mit dem Server
     */


    //Diese Methode ruft die Kommentare eier CoolNote ab
    @GET("comments")
    Call<List<Comment>> getAllComments(@Query("cool-note") String coolNoteId);

    //Diese Methode speichert ein Comment
    @Headers("Content-Type: application/json")
    @POST("/comments")
    Call<Comment> createComment(@Body Comment comment);

    //Diese Methode löscht ein Comment
    @DELETE("/comments/{id}")
    Call<Void> deleteComment(@Path("id") String commentId);
}
