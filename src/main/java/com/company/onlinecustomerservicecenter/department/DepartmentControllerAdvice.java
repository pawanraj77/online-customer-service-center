/******************************************************************************************************************
 *          @author           Jayshree
 *          Description       It is a controller advice class that handles exceptions across the entire application.
 *                            Specifically, it deals with validation exceptions thrown by the DepartmentController
 *                            methods, providing a way to return meaningful error messages to the client when
 *                            input validation constraints are violated.
 *          Version           3.2.2
 *          Created Date      20-March-2024
 *******************************************************************************************************************/


package com.company.onlinecustomerservicecenter.department;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DepartmentControllerAdvice {

    /***********************************************************************************************************
     * Method: 			        - handleValidationExceptions
     * Description: 			- Handles validation exceptions by returning a map of field names and validation
     *                            error messages.
     * @param ex                - exception that contains information about the validation errors
     * @return map              - a map containing field names as keys and validation error messages as values
     * Created By                - Jayshree
     * Created Date              - 20-March-2024

     ************************************************************************************************************/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
