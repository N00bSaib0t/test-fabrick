package pozzi.valerio.testfabrick.model.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.LinkedList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel<T> {

    private String status;
    private LinkedList<ErrorModel> errors;
    private T payload;

}
