package bg.softuni.servicesvratsa.model.binding;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

public class UserRegisterBindingModel {

    @Column(unique = true)
    @NotBlank(message = "Полето не трябва да е празно!")
    @Length(min = 3, max = 20, message = "Потребителското име трябва да е дълго между 3 и 20 символа!")
    private String username;

    @NotBlank(message = "Полето не трябва да е празно!")
    @Length(min = 5, message = "Паролата трябва да е поне 5 символа")
    private String password;

    @NotBlank(message = "Полето не трябва да е празно!")
    @Length(min = 5, message = "Паролата трябва да е поне 5 символа")
    private String confirmPassword;

    @NotBlank(message = "Полето трябва да съдържа валидно име!")
    private String firstName;

    @NotBlank(message = "Полето трябва да съдържа валидно име!")
    private String lastName;

    @NotBlank(message = "Полето не трябва да е празно!")
    @Email(message = "Моля, въведете валиден имейл!")
    private String email;

    @NotNull(message = "Полето не трябва да е празно!")
    @Positive(message = "Моля, въведете позитивно число!")
    private Integer age;

    @NotBlank(message = "Полето не трябва да е празно!")
    @Length(min = 10, message = "Телефонния номер трябва да е поне 10 символа")
    private String phoneNumber;

    @NotBlank(message = "Полето не трябва да е празно!")
    @Length(min = 5, message = "Адреса трябва да е поне 5 символа")
    private String address;

    public UserRegisterBindingModel() {
        age = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
