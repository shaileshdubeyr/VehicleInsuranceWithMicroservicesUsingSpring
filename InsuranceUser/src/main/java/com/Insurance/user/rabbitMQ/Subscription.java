package com.Insurance.user.rabbitMQ;

import com.Insurance.user.configuration.RabbitMQConfig;
import com.Insurance.user.model.UserData;
import com.Insurance.user.service.EmailService;
import com.Insurance.user.util.UserUtility;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Subscription {

    @Autowired
    EmailService emailService;

    @Autowired
    UserUtility userUtility;

    /**
     *
     *
     * @return returning the UserDTO
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void getUserDto(UserData userData){
        System.out.println("the get data from queue is "+userData);
        //generating token
        String token  = userUtility.createToken(userData.getId());
        //sending the mail to user
       // emailService.mailSentToUser(userData.getEmail(),"mail verification", emailService.getLink(token));
    }
}
