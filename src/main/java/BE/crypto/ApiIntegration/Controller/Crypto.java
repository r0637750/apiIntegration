package BE.crypto.ApiIntegration.Controller;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class Crypto {
    private final String coinsApiFrederik = "http://localhost:8081/coins/";
    private final String favoriteListApiBaljit = "http://localhost:8082/favorite-list/";
    private final String coinsApiArthur = "http://localhost:8083/api/authentication";


    // API  FREDERIK ---------------------------------------------------------------------------------------------------
    @PostMapping("/coin/add")
    public void addCoin (@RequestBody Object coin){
        String url = coinsApiFrederik + "addCoin";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, coin,String.class);
    }

    @PostMapping("/coin/update/{id}")
    public void updateCoin (@PathVariable int id, @RequestBody Object coin){
        String url = coinsApiFrederik + "update/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, coin,String.class);
    }

    @DeleteMapping("/coin/delete/{id}")
    public void deleteCoin (@PathVariable int id){
        String url = coinsApiFrederik + "delete/" + id ;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
    }

    @GetMapping("/coin/all")
    public String allCoins (){
        String url = coinsApiFrederik + "all";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    // API  BALJIT -----------------------------------------------------------------------------------------------------

    @GetMapping("favorite-list/all")
    public String getAllFavoriteLists (){
        String url = this.favoriteListApiBaljit + "overview";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @GetMapping("favorite-list/{id}")
    public String getAllFavoriteListsById (){
        String url = coinsApiFrederik + "/overview/{id}";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }







    // API  ARTHUR -----------------------------------------------------------------------------------------------------
    @PostMapping("/user/add")
    public void addUser (@RequestBody Object user){
        String url = coinsApiArthur + "addCoin";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, user,String.class);
    }

    @PostMapping("/user/update/{id}")
    public void updateUser (@PathVariable int id, @RequestBody Object user){
        String url = coinsApiArthur + "update/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, user,String.class);
    }

    @GetMapping("/user/findByName/{name}")
    public void findUserByName (@PathVariable String name, @RequestBody Object user){
        String url = coinsApiArthur + "update/" + name;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, user,String.class);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser (@PathVariable int id){
        String url = coinsApiArthur + "delete/" + id ;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
    }

    @GetMapping("/user/all")
    public String allUsers (){
        String url = coinsApiArthur + "all";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);

    }

}
