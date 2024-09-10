package com.twiter.Twiter12.Controllers;

import com.twiter.Twiter12.DataBase.DBSetup;
import com.twiter.Twiter12.Models.TokenSeason;
import com.twiter.Twiter12.Models.User;
import com.twiter.Twiter12.Response.Response;
import com.twiter.Twiter12.Response.SingUpResponse;
import com.twiter.Twiter12.Utils.UniqueGenerator;
import com.twiter.Twiter12.Utils.Validation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SingUpController {
    @PostMapping("/singUp")
    @CrossOrigin
    public ResponseEntity singUp(@RequestBody User user){
        Response response;
        int code;
        User newUser = User.checkNewUser(user.getEmail(), user.getUsername());
        if (newUser == null){
            user.setId(UniqueGenerator.generateUniqueID());
            Session session = DBSetup.getSession();
            Transaction transaction = session.beginTransaction();
            if(!Validation.emailIsValid(user.getEmail())){
                code=400;
                response = new SingUpResponse(false,"Not a valid email",null,400);
                return new ResponseEntity<>(response, HttpStatus.valueOf(code));  // 'code' is an int

            }
            if (Validation.passIsValid(user.getPassword())){
                code=400;
                response = new SingUpResponse(false,"Password is weak",null,400);
                return new ResponseEntity<>(response, HttpStatus.valueOf(code));  // 'code' is an int

            }
            session.save(user);
            TokenSeason token = new TokenSeason(user.getEmail(),UniqueGenerator.getLoginToken());
            session.save(token);
            transaction.commit();
            session.close();
            code = 200;
            response = new SingUpResponse(true,"account created",token.getToken(),200);
        }
        else {
            code = 409 ;
            response = new SingUpResponse(false, "email or username is already in use", null,409 );
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(code));  // 'code' is an int

    }
}
