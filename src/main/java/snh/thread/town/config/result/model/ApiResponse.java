package snh.thread.town.config.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ApiResponse<T> {
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private String code = "success";
    private String path;
    private T data;
    private String message;

    public ApiResponse(String path, T data) {
        this.path = path;
        this.data = data;
    }

    public ApiResponse(String path, String code, String message) {
        this.path = path;
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(String path, T data) {
        return new ApiResponse<>(path, data);
    }

    public static <T> ApiResponse<T> error(String path, String code, String message) {
        return new ApiResponse<>(path, code, message);
    }
}
