package com.Insurance.user.service;

import com.Insurance.user.model.UserData;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    /**
     *
     * @param to holds receiver email address
     * @param subject to set Email subject
     * @param body emailbody provide in body object
     * @Returntype no return type
     */
    public void mailSentToUser(String to, String subject, String body){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shaileshdubeyr@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText("this is system generated mail " + body);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail sent successfull");
    }

    /**
     *
     * @param to holds receiver email address
     * @param subject to set Email subject
     * @param body emailbody provide in body object
     * this method is will send the mail when data is updated
     */
    public void updateMail(String to, String subject, String body){
        SimpleMailMessage simpleMailMessage = null;
        simpleMailMessage.setFrom("shaileshdubeyr@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        System.out.println("updated data successfully");
        javaMailSender.send(simpleMailMessage);
    }

    /**
     *
     * @param token to pass as a parameter of methos
     * @return String which holds the link of endpoint
     */
    public String getLink(String token){
        //end point link of note microservice
        return "http://localhost:8084/get/verify/" + token;
    }

    /**
     *
     * @param userData holds the user information
     * @param token to verify the the user
     * @return end point link to change the password
     */
    public String forgetPassword(UserData userData, String token){

        return "curl -i \\\n" +
                "-H \"Accept: application/json\" \\\n" +
                "-H \"Content-Type:application/json\" \\\n" +
                "-X POST --data \n" +
                new Gson().toJson(userData) +"http://localhost:8080/user/reset/password?" + token;
    }
}
