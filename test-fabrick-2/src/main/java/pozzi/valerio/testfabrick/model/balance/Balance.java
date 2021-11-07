package pozzi.valerio.testfabrick.model.balance;


/*TODO:
-change date to correct date format (LocalDate? ZonedDateTime?);
-balance and availableBalance to BigDecimal;
-currency to enum;
-add custom annotation for validation(?)
*/

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    private String date;
    private String balance;
    private String availableBalance;
    private String currency;

}
