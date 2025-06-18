// תגבור 4 - בקשת פוסט 17/6/25

package com.example;

public class Student {
  private String id;
  private String name;
  private int avg;
  private boolean isMale;

  public Student(String id, String name, int avg, boolean isMale) {
    this.id = id;
    this.name = name;
    this.avg = avg;
    this.isMale = isMale;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAvg() {
    return avg;
  }

  public void setAvg(int avg) {
    this.avg = avg;
  }

  public boolean isMale() {
    return isMale;
  }

  public void setMale(boolean male) {
    isMale = male;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", avg=" + avg +
        ", isMale=" + isMale +
        '}';
  }
}