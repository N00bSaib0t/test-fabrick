package pozzi.valerio.testfabrick.model.moneytransfer.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amount {

    public BigDecimal debtorAmount;
    public String debtorCurrency;
    public BigDecimal creditorAmount;
    public String creditorCurrency;
    public LocalDate creditorCurrencyDate;
    public BigDecimal exchangeRate;

}
