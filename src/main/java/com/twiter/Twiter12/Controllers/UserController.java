package com.twiter.Twiter12.Controllers;

import com.twiter.Twiter12.Response.Response;
import com.twiter.Twiter12.Response.ValidationResponse;
import com.twiter.Twiter12.Utils.ChangeProfileServices;
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
}
