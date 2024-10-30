package com.example.thymeleaf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentDTO {

    private String id;
    private String name;
    private String email;
    private String address;  // Update field to match address data structure if necessary

    // Getters and Setters for all fields
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
