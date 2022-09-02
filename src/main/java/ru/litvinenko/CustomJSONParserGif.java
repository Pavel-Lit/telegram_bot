package ru.litvinenko;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomJSONParserGif {
    public static ModelGif parse(String json){
        ModelGif modelGif = new ModelGif();
        JSONObject object = new JSONObject(json);
        JSONArray jsonArray = object.getJSONArray("data");
        JSONObject images = new JSONObject();
        for (int i = 0; i< jsonArray.length(); i++){
            JSONObject temp = jsonArray.getJSONObject(i);
            images = temp.getJSONObject("images");
        }
        JSONObject original = images.getJSONObject("original");
        modelGif.setUrl(String.valueOf(original.get("url")));
        return modelGif;
    }

}
