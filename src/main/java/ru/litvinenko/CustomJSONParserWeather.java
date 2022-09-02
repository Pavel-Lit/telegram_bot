package ru.litvinenko;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomJSONParserWeather {

    public Model parse(String json){
        Model model = new Model();
        JSONObject object = new JSONObject(json);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray jsonArray = object.getJSONArray("weather");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject temp = jsonArray.getJSONObject(i);
            model.setMain(String.valueOf(temp.get("main")));
            model.setIcon(String.valueOf(temp.get("icon")));

        }

        return model;
    }
}
