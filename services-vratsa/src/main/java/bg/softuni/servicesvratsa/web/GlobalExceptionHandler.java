package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleProductNotFoundException(ProductNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error-404");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}
