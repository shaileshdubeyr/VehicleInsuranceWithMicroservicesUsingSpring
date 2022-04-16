package com.insurance.category.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InsuranceCategoryAspect {

    @Before("execution(* com.insurance.category.service.CategoryInsuranceService.saveInsurance(..))")
    public String beforeSave(JoinPoint joinPoint){
        System.out.println("data is saving");
        return "data saved";
    }

    @After("execution(* com.insurance.category.service.CategoryInsuranceService.saveInsurance(..))")
    public String afterSave(JoinPoint joinPoint){
        System.out.println("data is saving success");
        return "data saved";
    }

    @AfterReturning("execution(* com.insurance.category.service.CategoryInsuranceService.saveInsurance(..))")
    public String afterReturning(JoinPoint joinPoint){
        System.out.println("data is saved");
        return "data saved";
    }

    @After("execution(* com.insurance.category.service.CategoryInsuranceService.getInsuranceById(..))")
    public String afterGet(JoinPoint joinPoint){
        System.out.println("retrived data with id");
        return "data saved";
    }
}
