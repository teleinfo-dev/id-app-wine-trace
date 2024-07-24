package id.demo.mode;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY )
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private T data;

    public Result(int status, String message, T data) {
        init(status, message, data);
    }

    protected void init(int status, String message, T data){
        this.code = status;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}