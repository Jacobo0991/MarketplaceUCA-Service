package com.marketplace.backend.exceptions;

import com.marketplace.backend.domain.dto.GeneralResponse;
import com.marketplace.backend.exceptions.category.CategoryAlreadyExists;
import com.marketplace.backend.exceptions.category.CategoryNotFound;
import com.marketplace.backend.exceptions.comment.CommentNotFound;
import com.marketplace.backend.exceptions.faculty.FacultyNotFound;
import com.marketplace.backend.exceptions.likes.LikesNotFound;
import com.marketplace.backend.exceptions.product.ProductNotFound;
import com.marketplace.backend.exceptions.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* CATEGORY EXCEPTIONS */
    @ExceptionHandler(CategoryAlreadyExists.class)
    public ResponseEntity<GeneralResponse> categoryAlreadyExistsHandler(CategoryAlreadyExists e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
    }

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<GeneralResponse> categoryNotFoundHandler(CategoryNotFound e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    /* COMMENT EXCEPTIONS */
    @ExceptionHandler(CommentNotFound.class)
    public ResponseEntity<GeneralResponse> commentNotFoundHandler(CommentNotFound e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    /* FACULTY EXCEPTIONS */
    @ExceptionHandler(FacultyNotFound.class)
    public ResponseEntity<GeneralResponse> facultyNotFoundHandler(FacultyNotFound e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    /* LIKES EXCEPTIONS*/
    @ExceptionHandler(LikesNotFound.class)
    public ResponseEntity<GeneralResponse> likesNotFoundHandler(LikesNotFound e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    /* PRODUCT EXCEPTIONS */
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<GeneralResponse> productNotFoundHandler(ProductNotFound e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<GeneralResponse> failedToSaveImageHandler(IOException e) {
        return GeneralResponse.builder()
                .message(e.getMessage() + "Failed to save image")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    /* USER EXCEPTIONS */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GeneralResponse> failedToLoginHandler(AuthenticationException e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<GeneralResponse> userNotFoundHandler(UserNotFound e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(RoleAlreadySet.class)
    public ResponseEntity<GeneralResponse> userAlreadyAdminHandler(RoleAlreadySet e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @ExceptionHandler(UserNoAuthorized.class)
    public ResponseEntity<GeneralResponse> badPasswordHandler(UserNoAuthorized e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();

    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<GeneralResponse> userAlreadyExistHandler(UserAlreadyExist e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
    }

    @ExceptionHandler(ImpossibleAction.class)
    public ResponseEntity<GeneralResponse> impossibleAction(ImpossibleAction e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .build();
    }

    @ExceptionHandler(EmailNotVerify.class)
    public ResponseEntity<GeneralResponse> emailNotVerify(EmailNotVerify e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .build();
    }

    /* GENERAL EXCEPTIONS */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> unexpectedExceptionHandler(Exception e) {
        return GeneralResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }


}
