package com.twiter.Twiter12.Controllers;

import com.twiter.Twiter12.DataBase.DBSetup;
import com.twiter.Twiter12.Models.Post;
import com.twiter.Twiter12.Models.TokenSeason;
import com.twiter.Twiter12.Models.User;
import com.twiter.Twiter12.Response.Response;
import com.twiter.Twiter12.Response.ValidationResponse;
import com.twiter.Twiter12.Response.WholeProfileResponse;
import com.twiter.Twiter12.Utils.ChangeProfileServices;
import com.twiter.Twiter12.Utils.Media;
import com.twiter.Twiter12.Utils.PasswordServices;
import com.twiter.Twiter12.Utils.Validation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @PutMapping("/user/changeProfile")
    @CrossOrigin
    public ResponseEntity changeProfile(@RequestBody ChangeProfileServices newProfile) {
        Response response;
        int code;
        try{
            newProfile.madeChanges();
            code = 200;
            response = new ValidationResponse(true,"update succses",code);
        }catch(Exception e){
            code = 400;
            response = new ValidationResponse(false,e.getMessage(),code);
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(code));
    }
    @GetMapping("/user/allData/{UserName}")
    @CrossOrigin
    public ResponseEntity allData(String UserName) {
        Response response;
        int code;
        User user = User.checkNewUser(" ",UserName);
        if(user== null){
            code = 400;
            response = new ValidationResponse(false,"User not found",code);
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(code));
        }
        for (Post post : user.getPosts()) {
            post.setMedia(Media.uploadMedia(post.getMedia()));
        }
        response = new WholeProfileResponse(user.getPosts(),user.getPosts().size(), user.getFollowersCount(), user.getFollowingCount(), user.getUsername(), Media.uploadMedia(user.getProfilePicture()),200);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200)) ;
    }
    @PutMapping("/user/changePassword")
    @CrossOrigin
    public ResponseEntity changePassword(@RequestBody PasswordServices passwordServices){
        Response response;
        int code;
        User user = User.getUserByEmail(TokenSeason.getEmailByToken(passwordServices.getToken()));
        if(!Validation.passIsValid(passwordServices.getPassword())){
            code = 400;
            response = new ValidationResponse(false,"Password is weak",code);
            return new ResponseEntity<>(response,HttpStatusCode.valueOf(code));
        }
        user.setPassword(passwordServices.getPassword());
        Session session = DBSetup.getSession();
        Transaction transaction = session.getTransaction();
        session.update(user);
        transaction.commit();
        code =200;
        response = new ValidationResponse(true,"success",code);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(code));

    }

}
