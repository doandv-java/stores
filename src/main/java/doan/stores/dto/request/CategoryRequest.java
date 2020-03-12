package doan.stores.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {
    private Long id;
    @NotBlank(message = "Tên danh mục không thể để trống")
    private String name;

    private String detail;

    private int active;

    private String nameOld;
}
