package com.jonah.vttp5_ssf_day03ws.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public class Person {

    @NotEmpty(message = "name necessary")
    @Size(min = 3, max = 65, message = "name between 3 and 65")
    private String firstName;

    @Email(message = "email format!!")
    @NotBlank(message = "email needed")
    private String email;

    @NotNull(message = "phone number required!")
    @Min(80000000)
    @Max(99999999)
    private long phoneNumber;

    @NotNull(message = "date of birth required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    

    public Person() {
    }

    public Person(String firstName, String email, long phoneNumber, Date dob) {
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dob=" + dob
                + "]";
    }
    
    

    

    
}
