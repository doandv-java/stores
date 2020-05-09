package doan.stores.utils;

import lombok.Data;

@Data
public class Constants {
    public static final String IMAGE_DEFAULT = "no_image.png";
    public static final String PASS_RANDOM = "123456";

    public interface DELETE {
        int TRUE = 1;
        int FALSE = 0;
    }

    public interface DATE_FORMAT {
        String YYYY_MM_DD = "yyyy-MM-dd";
    }
    
}
