package com.example.demoreactive.services;

import com.example.demoreactive.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ApiService {
    private final static String PATH_TODO = "/todos/{id}";
    private final WebClient webClient;

    public Mono<Todo> getTodo(String id) {
        return webClient.get()  // HTTP method
                .uri(PATH_TODO, id)  // path relative to base url
                .retrieve()
                .bodyToMono(Todo.class);  // materialize
    }
}
