package cat.tecnocampus.notes.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cat.tecnocampus.notes.application.exception.UserNotFoundException;
import cat.tecnocampus.notes.domain.exception.NotEditableNote;
import cat.tecnocampus.notes.domain.exception.NoteNotOwnedException;
import cat.tecnocampus.notes.persistence.exception.NoteDoesNotExistException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, NoteDoesNotExistException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler({NotEditableNote.class, NoteNotOwnedException.class})
    public ResponseEntity<String> handleNotPermittedException(RuntimeException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class}) // ErrorValidationHandlingAdvice.class, 
    public ResponseEntity<String> handleValidationException(RuntimeException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}