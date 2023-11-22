package com.cybersoft.festore.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class SignUpRequest {

    @NotNull
    @NotBlank(message = "Tên user không được để trống")
    private String userName;

    @NotNull
    @NotBlank(message = "Password không được để trống")
    @Size(min = 8, max = 20, message = "Password phải có độ dài từ 8 đến 20 ký tự")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "Password phải chứa ít nhất một chữ số, một chữ thường, một chữ hoa, và một ký tự đặc biệt.")
    private String password;

    @NotNull
    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Địa chỉ email không hợp lệ")
    private String email;

    private int idRole;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
