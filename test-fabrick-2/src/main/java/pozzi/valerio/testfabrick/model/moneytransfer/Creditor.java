package pozzi.valerio.testfabrick.model.moneytransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Creditor {

    private String name;
    private Account account;
    private Address address;

}
