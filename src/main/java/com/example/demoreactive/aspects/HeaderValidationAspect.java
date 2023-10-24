package com.example.demoreactive.aspects;

import com.example.demoreactive.exception.InvalidHeaderException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Aspect
@Component
public class HeaderValidationAspect {

    @Around("@annotation(com.example.demoreactive.aspects.HeaderValidator)")
    public Object validateHeader(ProceedingJoinPoint joinPoint) throws Throwable {
        ServerHttpRequest request = ((ServerWebExchange) joinPoint.getArgs()[0]).getRequest();

        if (request.getHeaders().containsKey("x-header")) {
            String headerValue = request.getHeaders().getFirst("x-header");
            if (validHeader(headerValue)) {
                return joinPoint.proceed();
            } else {
                throw new InvalidHeaderException("Invalid header");
            }
        } else {
            throw new InvalidHeaderException("Header not found");
        }
    }

    private boolean validHeader(String headerValue) {
        return headerValue.equals("x-value");
    }
}

