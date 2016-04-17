package hackday.speechAccent.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Path path = Paths.get(env.getProperty("file.audio.location") + filename);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            LOGGER.error("Error occurences in: ", e);
        }
        return filename;
    }
}
