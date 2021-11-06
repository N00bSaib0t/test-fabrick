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

    String fieldName;
    String objectName;
    String defaultMessage;
    String code;
    String description;
    String params;

}
