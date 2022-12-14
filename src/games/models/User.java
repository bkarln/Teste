package games.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class User {
    public int id;

    @JsonAlias("Name")
    public String name;

    @JsonAlias("Balance")
    public float balance;

    @JsonAlias("Currency")
    public String currency;

    public String likes;

    @JsonAlias("High-Roller")
    public Boolean highRoller;
}