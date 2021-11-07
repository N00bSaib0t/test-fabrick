package pozzi.valerio.testfabrick.model.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorModel {

    private String fieldName;
    private String objectName;
    private String defaultMessage;
    private String code;
    private String description;
    private String params;

}
