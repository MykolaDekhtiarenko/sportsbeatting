package com.epam.training.sportsbeatting.aop;

import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Configuration
public class ServiceLoggingAspect {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final String METHOD_INFO = "Calling %s with paramethers: %s";
    private static final String METHOD_INVOCATION_RESULT = "Result is: %s";
    private static final String METHOD_EXECUTION_TIME = "Method took %s milliseconds to be executed.";

    @Around("within(com.epam.training.sportsbeatting.service..*)")
    public Object logExecutionTimeAndParameters(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getStaticPart().getSignature()).getMethod();
        LOGGER.info(String.format(METHOD_INFO, method.getName(), Arrays.asList(joinPoint.getArgs()).toString()));
        long startedAt = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startedAt;
        LOGGER.info(String.format(METHOD_INVOCATION_RESULT, Objects.nonNull(result) ? result.toString() : Strings.EMPTY));
        LOGGER.info(String.format(METHOD_EXECUTION_TIME, executionTime));

        return result;
    }

}