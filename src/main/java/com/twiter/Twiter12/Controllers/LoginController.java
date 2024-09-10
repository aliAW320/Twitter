package com.twiter.Twiter12.Controllers;

import com.twiter.Twiter12.DataBase.DBSetup;
import com.twiter.Twiter12.Models.TokenSeason;
import com.twiter.Twiter12.Models.User;
import com.twiter.Twiter12.Response.LoginResponse;
import com.twiter.Twiter12.Response.Response;
import com.twiter.Twiter12.Utils.LoginEntity;
import com.twiter.Twiter12.Utils.UniqueGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginEntity login){
        Response response ;
        int code;
        User user = User.checkUser(login.getEmail(), login.getPassword());

        if (user != null) {
            Session session = DBSetup.getSession();
            Transaction transaction = session.beginTransaction();

            TokenSeason token = session.get(TokenSeason.class, login.getEmail());
            if (token == null) {
                token = new TokenSeason();
                token.setEmail(login.getEmail());
                token.setToken(UniqueGenerator.getLoginToken());
                session.save(token);
            }

            code = 200;
            response = new LoginResponse(true, "Login successful", token.getToken(), 200);
            transaction.commit();
            session.close();
        } else {
            code = 404;
            response = new LoginResponse(false, "Email or password is incorrect", null, 404);
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(code));  // 'code' is an int



    }
    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }


    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public String handleOptionsRequest() {
        System.out.println("OPTIONS!!!!!!!!!!!!!");
        return "OPTIONS";
    }
}
