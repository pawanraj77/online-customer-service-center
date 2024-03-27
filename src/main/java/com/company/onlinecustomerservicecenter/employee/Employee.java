package com.company.onlinecustomerservicecenter.employee;

import com.company.onlinecustomerservicecenter.issue.Issue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************************************
 *          @author             Pawan Raj
 *          Description         It is a POJO class that represents an employee in the system.
 *                              employee contain cdsId, firstName, lastName, phoneNo, email,
 *                              password and city.
 *          Version             3.2.2
 *          Created Date        10-feb-2024
 *****************************************************************************************/

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer cdsId;

    @NotBlank(message = "First Name cant be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z ]{3,10}", message = "First Name should contain min 3 & max 10 chars, no digits and special chars allowed.")
    private String firstName;

    @NotBlank(message = "Last Name cant be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z ]{3,10}", message = "Last Name should contain min 3 & max 10 chars, no digits and special chars allowed.")
    private String lastName;

    @NotBlank(message = "mobileNumber is required")
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{10}$", message = "Phone no should contain exact 10 digits.")
    private String phoneNo;

    @Email(message = "Please provide valid email. e.g name@ford.com")
    private String email;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters")
    private String password;

    @NotBlank(message = "City cant be null, it should contain chars")
    private String city;


//    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Issue> issues = new ArrayList<>();

    public Employee(Integer cdsId, String firstName, String lastName, String phoneNo,
                    String email, String password, String city, List<Issue> issues) {
        super();
        this.cdsId = cdsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.city = city;
        this.issues = issues;
    }



    public Integer getCdsId() {
        return cdsId;
    }

    public void setCdsId(Integer cdsId) {
        this.cdsId = cdsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Employee() {

    super();
    }
}
