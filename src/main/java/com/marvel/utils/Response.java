package com.marvel.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.Dto.Character;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Response {

    final ObjectMapper mapper = new ObjectMapper();
    JsonNode node;


    /** Method convert response Api Marvel into a Response
     * Object Character
     * **/
    public List<Character> convertToJson(String response){
        List<Character> characters = new ArrayList<>();

        Character character;
        try {
            node = mapper.readTree(response);

            for (JsonNode node : node.get("data").get("results")) {
                character = new Character(node.get("id").asInt(),
                        node.get("name").asText(), node.get("description").asText(),
                        node.get("modified").asText(),
                        node.get("thumbnail").get("path").asText()+"."+node.get("thumbnail").get("extension").asText());

                characters.add(character);

            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return  characters;
    }


}
