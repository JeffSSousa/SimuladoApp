package com.jeffssousa.SimuladoApp.controller;

import com.jeffssousa.SimuladoApp.usecase.session.StartAttemptUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("attempt")
@RequiredArgsConstructor
public class ExamAttemptController {


    private final StartAttemptUseCase useCase;

    @PostMapping("/start/{id}")
    public ResponseEntity<Void> startAttempt(@PathVariable Long id){
        useCase.startAttempt(id);
        return ResponseEntity.ok().build();
    }

}
