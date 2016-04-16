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
        String filename = file.getOriginalFilename();
        File outputFile = new File(env.getProperty("file.audio.location") + filename);
        byte[] buffer = new byte[1024];

        try(FileInputStream reader = (FileInputStream) file.getInputStream();
            FileOutputStream writer = new FileOutputStream(outputFile)) {
            if (outputFile.createNewFile()) {
                while ((reader.read(buffer)) != -1) {
                    writer.write(buffer);
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            LOGGER.error("Error occurences in: ", e);
        }
        return filename;
    }
}
