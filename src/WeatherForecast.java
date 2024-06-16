import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class WeatherForecast {

    private static final String API_KEY = "API_KEY";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe a Cidade: ");
        String city = scanner.nextLine();
        
        scanner.close();
        
        try {
            String response = getWeatherData(city);
            parseAndDisplayWeather(response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String getWeatherData(String city) throws Exception {
        String urlString = BASE_URL + city + "&appid=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        return content.toString();
    }

    private static void parseAndDisplayWeather(String response) {
        JSONObject jsonObject = new JSONObject(response);
        String cityName = jsonObject.getString("name");
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp") - 273.15; // Convert Kelvin to Celsius
        int humidity = main.getInt("humidity");

        System.out.println("Cidade: " + cityName);
        System.out.println("Temperatura: " + String.format("%.2f", temperature) + "°C");
        System.out.println("Humidade: " + humidity + "%");
    }
}
