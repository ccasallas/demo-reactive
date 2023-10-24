package com.example.demoreactive.controllers;

import com.example.demoreactive.aspects.HeaderValidator;
import com.example.demoreactive.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.example.demoreactive.services.ApiService;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class HelloController {

    private final ApiService apiService;

    @GetMapping("hello")
    @HeaderValidator
    public Mono<String> sayHello(ServerWebExchange serverWebExchange) {
        return Mono.just("Hola, mundo!");
    }

    @GetMapping("hello-not-validated")
    public Mono<String> sayHelloNotHeaderValidated(ServerWebExchange serverWebExchange) {
        return Mono.just("Hola, mundo!");
    }

    @GetMapping("todos/{todoId}")
    public Mono<Todo> getTodo(@PathVariable String todoId) {
        return apiService.getTodo(todoId);
    }
}

