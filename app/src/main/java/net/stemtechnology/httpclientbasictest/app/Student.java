package net.stemtechnology.httpclientbasictest.app;

/**
 * Created by karthik on 7/24/14.
 */
public class Student {
    private String name;
    private int age;

    private int grade;
    private int id;


    private String email;
    public Student(String name, int age, int grade, int id, String email){
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.id = id;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {return grade; }
    public int getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }
}

