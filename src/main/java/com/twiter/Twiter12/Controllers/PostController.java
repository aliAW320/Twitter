package com.twiter.Twiter12.Controllers;

import com.twiter.Twiter12.DataBase.DBSetup;
import com.twiter.Twiter12.Models.Post;
import com.twiter.Twiter12.Models.TokenSeason;
import com.twiter.Twiter12.Models.User;
import com.twiter.Twiter12.Response.ValidationResponse;
import com.twiter.Twiter12.Response.Response;
import com.twiter.Twiter12.Utils.Media;
import com.twiter.Twiter12.Utils.PostToken;
import com.twiter.Twiter12.Utils.UniqueGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class PostController {
    @PostMapping("/post/create")
    @CrossOrigin
    public ResponseEntity addPost(@RequestBody PostToken postToken){
        Response response;
        int code;
        try(Session session = DBSetup.getSession()){
            Transaction transaction = session.beginTransaction();
            String token = postToken.getToken();
            String email = TokenSeason.getEmailByToken(token);
            User user = User.getUserByEmail(email);
            String url =Media.downloadMedia(postToken.getMediaName(), postToken.getMediaData());
            if(url == null){
                response = new ValidationResponse(false,"failed to download media in server", 500);
                code = 500;
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(code));

            }
            Post post = new Post ( user, postToken.getText(), LocalDateTime.now(),url);
            post.setId(UniqueGenerator.generateUniqueID());
            session.save(post);
            user.getPosts().add(post);
            session.update(user);
            transaction.commit();
            response = new ValidationResponse(true,"successfully added post", 200);
            code = 200;


        }catch (Exception e){
            code = 400;
            response = new ValidationResponse(false,e.getMessage(), 400);
        }





        return new ResponseEntity<>(response, HttpStatusCode.valueOf(code));
    }
}
