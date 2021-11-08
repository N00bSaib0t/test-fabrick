package pozzi.valerio.testfabrick.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pozzi.valerio.testfabrick.exceptions.BadRequestException;
import pozzi.valerio.testfabrick.exceptions.UnauthorizedException;
import pozzi.valerio.testfabrick.model.balance.Balance;
import pozzi.valerio.testfabrick.model.generic.ErrorModel;
import pozzi.valerio.testfabrick.model.generic.ResponseModel;
import pozzi.valerio.testfabrick.model.moneytransfer.*;
import pozzi.valerio.testfabrick.model.moneytransfer.FeeType;
import pozzi.valerio.testfabrick.model.moneytransfer.in.MoneyTransferIn;
import pozzi.valerio.testfabrick.model.moneytransfer.out.*;
import pozzi.valerio.testfabrick.model.transaction.Transaction;
import pozzi.valerio.testfabrick.model.transaction.TransactionList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/mockapi")
public class MockApi {

    @Autowired
    private RestTemplate restTemplate;

    @SneakyThrows
    @GetMapping("/sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance")
    public ResponseEntity getBalance(@NotNull @PathVariable("accountId") Long accountId,
                                     @NotNull @RequestHeader("Auth-Schema") String authSchema,
                                     @NotNull @RequestHeader("Api-Key") String apiKey) {

        if (checkAuthentication(authSchema, apiKey)) {
            if (accountId == 123456) {
                return returnObject(getFakeBalance());
            } else {
                throw new BadRequestException("AccountId not valid");
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    @SneakyThrows
    @GetMapping("/sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions")
    public ResponseEntity getTransactionList(@NotNull @PathVariable("accountId") Long accountId,
                                             @NotNull @RequestHeader("Auth-Schema") String authSchema,
                                             @NotNull @RequestHeader("Api-Key") String apiKey,
                                             @NotNull @RequestParam("fromAccountingDate") String fromAccountingDate,
                                             @NotNull @RequestParam("toAccountingDate") String toAccountingDate) {

        if (checkAuthentication(authSchema, apiKey)) {
            if (accountId == 123456) {
                return returnObject(getFakeTransactionList());
            } else {
                throw new BadRequestException("AccountId not valid");
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    @SneakyThrows
    @PostMapping("/sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers")
    public ResponseEntity getMoneyTransfer(@NotNull @PathVariable("accountId") Long accountId,
                                           @NotNull @RequestHeader("Auth-Schema") String authSchema,
                                           @NotNull @RequestHeader("Api-Key") String apiKey,
                                           @NotNull @Valid @RequestBody MoneyTransferIn moneyTransferIn ) {
        if (checkAuthentication(authSchema, apiKey)) {
            if (accountId == 123456) {
                return returnObject(getFakeMoneyTransfer());
            } else {
                throw new BadRequestException("AccountId not valid");
            }
        } else {
            throw new UnauthorizedException();
        }
    }


    private boolean checkAuthentication(String authSchema, String apiKey) {
        return ("S2S".equals(authSchema) && "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP".equals(apiKey));
    }

    private ResponseEntity returnObject(Object o) {
        LinkedList<ErrorModel> errors = new LinkedList<>();
        ResponseModel<Object> response = ResponseModel.builder().status("OK").payload(o).errors(errors).build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    private Balance getFakeBalance() {
        return Balance.builder()
                .date(LocalDate.parse("2018-08-17"))
                .balance(BigDecimal.valueOf(29.64))
                .availableBalance(BigDecimal.valueOf(29.64))
                .currency("EUR")
                .build();
    }

    private TransactionList getFakeTransactionList() {
        Transaction t1 = Transaction
                .builder()
                .transactionId("1331714087")
                .operationId("00000000273015")
                .accountingDate(LocalDate.parse("2019-04-01"))
                .valueDate(LocalDate.parse("2019-04-01"))
                .type(new Transaction.TransactionType("GBS_TRANSACTION_TYPE", "GBS_TRANSACTION_TYPE_0023"))
                .amount(BigDecimal.valueOf(-800))
                .currency("EUR")
                .description("BA JOHN DOE PAYMENT INVOICE 75/2017")
                .build();

        Transaction t2 = Transaction
                .builder()
                .transactionId("1331714088")
                .operationId("00000000273015")
                .accountingDate(LocalDate.parse("2019-04-01"))
                .valueDate(LocalDate.parse("2019-04-01"))
                .type(new Transaction.TransactionType("GBS_TRANSACTION_TYPE", "GBS_TRANSACTION_TYPE_0015"))
                .amount(BigDecimal.valueOf(-1))
                .currency("EUR")
                .description("CO MONEY TRANSFER FEES")
                .build();
        LinkedList<Transaction> list = new LinkedList<>();
        list.add(t1);
        list.add(t2);
        return TransactionList.builder().list(list).build();
    }

    private MoneyTransferOut getFakeMoneyTransfer() {

        return MoneyTransferOut.builder()
                .moneyTransferId("452516859427")
                .status(Status.EXECUTED)
                .direction(Direction.OUTGOING)
                .creditor(Creditor.builder()
                        .name("John Doe")
                        .account(Account.builder()
                                .accountCode("IT23A0336844430152923804660")
                                .bicCode("SELBIT2BXXX")
                                .build())
                        .address(Address.builder()
                                .address(null)
                                .city(null)
                                .countryCode(null).build())
                        .build())
                .debtor(Debtor.builder()
                        .name("")
                        .account(Account.builder()
                                .accountCode("IT61F0326802230280596327270")
                                .bicCode(null)
                                .build())
                        .build())
                .cro("1234566788907")
                .uri("REMITTANCE_INFORMATION")
                .trn("AJFSAD1234566788907CCSFDGTGVGV")
                .description("Description")
                .createdDatetime("2019-04-10T10:38:55.949+0200")
                .accountedDatetime("2019-04-10T10:38:56.000+0200")
                .debtorValueDate(LocalDate.parse("2019-04-10"))
                .creditorValueDate(LocalDate.parse("2019-04-10"))
                .amount(Amount.builder()
                        .debtorAmount(BigDecimal.valueOf(800))
                        .debtorCurrency("EUR")
                        .creditorAmount(BigDecimal.valueOf(800))
                        .creditorCurrency("EUR")
                        .creditorCurrencyDate(LocalDate.parse("2019-04-10"))
                        .exchangeRate(BigDecimal.valueOf(1))
                        .build())
                .isUrgent(false)
                .isInstant(false)
                .feeType(FeeType.SHA)
                .feeAccountId("12345678")
                .fees(List.of(
                        Fee.builder()
                                .feeCode("MK001")
                                .description("Money transfer execution fee")
                                .amount(BigDecimal.valueOf(0.25))
                                .currency("EUR")
                                .build(),
                        Fee.builder()
                                .feeCode("MK003")
                                .description("Currency exchange fee")
                                .amount(BigDecimal.valueOf(3.5))
                                .currency("EUR")
                                .build()
                ))
                .hasTaxRelief(true)
                .build();

    }

}
