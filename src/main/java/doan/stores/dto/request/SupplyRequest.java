package doan.stores.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SupplyRequest {

    private Long id;

    @NotBlank(message = "Tên nhà cung cấp không thể để trống")
    private String name;

    @NotBlank(message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotBlank(message = "Email nhà cung cấp không thể trống")
    private String email;

    private String address;

    private String detail;

    private int active;

    private int deleted;

    private String nameOld;
}
