package com.miss.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

/**
 * @project: miss-mvc-web
 * @package: com.miss.core.utils
 * @author: miss
 * @date: 2021/4/1 14:24
 * @since:
 * @history: 1.2021/4/1 created by miss
 */
public class PropUtils {

    public static String XML_FILE_EXTENSION = ".xml";

    public static Properties loadProperties(File resource) throws IOException {
        Properties props = new Properties();
        fillProperties(props, resource);
        return props;
    }

    /**
     * Fill the given properties from the given resource (in ISO-8859-1 encoding).
     * @param props the Properties instance to fill
     * @param resource the resource to load from
     * @throws IOException if loading failed
     */
    public static void fillProperties(Properties props, File resource) throws IOException {
        try (InputStream is = Files.newInputStream(resource.toPath())) {
            String filename = resource.getName();
            if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
                props.loadFromXML(is);
            }
            else {
                props.load(is);
            }
        }
    }
}
