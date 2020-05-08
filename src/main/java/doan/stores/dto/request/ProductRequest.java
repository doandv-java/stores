package doan.stores.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long Id;

    @NotBlank(message = "Chưa nhập tên sản phẩm")
    private String name;

    @NotBlank(message = "Chưa chọn nhà cung cấp")
    private String producer;

    @NotBlank(message = "Giá không được để trống")
    private String price;


    @NotBlank(message = "Loại sản phẩm chưa chọn")
    private String categoryId;

    private String detail;

    private String imageLink;

    private MultipartFile image;

    @NotBlank(message ="Chua chon he dieu hanh")
    private String os;

    @NotBlank(message = "Chưa nhập thông tin CPU")
    private String cpu;

    @NotBlank(message = "Chưa nhập thông tin Ram")
    private String ram;

    private String gpu;

    private String screen;

    private String storage;

    private String weight;

    private String releaseYear;


    private int deleted;

    private String nameOld;
}
