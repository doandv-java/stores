package doan.stores.bussiness.implement;

import doan.stores.bussiness.ImageService;
import doan.stores.framework.Settings;
import doan.stores.utils.Constants;
import doan.stores.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Settings settings;

    @Override
    public String saveImage(MultipartFile file, String imageLink) {
        if (StringUtils.isEmpty(imageLink)) {
            if (FileUtils.checkNullOrEmptyImage(file)) {
                return Constants.IMAGE_DEFAULT;
            } else {
                return FileUtils.saveImage(settings.getImageRoot().getPath(), file);
            }
        } else {
            if (FileUtils.checkNullOrEmptyImage(file)) {
                return imageLink;
            } else {
                return FileUtils.saveImage(settings.getImageRoot().getPath(), file);
            }
        }
    }

}
