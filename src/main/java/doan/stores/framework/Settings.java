package doan.stores.framework;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.settings")
public class Settings {

    private ImageRoot imageRoot = new ImageRoot();

    private Company company = new Company();

    @Data
    public static class ImageRoot {
        String path;
    }

    @Data
    public static class Company {
        String name;
        String email;
        String address;
        String phone;
        String bank;
        String account;

    }

}
