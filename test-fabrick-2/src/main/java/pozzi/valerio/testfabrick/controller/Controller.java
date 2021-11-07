package pozzi.valerio.testfabrick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pozzi.valerio.testfabrick.model.generic.ResponseModel;
import pozzi.valerio.testfabrick.model.balance.Balance;
import pozzi.valerio.testfabrick.model.transaction.TransactionList;

@RestController
@RequestMapping("/controller")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{accountId}/balance")
    public ResponseModel<Balance> getBalance(@PathVariable("accountId") String accountId) {

        // String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        String url = "http://localhost:8080/mockapi/sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        url = url.replace("{accountId}", accountId);

        HttpEntity<String> entity = new HttpEntity<>(null, getHeaders());

        ResponseEntity<ResponseModel<Balance>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ResponseModel<Balance>>() {
                }
        );

        return response.getBody();
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseModel<TransactionList> getTransactions(@PathVariable("accountId") String accountId,
                                                          @RequestParam("fromAccountingDate") String fromAccountingDate,
                                                          @RequestParam("toAccountingDate") String toAccountingDate) {

        //  String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
        String url = "http://localhost:8080/mockapi/sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
        url = url.replace("{accountId}", accountId);
        url = url.replace("{fromAccountingDate}", fromAccountingDate);
        url = url.replace("{toAccountingDate}", toAccountingDate);

        HttpEntity<String> entity = new HttpEntity<>(null, getHeaders());
        ResponseEntity<ResponseModel<TransactionList>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ResponseModel<TransactionList>>() {
                }
        );

        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        return headers;
    }

}
