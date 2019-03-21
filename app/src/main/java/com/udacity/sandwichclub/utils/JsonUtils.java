package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    /**
     * This method parses the given Json string and constructs a Sandwich object and returns it
     * @param json string
     * @return Sandwich object
     */
    public static Sandwich parseSandwichJson(String json) {
        String sandwichMainName = null, placeOfOrigin = null, sandwichDesc = null, sandwichImage = null;
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> sandwichIngredients = new ArrayList<>();
        try {
            JSONObject sandwichObj = new JSONObject(json);
            if (sandwichObj != null) {
                JSONObject sandwichName = sandwichObj.getJSONObject("name");
                if (sandwichName != null) {
                    if (sandwichName.getString("mainName") != null) {
                        sandwichMainName = sandwichName.getString("mainName");
                    }
                    JSONArray alsoKnownAsArray = sandwichName.getJSONArray("alsoKnownAs");
                    if (alsoKnownAsArray != null) {
                        for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                            String item = alsoKnownAsArray.get(i).toString();
                            alsoKnownAs.add(item);
                        }
                    }
                }
                if (sandwichObj.getString("placeOfOrigin") != null) {
                    placeOfOrigin = sandwichObj.getString("placeOfOrigin");
                }
                if (sandwichObj.getString("description") != null) {
                    sandwichDesc = sandwichObj.getString("description");
                }
                if (sandwichObj.getString("image") != null) {
                    sandwichImage = sandwichObj.getString("image");
                }

                JSONArray ingredientsArray = sandwichObj.getJSONArray("ingredients");
                if (ingredientsArray != null) {
                    for (int i = 0; i < ingredientsArray.length(); i++) {
                        String item = ingredientsArray.get(i).toString();
                        sandwichIngredients.add(item);
                    }
                }

            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(sandwichMainName, alsoKnownAs, placeOfOrigin, sandwichDesc, sandwichImage, sandwichIngredients);
    }
}
