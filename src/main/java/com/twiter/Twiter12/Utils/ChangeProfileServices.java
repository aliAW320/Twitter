package com.twiter.Twiter12.Utils;

import com.twiter.Twiter12.DataBase.DBSetup;
import com.twiter.Twiter12.Models.TokenSeason;
import com.twiter.Twiter12.Models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

public class ChangeProfileServices {
    private String token;
    private String bio;
    private String newProfilePic;


    public ChangeProfileServices(String token, String bio, String newProfilePic) {
        this.token = token;
        this.bio = bio;
        this.newProfilePic = newProfilePic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNewProfilePic() {
        return newProfilePic;
    }

    public void setNewProfilePic(String newProfilePic) {
        this.newProfilePic = newProfilePic;
    }

    public void madeChanges () {
        User user = User.getUserByEmail(TokenSeason.getEmailByToken(token));
        if(bio!=null||!bio.isEmpty()||bio.equals(" ")||!bio.equals("")){
            user.setBio(bio);
        }
        if(newProfilePic!=null||!newProfilePic.isEmpty()){
            try{
                String media = Media.downloadMedia(user.getUsername(),newProfilePic);
                user.setProfilePicture(media);
            }catch(Exception e){
                throw e;
            }

        }
        Session session = DBSetup.getSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
    }
}
