package games.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.List;

@Data
public class Game {
    public int id;

    @JsonAlias("Name")
    public String name;

    @JsonAlias("Type")
    public String type;

    @JsonAlias("Currency")
    public String currency;

    @JsonAlias("Stake")
    public List<Float> stake;

    @JsonAlias("StakesThisWeek")
    public int stakesThisWeek;
}