package doan.stores;

import doan.stores.framework.Settings;
import doan.stores.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoresApplication implements CommandLineRunner {

    @Autowired
    private Settings settings;

    public static void main(String[] args) {
        SpringApplication.run(StoresApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        FileUtils.createDirectoryIfNotExist(settings.getImageRoot().getPath());
    }
}
