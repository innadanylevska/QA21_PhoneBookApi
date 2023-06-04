package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.ContactResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class DeleteContactByIdTestsOkhttp {
    private  final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiaW5uYXNzaWsxMEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4MjQwNTk4OCwiaWF0IjoxNjgxODA1OTg4fQ.sQWzP9S2zSMeQNeZ_zTszM5ZuZAGPhbgh2XoYj_YGZc";
//pecondition add i get id i v test zabirat i dobavlyat v samom classe
    String id;
    @BeforeMethod
    public void precondition() throws IOException {
        //+i
        int i = new Random().nextInt(1000) + 1000;//java util
        ContactDto contactDto = ContactDto.builder().name("Oliver").lastName("Kan")
                .email("4kan" + i + "@gm.co").phone("4" + i + "478876")
                .address("Berlin").description("goalkeeoper").build();

        RequestBody body = RequestBody.create(gson.toJson(contactDto), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(body).build();
// ID: 5989aae0-4811-41ab-a43f-7534fab1b831
        Response response = client.newCall(request).execute();

        //iz resp json my
        ContactResponseDto contactResponse = gson.fromJson(response.body().string(), ContactResponseDto.class);
        String message = contactResponse.getMessage();//делим на две часи Ж ш пробел в стринг
//System.out.println(contactResponse.getMessage());
        String[] split = message.split(": ");
        id = split[1];
    }
    @Test
    public void deleteContactByIdSuccesses() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/"+id)
                .addHeader("Authorization",token)
                .delete().build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        //print
        ContactResponseDto contactResponseDto = gson.fromJson(response.body().string(), ContactResponseDto.class);
        System.out.println(contactResponseDto.getMessage());
        //"Contact was deleted!"
        Assert.assertEquals(contactResponseDto.getMessage(),"Contact was deleted!");
       // System.out.println(errorDto.getError());//for negative and gson.from

    }

}
