// תגבור 3 - בקשת פוסט 3/6/25

//4
package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // כי לא רוצים את השדה האחר סטטוס (יש2סהכ)

public class DogImage {
  public String message; // שדה עבור השדה מסג׳ שהשרת מחזיר שזה הנתיב של התמונה

  @Override
  public String toString() {
    return "DogImage{" +
        "message='" + message + '\'' +
        '}';
  }
}
