package com.example.hw2;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String city;
    private String country;
    private String profileImage;

    public User(String firstName, String lastName, int age, String email, String city, String country, String profileImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.city = city;
        this.country = country;
        this.profileImage = profileImage;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
    public String getProfileImage() {
        return profileImage;
    }
}