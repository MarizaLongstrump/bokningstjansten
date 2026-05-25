package com.mariza.hotel.dto.guest;

//

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// request använder by the controle
// request är de input data som användare ger till systemet
// därför här ska man göra validering
public class CreateGuestRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\- ]+$", message = "Invalid name")
    String firstName;
    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\- ]+$", message = "Invalid name")
    String lastName;
    @NotBlank
    @Email
    @Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "invalid e-mail adress")
    String email;
    @NotBlank
    @Pattern(regexp = "^\\+[0-9]{1,4}$", message = "Invalid prefix")
    String prefix;
    @NotBlank
    @Pattern(regexp = "^[0-9+\\- ]{6,20}$", message = "Invalid phone number")
    String telephone;
    @NotBlank
    String nationality;

    public CreateGuestRequest() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
