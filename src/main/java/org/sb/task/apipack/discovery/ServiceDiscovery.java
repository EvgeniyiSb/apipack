package org.sb.task.apipack.discovery;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация Service Discovery
 */
public class ServiceDiscovery {
    private static final Map<String, String> serviceList  = new HashMap<>();

    /**
     * Добавить сервис
     * @param name Имя добавляемого сервиса
     * @param url URL добавляемоог сервиса
     */
    public static void addService(String name, String url){
        serviceList.put(name, url);
    }

    /**
     * Удалить сервис
     * @param name Имя удаляемого сервиса
     */
    public static void removeService(String name){
        serviceList.remove(name);
    }

    /**
     * Получить сервис по имени
     * @param name Имя сервиса
     * @return URL указаного сервиса
     */
    public static String getService(String name){
        return serviceList.get(name);
    }
}
