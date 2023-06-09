package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkhttp {
    private  final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    @Test
    public void loginTest() throws IOException {
        //AuthRequestDto request
        AuthRequestDto auth = AuthRequestDto.builder()
                //moy login
                .username("innassik10@gmail.com")
                .password("1234!NNa")
                .build();
        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
            .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
            .post(body).build();
        Response response = client.newCall(request).execute();//client c ok http client = new i exeute alt enter
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        AuthResponseDto responseDto = gson.fromJson(response.body().string(), AuthResponseDto.class);
        System.out.println(responseDto.getToken());

        //error
//        ErrorDto errorDto = gson.fromJson(request.body().toString(), ErrorDto.class);
//        System.out.println(errorDto.getMessage());
//
//        Assert.assertEquals(errorDto.getError(),"Unauthorized");
//        Assert.assertEquals(errorDto.getMessage(),"Login or Password incorrect");

    }
    @Test
    public void loginWithWrongEmailTest() throws IOException {

        AuthRequestDto auth = AuthRequestDto.builder()
                .username("manuel+44gmail.com")
                .password("Manuel412345$")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body).build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),401);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        System.out.println(errorDto.getMessage());

        Assert.assertEquals(errorDto.getError(),"Unauthorized");
        Assert.assertEquals(errorDto.getMessage(),"Login or Password incorrect");
    }

    // eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWFudWVsKzRAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODE5ODIwNDMsImlhdCI6MTY4MTM4MjA0M30.gqsFTURMKE5vb7_-P8EM5OiksH8-oSYjP5b0YGaOa0A
    @Test
    public void loginWithAbsentEmailTest() throws IOException {

        AuthRequestDto auth = AuthRequestDto.builder()
                .username(" ")
                .password("Manuel412345$")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body).build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),401);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        System.out.println(errorDto.getMessage());

        Assert.assertEquals(errorDto.getError(),"Unauthorized");
        Assert.assertEquals(errorDto.getMessage(),"Login or Password  incorrect");
    }

}
