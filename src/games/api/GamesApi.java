package games.api;

import java.util.Arrays;
import java.util.List;

import io.github.piotrkluz.models.Game;
import io.github.piotrkluz.models.User;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;

public class GamesApi {
    private String baseUrl;
    private String usersUrl;
    private String gamesUrl;

    public GamesApi(String baseUrl) {
        this.baseUrl = baseUrl;
        this.usersUrl = baseUrl + "/users";
        this.gamesUrl = baseUrl + "/games";
    }

    public List<Game> getGames() {
       return getList(baseUrl + "/games", Game[].class);
    }

    public List<User> getUsers() {
        return getList(usersUrl, User[].class);
    }

    public User getUser(String name) {
        return getList(usersUrl + "?Name=" + name, User[].class).get(0);
    }

    public User getUser(int id) {
        return given().get(usersUrl + "/" + String.valueOf(id)).as(User.class);
    }

    public User addUser(User user) {

        return given()
                .header("content-type", "application/json")
                .body(user)
                .post(usersUrl)
                .as(User.class);
    }

    public User updateBalance(int userId, float updateBalance) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("Balance", updateBalance);

        return given()
                .header("content-type", "application/json")
                .body(requestParams.toJSONString())
                .patch("http://localhost:3000/users/" + userId )
                .as(User.class);
    }

    private <T> List<T> getList(String url, Class<T[]> target) {
        return Arrays.asList(given().get(url).as(target));
    }
}