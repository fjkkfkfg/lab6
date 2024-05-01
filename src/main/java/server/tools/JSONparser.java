package server.tools;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import global.facility.Route;
import global.tools.Console;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class JSONparser {
    JsonSerializer<Date> dateSerializer = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return src == null ? null : new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd").format(src));
        }
    };

    JsonDeserializer<Date> dateDeserializer = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, com.google.gson.JsonDeserializationContext context) {
            try {
                return json == null ? null : new SimpleDateFormat("yyyy-MM-dd").parse(json.getAsString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().registerTypeAdapter(Date.class, dateSerializer).registerTypeAdapter(Date.class, dateSerializer).create();
    private final String fileName;
    private final Console console;

    public JSONparser(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Записывает коллекцию в файл.
     *
     * @param collection коллекция
     */


//    public void writeCollection(Stack<Route> collection) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            writer.write(gson.toJson(collection));
//            console.println("Коллекция успешно сохранена в файл!");
//        } catch (IOException exception) {
//            console.printError("Ошибка записи в файл: " + exception.getMessage());
//        }
//    }
    public void writeCollection(Stack<Route> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(gson.toJson(collection));
            console.println("Коллекция успешна сохранена в файл!"); // Assuming console.println is a valid method for output
        } catch (IOException exception) {
            console.printError("Загрузочный файл не может быть открыт!"); // Assuming console.printError is a valid method for error output
        }
    }

    public Stack<Route> readCollection(Stack<Route> collection) {
        if (fileName != null && !fileName.isEmpty()) {
            try (InputStream inputStream = new FileInputStream(fileName); Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                Type collectionType = new TypeToken<Stack<Route>>() {
                }.getType();
                Stack<Route> newCollection = gson.fromJson(reader, collectionType);
                for (var e : newCollection) {
                    if (e.validate()) {
                        collection.push(e);
                    } else {
                        console.printError("Файл с коллекцией содержит не действительные данные");
//                        console.printError("Прога выключается. Пофиксите файл и снова запустите ее :)");
//                        System.exit(0);

                    }
                }
                console.println("Коллекция успешно загружена");
                return collection;
            } catch (FileNotFoundException e) {
                console.printError("Загрузочный файл не найден");
            } catch (UnsupportedEncodingException e) {
                console.printError("Неподдерживаемая кодировка файла");
            } catch (IOException e) {
                console.printError("Ошибка чтения файла: " + e.getMessage());
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден");
        }
        return new Stack<>();
    }
}


