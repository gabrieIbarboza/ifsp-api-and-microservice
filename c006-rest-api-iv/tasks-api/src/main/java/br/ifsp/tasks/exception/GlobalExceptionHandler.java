package br.ifsp.tasks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe para tratar exceções globais e personalizar as respostas de erro.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de recurso não encontrado
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Trata exceções de tentar modificar tarefas concluídas
     */
    @ExceptionHandler(InvalidTaskStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, String>> handleInvalidTaskStateException(InvalidTaskStateException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Trata exceções de validação e retorna uma resposta simplificada.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleGlobalValidationExceptions(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(violation -> 
            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Trata exceções de conversão de tipos e retorna uma resposta simplificada.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex) {

        Map<String, String> error = new HashMap<>();
        Throwable rootCause = ex.getRootCause();

        if (rootCause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) rootCause;
            if (ife.getTargetType().isEnum()) {
                String enumName = ife.getTargetType().getSimpleName();
                String message = String.format(
                    "Invalid value '%s' for enum %s. Valid values: %s",
                    ife.getValue(), enumName, Arrays.toString(ife.getTargetType().getEnumConstants())
                );
                error.put("error", message);
                return ResponseEntity.badRequest().body(error);
            }
        }

        error.put("error", "Malformed request payload");
        return ResponseEntity.badRequest().body(error);
    }



    /**
     * Trata exceções genéricas que não foram capturadas pelos handlers anteriores.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Erro interno no servidor. Entre em contato com o suporte.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}