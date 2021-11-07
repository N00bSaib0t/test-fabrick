package pozzi.valerio.testfabrick.model.moneytransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private String accountCode;
    private String bicCode;

}
