package pozzi.valerio.testfabrick.model.moneytransfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyTransfer {

    private String moneyTransferId;
    private Status status;
    private Direction direction;
    private Creditor creditor;
    private Debtor debtor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private String createdDatetime;
    private String accountedDatetime;
    private LocalDate debtorValueDate;
    private LocalDate creditorValueDate;
    private Amount amount;
    private boolean isUrgent;
    private boolean isInstant;
    private FeeType feeType;
    private String feeAccountId;
    private List<Fee> fees;
    private boolean hasTaxRelief;

}



