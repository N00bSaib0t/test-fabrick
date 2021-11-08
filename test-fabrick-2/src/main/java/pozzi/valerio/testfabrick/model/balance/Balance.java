package pozzi.valerio.testfabrick.model.balance;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    private LocalDate date;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private String currency;

}
