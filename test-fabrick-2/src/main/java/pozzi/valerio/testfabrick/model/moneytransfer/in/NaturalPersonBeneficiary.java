package pozzi.valerio.testfabrick.model.moneytransfer.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class NaturalPersonBeneficiary {

    @NotNull
    private String fiscalCode1;
    private String fiscalCode2;
    private String fiscalCode3;
    private String fiscalCode4;
    private String fiscalCode5;

}
