package pozzi.valerio.testfabrick.model.moneytransfer.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pozzi.valerio.testfabrick.model.moneytransfer.BeneficiaryType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class TaxRelief {

    private String taxReliefId;
    @NotNull
    private Boolean isCondoUpgrade;
    @NotNull
    private String creditorFiscalCode;
    @NotNull
    private BeneficiaryType beneficiaryType;
    @Valid
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    @Valid
    private LegalPersonBeneficiary legalPersonBeneficiary;

}
