package bg.softuni.servicesvratsa.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactBindingModel {

    @NotBlank(message = "Полето не трябва да е празно!")
    private String firstName;

    @NotBlank(message = "Полето не трябва да е празно!")
    private String lastName;

    @NotBlank(message = "Полето не трябва да е празно!")
    @Email(message = "Моля, въведете валиден имейл адрес!")
    private String email;
    @NotBlank(message = "Полето не трябва да е празно!")
    private String phoneNumber;

    @NotBlank(message = "Полето не трябва да е празно!")
    private String message;

    public ContactBindingModel() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
