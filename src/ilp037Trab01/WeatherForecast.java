package ilp037Trab01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class WeatherForecast {

    private static final String API_KEY = "api-key";
    private static final String BASE_URL = "http://api.openweathermap.org/data/3.0/weather?q=";
    
    private static String getWeatherData(String city) throws Exception {
        String urlString = BASE_URL + city + "&appid=" + API_KEY;
        HttpURLConnection connection = HttpConnectionFactory.createConnection(urlString);

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
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the city: ");
        String city = scanner.nextLine();

        System.out.print("Choose display mode (detailed/simple): ");
        String mode = scanner.nextLine();
        
        scanner.close();
        
        WeatherDisplayStrategy displayStrategy = mode.equalsIgnoreCase("simple") 
            ? new SimpleWeatherDisplay() 
            : new DetailedWeatherDisplay();

        try {
            String response = getWeatherData(city);
            displayStrategy.display(response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
