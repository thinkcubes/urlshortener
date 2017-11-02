package kr.co.freeism.urlshortener.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@Slf4j
@ControllerAdvice
@Controller
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ModelAndView handle(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException", ex);

        return new ModelAndView("error");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView handle(RuntimeException ex) {
        log.error("RuntimeException : message = {}", ex.getMessage(), ex);

        return new ModelAndView("error");
    }
}