// תגבור 4 - בקשת פוסט 17/6/25

package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//בקשת גט- מה שבקישור . חשוף למשתמש. מבקשים מידע מהשרת.
//בקשת פוסט(כמו הכנסת ססמה ומשתמש)-מה שלא חשוף למשתמש בלינק.
//פוסט מורכב מהד(מה שכן יהיה כתוב בלינק)ובודי(המידע מהבקשה שלא חשוף למשתמש).
//שולחים מידע לשרת שרוצים שיאחסן אותו.

public class PostApi {
  // httpClient - שליח
  // HttpRequest - המכתב , הבקשה
  // HttpResponse - התשובה שחוזרת , התגובה
  // ObjectMapper - ממירים אובייקט לג'ייסון

  static HttpClient httpClient = HttpClient.newHttpClient();

  public static void main(String[] args) throws IOException, InterruptedException {
    //
    // Scanner scanner = new Scanner(System.in);
    // System.out.println("enter your name: ");
    // String name = scanner.nextLine();
    // System.out.println("enter your email: ");
    // String email = scanner.nextLine();
    //
    // Map<String, String> data = new HashMap<>();
    // data.put("name",name);
    // data.put("email", email);
    // מפה אחת עם איבר לשם ואיבר לשם משתמש

    Student student = new Student("123456789", "Eva", 99, false);

    // בפוסט נמיר אובייקט לגייסון שזה הפוך ממה שעשינו בגט שאת הגייסון המרנו לאובייקט
    // שרוצים

    ObjectMapper objectMapper = new ObjectMapper();
    // String json = objectMapper.writeValueAsString(data); // הופכים את המידע שלנו
    // לJSON
    // את כל הדאטה של המפה העברנו לסטרינג. ייראה כמו מערך של סטרינגים של השדות
    // והערכים שלהם.
    // את האובייקט הזה שהפכנו לגייסון של סטרינג נעביר בפוסט.

    String json = objectMapper.writeValueAsString(student);

    // כדי להפוך לגייסון עושים writeValue (פה נרצה כסטרינג)
    // כדי להפוך לאובייקט עושים readValue

    HttpRequest request = HttpRequest.newBuilder()
        // הנתיב של השרת
        .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
        // הדר - קונטנט טייפ זה איך מעבירים את המידע אחרי הפסיק כהגייסון שהוא סטרינג פה
        .header("Content-Type", "application/json")
        // סוג הבקשה
        .POST(HttpRequest.BodyPublishers.ofString(json))
        // בונה את הבקשה כפוסט
        .build();

    // אותו מבנה:
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.statusCode());
    System.out.println(response.body());

  }
  // בגלל שבכללי בשרת המידע שמור בצורה של גייסון (זה גם מה שנראה בדפדפן ואם
  // נעשה גט למידע), אז כדי לפרסם מידע שרוצים שהשרת ישמור (ז"א פוסט) זה גם צריך
  // להשלח כגייסון לדוגמה כסטרינג (שיהיה אפשר לגשת אליו כסטרינג אחרכך כמו בגט) אז
  // חייב לעשות את זה עם אובג׳קט מאפר ולהכתיב את הג׳ייסון לאובייקט שרוצים לשלוח
  // לשרת (לפני ששולחים את הרקווסט (כפוסט, לפי הטייפ של הגייסון))

}
