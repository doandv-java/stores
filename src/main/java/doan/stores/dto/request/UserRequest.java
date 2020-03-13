package doan.stores.dto.request;

import doan.stores.validation.annotation.Date;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRequest {

    private Long id;

    @NotBlank(message = "Email không được để trống")
    private String userName;


    private String password;

    private String role;

    @NotBlank(message = "Họ tên không được để trống")
    private String name;

    @Date(pattern = "yyyy-MM-dd", message = "Ngày sinh chưa hợp lệ")
    @NotBlank(message = "Vui lòng nhập ngày sinh")
    private String birthDay;

    private int gender;

    private String address;

    @Pattern(regexp = "^[0-9]{9,12}$", message = "Số d điện thoại không hợp lệ")
    private String phone;

    private String imageLink;

    private MultipartFile image;

    private int state;

    private int deleted;

    private String userNameOld;

}

