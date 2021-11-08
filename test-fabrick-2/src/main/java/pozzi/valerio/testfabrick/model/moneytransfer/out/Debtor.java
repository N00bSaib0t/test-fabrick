package pozzi.valerio.testfabrick.model.moneytransfer.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pozzi.valerio.testfabrick.model.moneytransfer.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Debtor {

    private String name;
    private Account account;

}
