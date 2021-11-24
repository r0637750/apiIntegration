package BE.crypto.ApiIntegration.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Crypto {
    private final String coinsApiFrederik = "http://localhost:8081/coins/";
    private final String favoriteListApiBaljit = "http://localhost:8082/favorite-list/";
    private final String coinsApiArthur = "http://localhost:8083/api/authentication/";
    private final String nftApiJoachim = "http://localhost:8084/nft/";

    // API status
    @GetMapping("/status")
    public String getStatus() {
        return "Active";
    }

    // API  FREDERIK ---------------------------------------------------------------------------------------------------
    @PostMapping("/coin/add")
    public void addCoin(@RequestBody Object coin) {
        String url = coinsApiFrederik + "addCoin";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, coin, String.class);
    }

    @PostMapping("/coin/update/{id}")
    public void updateCoin(@PathVariable int id, @RequestBody Object coin) {
        String url = coinsApiFrederik + "update/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, coin, String.class);
    }

    @DeleteMapping("/coin/delete/{id}")
    public void deleteCoin(@PathVariable int id) {
        String url = coinsApiFrederik + "delete/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
    }

    @GetMapping("/coin/all")
    public String allCoins() {
        String url = coinsApiFrederik + "all";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    /** API  BALJIT----------------------------------------------------------------------------------------------------- */

    @GetMapping("/favorite-list/coins/all")
    public String getAllCoinsOverview() {
        String url = this.favoriteListApiBaljit + "coin/overview";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @PostMapping("/favorite-list/coins/new")
    public Object createNewFavoriteCoinList(@RequestBody Map<String, String> payload) {
        String url = this.favoriteListApiBaljit + "coin/new";
        Object res = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            res = restTemplate.postForObject(url, payload, String.class);
        } catch (Exception e) {
            res = e.getMessage();
        }
        return res;
    }

    @GetMapping("/favorite-list/all")
    public String getAllFavoriteLists() {
        String url = this.favoriteListApiBaljit + "overview";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @GetMapping("/favorite-list/coins/{id}")
    public String getAllFavoriteListsById(@PathVariable int id) {
        String url = this.favoriteListApiBaljit + "overview/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @PostMapping("/favorite-list/new/{name}")
    public Object createNewFavoriteCoinList(@PathVariable Object name) {
        String url = this.favoriteListApiBaljit + "new/" + name;
        Object res = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            res = restTemplate.postForObject(url, name, String.class);
        } catch (Exception e) {
            res = e.getMessage();
        }
        return res;
    }

    @PostMapping("/favorite-list/add-coin")
    public Object addCoinFavoriteCoinList(@RequestBody Object payload) {
        Object res = null;
        try {
            String url = this.favoriteListApiBaljit + "add-coin/";
            RestTemplate restTemplate = new RestTemplate();
            res = restTemplate.postForObject(url, payload, String.class);
        } catch (Exception e) {
            res = e.getMessage();
        }
        return res;
    }

    @DeleteMapping("/favorite-list/remove-coin/{coinId}/{listId}")
    public Object removeCoinFromFavoriteCoinList(@PathVariable long coinId, @PathVariable long listId) {
        try {
            String url = this.favoriteListApiBaljit + "remove-coin/" + coinId + "/" + listId;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(url);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Success";
    }

    /** Existing KRAKEN API */
    @GetMapping("/kraken/assets")
    public Object getPublicAssets() throws JsonProcessingException {
        String url = "https://api.kraken.com/0/public/Assets";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        Map<String, String> resultMap = jsonToHashMap(result);
        if (resultMap.get("error") == null){
            return resultMap.get("error");
        }

        Object resultSet = resultMap.get("result");
        return resultSet;
    }

    @GetMapping("/kraken/status")
    public Object getStatusKrakenApi() throws JsonProcessingException {
        String url = "https://api.kraken.com/0/public/SystemStatus";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        Map<String, String> resultMap = jsonToHashMap(result);
        if (resultMap.get("error") == null){
            return resultMap.get("error");
        }

        Object resultSet = resultMap.get("result");
        return resultSet;
    }

    // API  ARTHUR -----------------------------------------------------------------------------------------------------
    @PostMapping("/user/add")
    public void addUser(@RequestBody Object user) {
        String url = coinsApiArthur + "addCoin";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, user, String.class);
    }

    @PostMapping("/user/update/{id}")
    public void updateUser(@PathVariable int id, @RequestBody Object user) {
        String url = coinsApiArthur + "update/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, user, String.class);
    }

    @GetMapping("/user/findByName/{name}")
    public void findUserByName(@PathVariable String name, @RequestBody Object user) {
        String url = coinsApiArthur + "update/" + name;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, user, String.class);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        String url = coinsApiArthur + "delete/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
    }

    @GetMapping("/user/all")
    public String allUsers() {
        String url = coinsApiArthur + "all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);

    }

    /** Helper methods*/
    private Map<String, String> jsonToHashMap(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    // API JOACHIM -----------------------------------------------------------------------------------------------------
    @GetMapping("/nft/all")
    public String allNfts(){
        String url = nftApiJoachim + "all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("/nft/add")
    public void addNft(@RequestBody Object nft){
        String url = nftApiJoachim + "add";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, nft, String.class);
    }

    @DeleteMapping("/nft/delete/{id}")
    public void deleteNft(@PathVariable int id){
        String url = nftApiJoachim + "delete/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
    }

    @PostMapping("/nft/update/{id}")
    public void updateNft(@PathVariable int id, @RequestBody Object nft){
        String url = nftApiJoachim + "update/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, nft, String.class);
    }

}
