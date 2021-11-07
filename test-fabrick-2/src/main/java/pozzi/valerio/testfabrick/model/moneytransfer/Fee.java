package pozzi.valerio.testfabrick.model.moneytransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fee {

    private String feeCode;
    private String description;
    private BigDecimal amount;
    private String currency;

}
