package okhttp;

import com.google.gson.Gson;
import com.sun.source.tree.AssertTree;
import dto.ContactDto;
import dto.ContactResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class AddContactTestsOkhttp {
    //Contact was added! ID: 484678f5-d6fb-4fb0-be39-a11ddf6c2929
    //https://contactapp-telran-backend.herokuapp.com/swagger-ui/index.html?fbclid=IwAR3SizB12aEfS0PDaUmhypRQDZ28VOWQVtlCUNcZCPdrHSfHphLjVoOlChw#/Contact%20Controller/getContacts
    private  final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiaW5uYXNzaWsxMEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4MjQwNTk4OCwiaWF0IjoxNjgxODA1OTg4fQ.sQWzP9S2zSMeQNeZ_zTszM5ZuZAGPhbgh2XoYj_YGZc";

    @Test
    public void addContactSuccessTest() throws IOException {
        //+i
        int i = new Random().nextInt(1000)+1000;//java util
        ContactDto contactDto = ContactDto.builder().name("4"+i+"Oliver").lastName("4"+i+"Kan")
                .email("4kan"+i+"@gm.co").phone(i+"456789876")
                .address("4"+i+"Berlin").description("goalkeeper").build();

        RequestBody body = RequestBody.create(gson.toJson(contactDto),JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .post(body).build();
// ID: 5989aae0-4811-41ab-a43f-7534fab1b831
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ContactResponseDto contactResponse = gson.fromJson(response.body().string(), ContactResponseDto.class);
        System.out.println(contactResponse.getMessage());
        Assert.assertTrue(contactResponse.getMessage().contains("Contact was added"));
    }

    @Test
    public void addContactNeganiveWithoutNameTest() throws IOException {
        //name("4"+i+"Oliver") except
        //int i = new Random().nextInt(1000)+1000;//java util
        ContactDto contactDto = ContactDto.builder().lastName("4Kan")
                .email("4kan@gm.co").phone("4123456789876")
                .address("4Berlin").description("4goalkeeper").build();

        RequestBody body = RequestBody.create(gson.toJson(contactDto),JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .post(body).build();
// ID: 5989aae0-4811-41ab-a43f-7534fab1b831
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),400);//assertTrue(response.isSuccessful()
// kakoy message v error dto
        ErrorDto error = gson.fromJson(response.body().string(), ErrorDto.class);
        //System.out.println(error.getMessage());
       Assert.assertEquals(error.getMessage().toString(),"{name=must not be blank}");
    }
    @Test
    public void addSameContactNeganiveTest() throws IOException {
        // except
        //int i = new Random().nextInt(1000)+1000;//java util
        ContactDto contactDto = ContactDto.builder().name("4Oliver").lastName("4Kan")
                .email("4kan@gm.co").phone("4123456789876")
                .address("4Berlin").description("4goalkeeper").build();

        RequestBody body = RequestBody.create(gson.toJson(contactDto),JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .post(body).build();
// ID: 5989aae0-4811-41ab-a43f-7534fab1b831
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),400);//assertTrue(response.isSuccessful()
// kakoy message v error dto
        ErrorDto error = gson.fromJson(response.body().string(), ErrorDto.class);
        System.out.println(error.getMessage());
       Assert.assertEquals(error.getMessage().toString(),"{name=must not be blank}");
    }//ctrl+d copy
    //409 c id bug
    //401 token bug
    //bez id @mail

    //hw for guest i put
}
