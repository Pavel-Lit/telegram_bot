package ru.litvinenko;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class SendImage {
    public static String sendImgFromUrl(String message) throws IOException {

        String metCast = " ";
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=2d699c829ecea67a8399e8f6b16d5199");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        while (scanner.hasNext()) {
            metCast += scanner.nextLine();
        }
        CustomJSONParserWeather customJSONParser = new CustomJSONParserWeather();
        Model model = customJSONParser.parse(metCast);
        String weatherType = model.getMain();
        System.out.println(weatherType);

return weatherType;
    }
}
