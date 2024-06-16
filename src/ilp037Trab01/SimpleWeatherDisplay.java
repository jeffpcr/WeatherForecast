package ilp037Trab01;

import org.json.JSONObject;

public class SimpleWeatherDisplay implements WeatherDisplayStrategy {
    @Override
    public void display(String response) {
        JSONObject jsonObject = new JSONObject(response);
        String cityName = jsonObject.getString("name");
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp") - 273.15; // Convert Kelvin to Celsius

        System.out.println("City: " + cityName);
        System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
    }
}

