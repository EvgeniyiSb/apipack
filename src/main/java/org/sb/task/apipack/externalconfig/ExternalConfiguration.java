package org.sb.task.apipack.externalconfig;

//import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Реализация External Configuration
 */
//@Slf4j
public class ExternalConfiguration {
    private static final String CONFIG_FILE_PATH = "C:/java_test_config/config.properties";
    private final Properties propertiesList;

    public ExternalConfiguration(){
        this.propertiesList = loadProperties();
    }

    /**
     * Получить значение параметра
     * @param propertyKey Название параметра
     * @return Значение параметра
     */
    public String getPropertyValue(String propertyKey){
        return propertiesList.getProperty(propertyKey);
    }

    /**
     * Загрузить файл конфигураций настроек
     * @return Список настроек
     */
    private Properties loadProperties(){
        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(inputStream);
        }catch (Exception e){
//            log.error("e: ", e);
            System.out.println(e);
        }

        return properties;
    }
}
