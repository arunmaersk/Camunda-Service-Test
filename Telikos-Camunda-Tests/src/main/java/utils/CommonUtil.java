package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonUtil {

    public static String getResourceAsString(String resourceName) {
        try {
            URL resource = CommonUtil.class.getResource(resourceName);
            if (resource == null) {
                throw new RuntimeException("Failed getting resource:" + resourceName);
            }

            return new String(Files.readAllBytes(Paths.get(resource.toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed getting resource:" + resourceName, e);
        }
    }
}
