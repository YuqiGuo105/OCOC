package com.example.club.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;

public class activites {

    /**
     * @param input input array
     * @param key search by which type
     * @param search the keyword
     * @return result
     * */
    public JSONArray search(JSONArray input, String key, String search) throws JSONException {
        JSONArray result = new JSONArray();

        for (int i=0; i<input.length(); i++) {
            JSONObject temp = input.getJSONObject(i);
            String tempStr = (String) temp.get(key);

            double similarity = similarity(tempStr, search);

            if (similarity > 0.1) {
                temp.put("similarity", similarity);
                result.put(temp);
            }

        }

        result = sort(result, "similarity");

        return result;
    }

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */
    private double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
            /* both strings are zero length */ }

    /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    /**
     * @param input input array
     * @param category the attribute to filter
     * @param key the condition to filter result
     * @return result array
     * */
    public JSONArray category(JSONArray input, String category, String key) throws JSONException {
        JSONArray result = new JSONArray();

        for (int i=0;i<input.length();i++) {

            if (key.equals(input.getJSONObject(i).get(category))) {
                JSONObject temp = input.getJSONObject(i);
                result.put(temp);
            }

        }

        return result;
    }

    /**
     * @param input input array
     * @param key sort by which attribute
     * */
    public JSONArray sort(JSONArray input, String key) throws JSONException {

        for (int i=0;i<input.length();i++){
            JSONObject a = input.getJSONObject(i);
            String aValue = (String) a.get(key).toString();

            for (int j=i+1;j<input.length();j++){
                JSONObject b = input.getJSONObject(j);
                String bValue = (String) b.get(key).toString();

                int compareTon = aValue.compareTo(bValue);
                if (compareTon < 1) {
                    input.put(i, b);
                    input.put(j, a);
                }
            }

        }

        return input;
    }
}
