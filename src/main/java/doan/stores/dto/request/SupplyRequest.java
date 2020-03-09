package doan.stores.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SupplyRequest {

    private Long id;

    @NotNull(message = "Tên nhà cung cấp không thể để trống")
    private String name;

    private String description;

    @NotBlank(message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotBlank(message = "Email nhà cung cấp không thể trống")
    private String email;

    private String address;

    private int active;

    private String nameOld;
}
