package org.example.abidijasser.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.*;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @After("execution( * org.example.abidijasser.service.serviceExamImpl.add*() )")
    public void logAfter(JoinPoint joinPoint) {
        l.info("méthode executé");
    }

    @Before("execution( * org.example.abidijasser.service.serviceExamImpl.add*() )")
    public void logBefore(JoinPoint joinPoint) {
        l.info("méthode executé");
    }

    @Around("execution( * org.example.abidijasser.service.serviceExamImpl.add*() )")
    public Object logArround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long endTime = System.currentTimeMillis()-startTime;
        l.info("Arround execution methode:"+endTime);

        return obj;
    }


}
// 3 methodes pour lancer le LOG:
// before, after , arround l'execution du methode

//   joinpoint point lors l'execution du programme, quand l'execution se fait, ou une exception ..........


// (* org.example.abidijasser.service.serviceExamImpl.add*() = visibilité -> typRetour -> package -> qui commence avec ->
// ( les types de parametres, string , int ... )
