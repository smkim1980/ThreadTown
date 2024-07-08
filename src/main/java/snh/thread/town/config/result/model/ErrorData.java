package snh.temp.springjsp.common.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@ToString
public class ErrorData {
    private String code = "failure";
    private HttpStatus httpStatus;
    private String message;
}
