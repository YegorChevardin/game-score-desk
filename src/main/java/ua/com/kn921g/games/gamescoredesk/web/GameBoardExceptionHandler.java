package ua.com.kn921g.games.gamescoredesk.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.kn921g.games.gamescoredesk.common.GameBoardExceptionMessages;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.GameScoreBoardBaseException;
import ua.com.kn921g.games.gamescoredesk.generated.dto.ApplicationExceptionResponseDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.ApplicationMethodArgumentNotValidResponseDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.InvalidFieldDto;

import java.time.OffsetDateTime;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GameBoardExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(GameScoreBoardBaseException.class)
  public ResponseEntity<ApplicationExceptionResponseDto> handleMimosaBaseExceptions(
      GameScoreBoardBaseException exception) {
    return ResponseEntity.status(exception.getHttpStatus())
        .body(createResponseDtoFromDetails(exception.getMessage(), exception.getHttpStatus()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApplicationExceptionResponseDto> handleAccessDeniedException(
      Exception exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(createResponseDtoFromDetails(exception.getMessage(), HttpStatus.FORBIDDEN));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    HttpStatus httpStatus = HttpStatus.valueOf(status.value());
    return ResponseEntity.status(httpStatus)
        .body(
            createResponseDtoFromDetails(
                GameBoardExceptionMessages.DTO_IS_NOT_VALID.getMessage(),
                httpStatus,
                extractErrorMessage(ex)));
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApplicationExceptionResponseDto> handleAuthenticationException(
      AuthenticationException exception) {
    return ResponseEntity.badRequest()
        .body(createResponseDtoFromDetails(exception.getMessage(), HttpStatus.BAD_REQUEST));
  }

  private ApplicationMethodArgumentNotValidResponseDto createResponseDtoFromDetails(
      String exceptionMessage, HttpStatus httpStatus, List<InvalidFieldDto> arguments) {
    return ApplicationMethodArgumentNotValidResponseDto.builder()
        .exceptionMessage(exceptionMessage)
        .statusCode(httpStatus.value())
        .message(httpStatus.getReasonPhrase())
        .timestamp(OffsetDateTime.now())
        .validationErrors(arguments)
        .build();
  }

  private ApplicationExceptionResponseDto createResponseDtoFromDetails(
      String exceptionMessage, HttpStatus httpStatus) {
    return ApplicationExceptionResponseDto.builder()
        .exceptionMessage(exceptionMessage)
        .statusCode(httpStatus.value())
        .message(httpStatus.getReasonPhrase())
        .timestamp(OffsetDateTime.now())
        .build();
  }

  private static List<InvalidFieldDto> extractErrorMessage(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream()
        .map(
            fieldError ->
                InvalidFieldDto.builder()
                    .fieldName(fieldError.getField())
                    .validationError(fieldError.getDefaultMessage())
                    .build())
        .toList();
  }

  // DO NOT PUT ANYTHING AFTER THIS NEXT HANDLER, IT WON'T BE CAUGHT
  // WRITE ANY NEW HANDLERS ABOVE THIS COMMENT
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ApplicationExceptionResponseDto genericAppException(Exception e) {
    log.error(e.getMessage(), e);
    return ApplicationExceptionResponseDto.builder()
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Unexpected exception")
        .exceptionMessage(e.getMessage())
        .timestamp(OffsetDateTime.now())
        .build();
  }
}
