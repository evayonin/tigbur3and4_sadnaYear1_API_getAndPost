// תגבור 3 - בקשת פוסט 3/6/25

//2
package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//(שדות שבחרנו לא להשתמש בהם)נוטציה שמתעלמת משדות שלא קיימים.
// ידלג בג׳ייסון על השדות שלא קיימים כאן. מונע קריסה.
// נלמד נוטציות שנה הבאה
@JsonIgnoreProperties(ignoreUnknown = true)

public class Joke {
  // השדות הרלוונטיים שנרצה
  public String category;
  public String type;
  public String joke;
  public String setup;
  public String delivery;
  public String lang;
  // אביה עשתה אותם פבליק כדי לא להשתמש בגטרים (לחסוך זמן)

  @Override
  public String toString() {
    return "Joke{" +
        "category='" + category + '\'' + "\n" +
        ", type='" + type + '\'' + "\n" +
        ", joke='" + joke + '\'' + "\n" +
        ", setup='" + setup + '\'' + "\n" +
        ", delivery='" + delivery + '\'' + "\n" +
        ", lang='" + lang + '\'' + "\n" +
        '}';
  }
}