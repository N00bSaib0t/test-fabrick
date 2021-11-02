package pozzi.valerio.testfabrick;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("getSaldo")
    public String getSaldo(@RequestParam("accountId") long accountId) {
        return getBalanceFromApi(accountId);
    }

    @GetMapping("getTransazioni")
    public String getTransazioni(@RequestParam("accountId") long accountId , @RequestParam("fromAccountingDate") String fromAccountingDate, @RequestParam("toAccountingDate") String toAccountingDate) {
        return getTransactionsFromApi(accountId, fromAccountingDate, toAccountingDate);
    }


    private String getBalanceFromApi(long accountId) {
        String address = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        address = address.replace("{accountId}", accountId + "");
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Auth-Schema", "S2S");
            conn.addRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
            conn.addRequestProperty("accountId", accountId + "");
            conn.connect();

            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                Gson gson = new Gson();
                Map map = gson.fromJson(String.valueOf(content), Map.class);
                String balance = String.valueOf(map.get("payload"));
                System.out.println(balance);
                return balance;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    private String getTransactionsFromApi(long accountId, String fromAccountingDate, String toAccountingDate) {
        String address = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts?accountId={accountId}&fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
        address = address.replace("{accountId}", accountId + "");
        address = address.replace("{fromAccountingDate}", fromAccountingDate + "");
        address = address.replace("{toAccountingDate}", toAccountingDate + "");
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Auth-Schema", "S2S");
            conn.addRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
            conn.addRequestProperty("accountId", accountId + "");
            conn.connect();

            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                Gson gson = new Gson();
                Map map = gson.fromJson(String.valueOf(content), Map.class);
                String transactions = String.valueOf(map.get("payload"));
                System.out.println(transactions);
                return transactions;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }


}
