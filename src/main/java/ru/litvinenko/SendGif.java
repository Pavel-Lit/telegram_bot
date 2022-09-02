package ru.litvinenko;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

public class SendGif {
    public static String sendGif(String message) throws IOException {
        String imageForGif = SendImage.sendImgFromUrl(message); //clear weatherType
        String response = " ";
        String request = "https://api.giphy.com/v1/gifs/search?api_key=blj53vBHLpfaD6RX2u3VFziN3X3qjZfo&q="+imageForGif.toLowerCase(Locale.ROOT)+"&limit=1&offset=0&rating=g&lang=en";
        URL url = new URL(request);
        Scanner scanner = new Scanner((InputStream) url.getContent());
        while (scanner.hasNext()) {
            response += scanner.nextLine();
        }

        ModelGif modelGif = CustomJSONParserGif.parse(response);
//        https://api.giphy.com/v1/gifs/search?api_key=blj53vBHLpfaD6RX2u3VFziN3X3qjZfo&q=rain&limit=1&offset=0&rating=g&lang=en
//        String gifUrl= "https://giphy.com/gifs/warnerarchive-the-new-adventures-of-gilligan-3osxYzIQRqN4DOEddC";
//        String gifUrl2 = "https://giphy.com/embed/l41lQIclE3lItAlfq";
        String gifUrl1 = modelGif.getUrl();
        System.out.println(gifUrl1);
        return gifUrl1;


    }
}
