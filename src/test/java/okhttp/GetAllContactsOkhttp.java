package okhttp;

import com.google.gson.Gson;
import dto.AllContactsDto;
import dto.ContactDto;
import dto.ErrorDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllContactsOkhttp {
    Gson gson = new Gson();//
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiaW5uYXNzaWsxMEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY4MjQwNTk4OCwiaWF0IjoxNjgxODA1OTg4fQ.sQWzP9S2zSMeQNeZ_zTszM5ZuZAGPhbgh2XoYj_YGZc";
    //get id
    //70c7995d-228b-4dce-996b-576a936cbaf0
    @Test
    public void getAllContactsSuccessTest() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .get().build();
        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        AllContactsDto allContacts = gson.fromJson(response.body().string(), AllContactsDto.class);

        List<ContactDto> contacts = allContacts.getContacts();

        for (ContactDto contact: contacts) {
            System.out.println(contact.getId());
            System.out.println("==================================================");
        }
    }

    @Test
    public void getAllContactsNegativeTest() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)//data to header
                .get().build();//get v sagger
        Response response = client.newCall(request).execute();//send with httpClient lib
//answer in response
        Assert.assertEquals(response.code(),401);

        //gson string iz json videlyat
        //iz responsa nugen body.string(),kotoryi propisali v ErrorDTO.class
        ErrorDto error = gson.fromJson(response.body().string(), ErrorDto.class);


        //Assert
    }
}

