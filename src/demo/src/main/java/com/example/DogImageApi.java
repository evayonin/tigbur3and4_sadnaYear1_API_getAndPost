// תגבור 3 - בקשת פוסט 3/6/25

//3
package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DogImageApi {
  static HttpClient httpClient = HttpClient.newHttpClient();

  public static void main(String[] args) throws IOException, InterruptedException {
    // אותו מבנה:
    String url = "https://dog.ceo/api/breeds/image/random";
    String result = fetchDogImage(url);
    ObjectMapper mapper = new ObjectMapper();
    DogImage image = mapper.readValue(result, DogImage.class); // שדה מסג׳
    System.out.println(image); // טו סטרינג

    // ניצור את התמונה על ידי התגובה:

    BufferedImage bufferedImage = ImageIO.read(new URL(image.message)); // יצירת תמונה ע״י הערך של השדה מסג׳ (שהוא נתיב
                                                                        // כסטרינג)
    File myImage = new File("src/demo/src/main/resources/Dog.png"); // איפה ניצור את התמונה

    ImageIO.write(bufferedImage, "png", myImage);
    // יצירת התמונה שבבאפרד אימג׳ כפי אן ג׳י לתוך מיי אימג׳ שממוקמת בריסורסס

  }

  // גם צריכה להיות סטטית כי סטטית קוראת לסטטית
  private static String fetchDogImage(String url) throws IOException, InterruptedException { // אותו מבנה
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(
        request,
        HttpResponse.BodyHandlers.ofString());
    return response.body();
  }
}
