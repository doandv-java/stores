package doan.stores.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class AdvertiseRequest {
    private Long id;
    @NotBlank(message = "Nội dung không thể để trống")
    private String content;
    private String imageLink;
    private MultipartFile image;
    private int active;
}
