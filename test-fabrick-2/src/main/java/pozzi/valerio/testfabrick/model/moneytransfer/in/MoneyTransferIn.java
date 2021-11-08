package pozzi.valerio.testfabrick.model.moneytransfer.in;

import lombok.*;
import pozzi.valerio.testfabrick.model.moneytransfer.Creditor;
import pozzi.valerio.testfabrick.model.moneytransfer.FeeType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class MoneyTransferIn {

    @Valid
    @NotNull
    private Creditor creditor;
    private String executionDate;
    private String uri;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private FeeType feeType;
    private String feeAccountId;
    @Valid
    private TaxRelief taxRelief;

}



