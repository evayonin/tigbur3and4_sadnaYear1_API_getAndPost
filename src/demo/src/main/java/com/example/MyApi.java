// תגבור 3 - בקשת פוסט 3/6/25

//1
package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyApi {
    public static final int PROGRAMMING = 1;
    public static final int DARK = 2;
    public static final int ANY = 3;

    // any
    // Programming
    // Misc
    // Dark
    // Pun
    // Spooky
    // Christmas

    // httpClient - שליח
    // HttpRequest - המכתב , הבקשה
    // HttpResponse - התשובה שחוזרת , התגובה
    // ObjectMapper - להמיר ממחרוזת לג'ייסון (אובייקט)
    static HttpClient httpClient = HttpClient.newHttpClient(); // לשלוח את הבקשה
    // במחלקה של הקליינט יש מתודה סטטית שמאפרת ליצור קליינט חדש שעל ידו נשלח את
    // הבקשה.
    // עשינו אותו סטטי כדי שנוכל להשתמש בו גם במחלקות אחרות מבלי ליצור אובייקט

    public static void main(String[] args) throws IOException, InterruptedException {
        // https://v2.jokeapi.dev
        // אם שמים רק את זה אפשר לראות את האתר ואת המבנה של השרת כדי לדעת איך להשתמש
        // (send request)

        Scanner scanner = new Scanner(System.in);
        System.out.println("choose category" + "\n" + "1) Programming"
                + "\n" + "2) Dark" + "\n" + "3) Any");
        int categoryId = scanner.nextInt();

        String category = getMyCategory(categoryId);

        String result = getJokeByUrl("https://v2.jokeapi.dev/joke/" + category); // גוף הבקשה שחזר

        // int indexOf = result.indexOf("category"); // לא טוב! האינדקס יכול להשתנות

        // מבנה של ג׳ייסון זה שדה:ערך

        // אם נעשה מחלקה שתדע להכיל את השדות של הג׳ייסון שחזר לנו לא נצטרך לנחש מה
        // האינדקס של השדה שאנחנו צריכים.

        // אז נמיר את התגובה(ג׳ייסון) לאובייקט של המחלקה שיצרנו עם השדות הרלווניטיים של
        // הג׳ייסון (שבמבנה הגוף של התגובה):
        ObjectMapper mapper = new ObjectMapper(); // ימיר מה שחזר (שקבענו כגייסון) לאובייקט

        // יצירת האובייקט - ימפה את שדות הג׳ייסון לערכיהם של שדות המחלקה בהתאמה לפי שמות
        // השדות. (הערכים יתקבלו מהתגובה).

        Joke joke = mapper.readValue(result, Joke.class); // נותנים לו את התוצאה שימיר אותה לסוג האובייקט של המחלקה
                                                          // ג׳וק.
        // ריד וליו זה כאשר אנחנו רוצים להמיר ג׳ייסון לאובייקט - יקרא את הווליו(ערך) של
        // הג׳ייסון ויהפוך את זה לאובייקט.
        // המתודה קוראת את הערך של הג׳ייסון ריזולט וממירה אותו לאובייקט ג׳וק.
        // System.out.println(joke); // לפי טו סטרינג
        if (joke.type == "single")
            System.out.println(joke.joke); // ידפיס את הערך שחזר בשדה הזה ע״י ריד וליו
        else
            System.out.println(joke.setup + "\n" + joke.delivery);// twhopart
    }

    private static String getMyCategory(int categoryId) { // גם צריכה להיות סטטית כי סטטית קוראת לסטטית
        return switch (categoryId) {
            case PROGRAMMING -> "Programming";
            case DARK -> "Dark";
            default -> "any";
        };
    }

    // גם צריכה להיות סטטית כי סטטית קוראת לסטטית
    private static String getJokeByUrl(String url) throws IOException, InterruptedException { // זריקה בגלל מתודת סנד
        // המתודה תיצור לנו את הבקשה , נשלח את הבקשה ונתפוס את מה שהיא מחזירה

        HttpRequest request = HttpRequest.newBuilder()
                // אומר לאיזו כתובת המכתב (הבקשה) מיועד
                .uri(URI.create(url)) // המרת הסטרינג של הקיצור ששלחנו לנתיב (קישור אמיתי)
                // סוג הבקשה
                .GET()
                // פטנקציה שבונה את הבקשה
                .build();

        // עכשיו נשלח את המכתב שיצרנו ע״י השליח ונגיד מה אנחנו רוצים לקבל כתשובה.

        // נרצה אותה כסטרינג (גנרי) כדי שנוכל לעבוד איתה
        HttpResponse<String> response = httpClient.send(
                // מתודה סנד של קליינט מצפה לקבל את הבקשה ואת סוג התשובה שנרצה
                request,
                HttpResponse.BodyHandlers.ofString());

        // System.out.println(response.body()); // בדיקה
        // נחזיר כבאדי כדי שיחזיר את התוכן של הבקשה ולא סתם רקווסט שהיה מחזיר את קוד
        // הסטטוס ( 200 ומשהו אם הצליח)
        return response.body(); // בכללי הבאדי זה המידע שאפשר לגשת אליו שחשוף בלינק
    }
}