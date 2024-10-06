import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String jsonFileName = "new_data.json";

        String json = readString(jsonFileName);

        List<Employee> list = jsonToList(json);

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    private static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                String jsonStr = gson.toJson(jsonObject);

                Employee employee = gson.fromJson(jsonStr, Employee.class);

                list.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}