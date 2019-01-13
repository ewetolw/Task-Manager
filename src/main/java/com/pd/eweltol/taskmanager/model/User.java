package com.pd.eweltol.taskmanager.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pd.eweltol.taskmanager.model.types.Role;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "username must not be null")
    @Size(min=5, message = "username is too short. Write min. 5 characters!")
    private String username;
    @NotNull(message= "password must not be null")
    @Size(min=8, message = "password is too short. Write min. 8 characters!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String lastName;
    private String firstName;
    @Column(unique = true)
    private String email;
    @NotNull(message= "role must not be null")
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean activated;

    public User(String username, String password, String lastName, String firstName, String email, Role role, Boolean activated) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
        this.activated = activated;
    }

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                Objects.equals(activated, user.activated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, lastName, firstName, email, role, activated);
    }

    public void updateUserData(User newUserData){
        if(newUserData.getEmail()!=null){this.setEmail(newUserData.getEmail().toUpperCase());}
        if(newUserData.getUsername()!=null){this.setUsername(newUserData.getUsername());}
        if(newUserData.getRole()!=null){this.setRole(newUserData.getRole());}
        if(newUserData.getPassword()!=null){this.setPassword(newUserData.getPassword());}
        if(newUserData.getActivated()!=null){this.setActivated(newUserData.getActivated());}
        if(newUserData.getFirstName()!=null){this.setFirstName(newUserData.getFirstName().toUpperCase());}
        if(newUserData.getLastName()!=null){this.setLastName(newUserData.getLastName().toUpperCase());}
    }
}
