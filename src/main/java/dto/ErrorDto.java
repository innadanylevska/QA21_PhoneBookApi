package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
@Setter
@Getter
@ToString
@Builder
public class ErrorDto {
    private int status;
    private String error;
    private Object message;
    private String path;
}
