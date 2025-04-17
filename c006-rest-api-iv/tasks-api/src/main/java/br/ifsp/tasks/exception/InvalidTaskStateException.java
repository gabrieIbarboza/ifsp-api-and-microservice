package br.ifsp.tasks.exception;

public class InvalidTaskStateException extends RuntimeException  {
    public InvalidTaskStateException(String message) {
        super(message);
    }
}
