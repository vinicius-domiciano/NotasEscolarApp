package br.com.myApp.MyApp.model.dto.errors;

public class ErrorResponseDTO {

    private Integer statusCode;
    private String message;

    /** Constructors **/

    public ErrorResponseDTO() {
    }

    public ErrorResponseDTO(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /** Getters and Setters **/

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
