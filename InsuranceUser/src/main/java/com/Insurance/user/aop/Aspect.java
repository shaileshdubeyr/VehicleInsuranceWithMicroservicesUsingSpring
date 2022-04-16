package com.Insurance.user.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Before("execution(* com.Insurance.user.service.UserService.saveUser(..))")
    public void savingData(JoinPoint jp){
        System.out.println("Saving data into the database");
    }
}
