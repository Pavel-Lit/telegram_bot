package ru.litvinenko;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        CustomJSONParser customJSONParser = new CustomJSONParser();
        Model model = customJSONParser.parse(metCast);
        String photoUrl = "https://openweathermap.org/img/wn/" +model.getIcon()+".png";

return photoUrl;
    }
}
