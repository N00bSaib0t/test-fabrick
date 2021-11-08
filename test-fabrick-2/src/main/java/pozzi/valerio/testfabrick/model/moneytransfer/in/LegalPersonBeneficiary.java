package pozzi.valerio.testfabrick.model.moneytransfer.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class LegalPersonBeneficiary {

    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

}
