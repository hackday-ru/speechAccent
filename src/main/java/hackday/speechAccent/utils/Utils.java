package hackday.speechAccent.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nicaraguanec on 16.04.2016.
 */
@Component
public final class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class);

    @Autowired
    Environment env;

    public final String saveFile(MultipartFile file) {
        String filename = file.getName();
        File outputFile = new File(env.getProperty("file.audio.location") + filename);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            byte[] fileBytes = file.getBytes();
            fileOutputStream.write(fileBytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            LOGGER.error("Error occurences in: ", e);
        }
        return filename;
    }
}
