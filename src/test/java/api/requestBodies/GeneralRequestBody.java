package api.requestBodies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class GeneralRequestBody<T> {

    @Builder.Default
    private String jsonrpc = "2.0";
    @Builder.Default
    private int id = 146806551;
    public String method;
    public T params;

}
