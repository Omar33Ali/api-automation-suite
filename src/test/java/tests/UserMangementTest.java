package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("User Management Workflow")
@Feature("API CRUD Operations with Reqres")
public class UserMangementTest {

    private final String baseUri = "https://reqres.in/api";
    private int userId = 2; //A static user ID (`userId = 2`) is used due to the non-persistent nature of [reqres.in](https://reqres.in).

    @Test(priority = 1)
    @Story("Create User")
    @Description("Create a new user with name and job, and assert the returned fields")
    public void createUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Omar");
        requestBody.put("job", "QA Lead");

        Response response = given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody.toString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Omar"))
                .body("job", equalTo("QA Lead"))
                .extract().response();
        // In real APIs you'd persist this, but here we'll log it only
        String createdId = response.jsonPath().getString("id");
        Allure.addAttachment("Create User Response", response.asPrettyString());
    }

    @Test(priority = 2)
    @Story("Update User")
    @Description("Update an existing user's job")
    public void updateUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("job", "Automation Architect");

        Response response = given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody.toString())
                .when()
                .put("/users/" + userId) // using static userId (2)
                .then()
                .statusCode(200)
                .body("job", equalTo("Automation Architect"))
                .extract().response();

        Allure.addAttachment("Update User Response", response.asPrettyString());
    }

    @Test(priority = 3)
    @Story("Get User")
    @Description("Fetch a user with ID = 2 and validate the response structure")
    public void getUser() {
        Response response = given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(userId))
                .extract().response();

        Allure.addAttachment("Get User Response", response.asPrettyString());
    }

    @Test(priority = 4)
    @Story("Delete User")
    @Description("Delete a user (mock behavior in Reqres)")
    public void deleteUser() {
        // This always returns 204 but has no effect on Reqres users
        given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }

    @Test(priority = 5)
    @Story("Verify User Deletion")
    @Description("Confirm user still exists after 'deletion' due to Reqres non-persistence")
    public void verifyUserDeleted() {
        // This will NOT return 404 in Reqres, since DELETE is not persisted
        Response response = given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200) // It still returns 200 even after "deletion"
                .extract().response();

        Allure.addAttachment("Verify User Response", response.asPrettyString());
    }
}