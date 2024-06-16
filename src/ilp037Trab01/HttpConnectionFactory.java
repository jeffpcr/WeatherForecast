package ilp037Trab01;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionFactory {

    public static HttpURLConnection createConnection(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }
}

