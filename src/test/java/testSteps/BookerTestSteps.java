package testSteps;

import api.bookerApi;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import java.util.Map;

import static org.junit.Assert.*;

public class BookerTestSteps {

    private static String authToken;
    private static Integer bookingId;
    private static Response response;


    @Given("^Отправляю запрос авторизации\\. Логин: '(.+)', пароль: '(.+)'$")
    public void getToken(String login, String password) {
        String requestBody = "{\n" +
                "    \"username\" : \"" + login + "\",\n" +
                "    \"password\" : \"" + password + "\"\n" +
                "}";
        response = bookerApi.auth(requestBody);
        assertEquals(200, response.statusCode());
    }

    @Then("^Проверяю, что был получен токен авторизации$")
    public void checkGetAuthToken() {
        authToken = response.jsonPath().get("token");
        assertNotNull(authToken);
        System.out.println("Получили токен авторизации: " + authToken + "\n");
    }

    @Then("^Проверяю, что не был получен токен авторизации$")
    public void checkNotGetAuthToken() {
        authToken = response.jsonPath().get("token");
        assertNull(authToken);
        String reason = response.jsonPath().get("reason");
        assertNotNull(reason);
        System.out.println("Получили отказ в авторизации с причиной: " + reason  + "\n");
    }

    @Given("^Создаю бронирование с параметрами:$")
    public void bookingCreate(Map<String, String> data) {
        String bookingCreateJsonBody = "{\n" +
                "    \"firstname\" : \"" + data.get("Имя") + "\",\n" +
                "    \"lastname\" : \""+ data.get("Фамилия") + "\",\n" +
                "    \"totalprice\" : " + data.get("Стоимость") + ",\n" +
                "    \"depositpaid\" : " + data.get("Депозит внесен") + ",\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"" + data.get("Въезд") + "\",\n" +
                "        \"checkout\" : \"" + data.get("Выезд") + "\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"" + data.get("Дополнительные потребности") + "\"\n" +
                "}";
        response = bookerApi.bookingCreate(bookingCreateJsonBody);
    }

    @Then("^Проверяю, что было создано бронирование$")
    public void checkCreateBooking() {
        assertEquals(200, response.statusCode());
        bookingId = response.jsonPath().get("bookingid");
        assertNotNull(bookingId);
        System.out.println("Создали бронирование: " + bookingId + "\n");
    }

    @Then("^Проверяю, что не было создано бронирование$")
    public void checkNotCreateBooking() {
        assertEquals(400, response.statusCode());
        System.out.println("Невозможно создать бронирование. Невалидный запрос" + "\n");
    }

    @When("^Отправляю запрос на получение текущего бронирования$")
    public void getCurrentBooking() {
        response = bookerApi.getBooking(bookingId);
    }

    @When("^Отправляю запрос на получение бронирования с id: (\\d+)$")
    public void getBooking(int bookingId) {
        response = bookerApi.getBooking(bookingId);

    }

    @Then("^Проверяю, что возвращается бронирование$")
    public void checkGetBooking() {
        assertEquals(200, response.statusCode());
        System.out.println("Получили информацию о бронировании: " + bookingId + "\n");
    }

    @Then("^Проверяю, что не возвращается бронирование$")
    public void checkNotGetBooking() {
        assertEquals(404, response.statusCode());
        System.out.println("Не найдено бронирование\n");
    }
    @When("^Обновляю текущее бронирование с параметрами:$")
    public void updateCurrentBooking(Map<String, String> data) {
        String bookingUpdateJsonBody = "{\n" +
                "    \"firstname\" : \"" + data.get("Имя") + "\",\n" +
                "    \"lastname\" : \""+ data.get("Фамилия") + "\",\n" +
                "    \"totalprice\" : " + data.get("Стоимость") + ",\n" +
                "    \"depositpaid\" : " + data.get("Депозит внесен") + ",\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"" + data.get("Въезд") + "\",\n" +
                "        \"checkout\" : \"" + data.get("Выезд") + "\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"" + data.get("Дополнительные потребности") + "\"\n" +
                "}";
        response = bookerApi.bookingUpdate(authToken, bookingId, bookingUpdateJsonBody);
    }

    @Then("^Проверяю, что было обновлено бронирование$")
    public void checkUpdateBooking() {
        assertEquals(200, response.statusCode());
        System.out.println("Обновили бронирование: " + bookingId + "\n");
    }

    @Then("^Проверяю, что не было обновлено бронирование$")
    public void checkNotUpdateBooking() {
        assertEquals(400, response.statusCode());
        System.out.println("Невозможно обновить бронирование. Невалидный запрос" + "\n");
    }

    @When("^Отправляю запрос на удаление текущего бронирования$")
    public void deleteCurrentBooking() {
        response = bookerApi.bookingDelete(authToken, bookingId);
    }

    @When("^Отправляю запрос на удаление бронирования с id: (\\d+)$")
    public void deleteBooking(int bookingId) {
        response = bookerApi.bookingDelete(authToken,bookingId);
    }

    @Then("^Проверяю, что было удалено бронирование$")
    public void checkDeleteBooking() {
        assertEquals(201, response.statusCode());
        System.out.println("Удалили бронирование: " + bookingId + "\n");
    }

    @Then("^Проверяю, что не было удалено бронирование$")
    public void checkNotDeleteBooking() {
        assertEquals(405, response.statusCode());
        System.out.println("Невозможно удалить бронирование\n");
    }


}
