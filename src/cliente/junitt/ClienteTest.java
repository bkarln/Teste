/*package cliente.junitt;

import io.restassured.http.ContentType;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;


public class TestClient {

    private String url = "https://tester-global-cliente-api.herokuapp.com/"; //API NÃO FUNCIONA

    @Test
    public void putUserTest() {
        String corpoAtualizadoRequisicao = "{\n" +
                "    \"name\": \"Antonio\",\n" +
                "     \"id\": 40,\n" +
                "    \"age\": 50,\n" +
                "    \"risc\": 1\n" +
                "  }";
        String resposta = "{\"40\":" +
                "{\"name\":\"Antonio\"," +
                "\"id\":40," +
                "\"age\":50," +
                "\"risc\":1}" +
                "}";
        given().
                contentType(ContentType.JSON).
                body(corpoAtualizadoRequisicao).
                when().
                put(url + "cliente").
                then().
                statusCode(200).
                assertThat().body(containsString(resposta));

        deleteUsersTest();
    }

    @Test
    public void postUserTest() {
        String valoresRequisicao = "{\n" +
                "    \"name\": \"Antonio\",\n" +
                "    \"id\": 40,\n" +
                "    \"age\": 65,\n" +
                "    \"risc\": 1\n" +
                "  }";
        String resposta = "{\"40\":" +
                "{\"name\":\"Antonio\"," +
                "\"id\":40," +
                "\"age\":65," +
                "\"risc\":1}" +
                "}";
        given().
                contentType(ContentType.JSON).
                body(valoresRequisicao).
                when().
                post(url + "cliente").
                then().
                statusCode(201).
                assertThat().body(containsString(resposta));

    }
     @Test
        public void getCliente() {
            deleteUsersTest();
            String valorEsperado = "{}";
            when().
                    get(url).
                    then().
                    statusCode(200).
                    assertThat().body(new IsEqual(valorEsperado));
        }

    @Test
    public void getUserTest() {
        String valoresRequisicao = "{\n" +
                "    \"name\": \"Antonio\",\n" +
                "    \"id\": 40,\n" +
                "    \"age\": 65,\n" +
                "    \"risc\": 1\n" +
                "  }";
        given().
                contentType(ContentType.JSON).
                body(valoresRequisicao).
                when().
                post(url + "cliente");

        get(url + "cliente/40").then().body("name", equalTo("Antonio"));
    }

     @Test
    public void deleteUsersTest() {
        when().
                delete(url + "cliente/excluirClientes").
                then()
                .statusCode(200);
    }

    @Test
    public void deleteUserTest() {
        String valoresRequisicao = "{\n" +
                "    \"name\": \"Antonio\",\n" +
                "    \"id\": 40,\n" +
                "    \"age\": 65,\n" +
                "    \"risc\": 1\n" +
                "  }";
        given().
                contentType(ContentType.JSON).
                body(valoresRequisicao).
                when().
                post(url + "cliente");
        when().
                delete(url + "cliente/40").
                then()
                .statusCode(200);
    }
}*/