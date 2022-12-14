package games.utils;

import io.github.piotrkluz.api.GamesApi;
import io.github.piotrkluz.models.Game;
import io.github.piotrkluz.models.User;
import io.github.piotrkluz.utils.Utils;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTests {
    private GamesApi api = new GamesApi("http://localhost:3000");

    @Test
    @DisplayName("O usuário com o saldo mais alto, independentemente da moeda, é o único usuário com o campo \"high-roller\" definido como verdadeiro")
    public void highRollerFieldSet() {
        List<User> users = api.getUsers();
        Optional<User> maxUserOptional = users.stream().max((u1, u2) -> Float.compare(u1.balance, u2.balance));
        assertTrue(maxUserOptional.isPresent(), "O usuário com saldo máximo deve ser encontrado.");
        User maxUser = maxUserOptional.get();

        List<User> wrongHighRollerUsers = users.stream()
                .filter(u -> u != maxUser)
                .filter(u -> u.highRoller != null && u.highRoller)
                .collect(Collectors.toList());

        assertTrue(maxUser.highRoller, "O usuário com a maior quantia deve ter o campo High-Roller definido.\n" + maxUser.toString());
        assertEquals(wrongHighRollerUsers.size(), 0,
                "Todos os outros usuários devem ter o campo highRoller definido como falso. A seguir, definiu como verdadeiro: "
                        + Utils.listToString(wrongHighRollerUsers));
    }

    @Test
    @DisplayName("Certifique-se de que todos os usuários tenham um saldo em libras esterlinas e converta quaisquer saldos e códigos de moeda que não sejam")
    public void userBalances() {
        List<User> users = api.getUsers();

        List notGBPUsers = users.stream().filter(u -> !u.currency.equals("GBP")).collect(Collectors.toList());
        assertTrue(
                notGBPUsers.size() == 0,
                () -> "Não deve haver usuários com moeda diferente de GBP. Os seguintes usuários estão errados: " + Utils.listToString(notGBPUsers)
        );
    }

    @Test
    @DisplayName("Processe um depósito (atualize o saldo) para Brian de 20.000 da moeda do usuário")
    public void updateBalance() {
        int updateBalance = 20000;
        String name = "Brian";

        User brian = api.getUser(name);
        api.updateBalance(brian.id, updateBalance);

        assertEquals(updateBalance, api.getUser(brian.id).balance, name + " deve ter atualizado o saldo para: " + updateBalance);
    }

    @Test
    @DisplayName("Registre um novo cliente.")
    public void registerNewCustomer() {
        User james = new User();
        james.name = "James";
        james.balance = 20;
        james.currency = "GBP";
        james.likes = "Bingo";
        james.highRoller = false;

        User addedResponse = api.addUser(james);
        james.id = addedResponse.id; //Update model ID

        assertEquals(james, addedResponse);
        assertEquals(james, api.getUser(addedResponse.id));
    }

    @Test
    @DisplayName("Produza uma lista dos jogos mais populares desta semana, do mais popular ao menos popular.")
    public void logMostPopularGames() {
        List<Game> list = api.getGames().stream()
                .sorted((o1, o2) -> Integer.compare(o2.stakesThisWeek, o1.stakesThisWeek))
                .collect(Collectors.toList());
        System.out.println("JOGOS MAIS POPULARES: ");
        list.forEach(g -> System.out.println(g.name + ": POPULARIDADE:" + g.stakesThisWeek));
    }

    @Test
    @DisplayName("O departamento de marketing pretende executar uma campanha no Starburst, produzir uma lista de clientes-alvo com base em seus gostos.")
    public void customersByLikes() {
        api.getUsers().stream().map(u -> u.likes).distinct().collect(Collectors.toList());

        Map<String, List<User>> usersByLikes = api.getUsers().stream()
                .collect(Collectors.groupingBy(u -> u.likes));

        System.out.println("ESTATÍSTICAS DOS JOGOS MAIS POPULARES: ");

        usersByLikes.forEach((likes, users) -> {
            System.out.println("\nUSERS likes: " + likes);
            users.forEach(u -> System.out.println(u.toString()));
        });
    }

    @Test
    @DisplayName("Produzir uma lista para cada usuário mostrando quantos giros eles podem fazer em cada jogo, em cada nível de aposta disponível.")
    public void usersStakesStatistics() {
        api.getUsers().forEach(user -> {
            System.out.println("\n\n-------------------------------------\n\nUSER: " + user.name);
            api.getGames().forEach(g -> {
                System.out.println("\nApostas no JOGO: " + g.name);
                g.stake.forEach(s -> {
                    System.out.println(s + " ---> " + user.balance / s);
                });
            });
        });
    }
}
