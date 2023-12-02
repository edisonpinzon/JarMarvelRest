package com.marvel.restController;

import com.marvel.Dto.Character;
import com.marvel.utils.Response;
import com.marvel.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class consumeRestMarvel {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Md5 md5;

    @Autowired
    Response response;
    final String URL = "https://gateway.marvel.com:443/v1/public/characters";
    String jsonString;
    final String apikey = "770f2f2e02524cf4a62106b1fc6e1aca";
    final String timezone = "1";

     /** GET Method call Api Marvel to get all character
     *  rreturn List Object Dto Response
     * **/
    @RequestMapping(value = "/apiMarvel/getCharacters")
    public List<Character> getCharacters() {

        List<Character> characters = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        //adding the query params to the URL
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL + "?")
                .queryParam("apikey", apikey)
                .queryParam("ts", timezone)
                .queryParam("hash", md5.hashMd5());

        jsonString  = restTemplate.exchange(uriBuilder.toUriString(),
                HttpMethod.GET, entity, String.class).getBody();

        //convert JsonNode to Object
        characters = response.convertToJson(jsonString);
        return  characters;
    }

    /** GET Method call Api Marvel to get character by Id
     *  return List Object Dto Response
     * **/
    @RequestMapping(value = "/apiMarvel/getCharacterById/{Id}")
    public List<Character> getCharacterById(@PathVariable("Id") String Id) {

        List<Character> characters = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        //adding the query params to the URL
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL + "/" + Id + "?")
                .queryParam("apikey", apikey)
                .queryParam("ts", timezone)
                .queryParam("hash", md5.hashMd5());

        jsonString  = restTemplate.exchange(uriBuilder.toUriString(),
                HttpMethod.GET, entity, String.class).getBody();

        //convert JsonNode to Object
        characters =  response.convertToJson(jsonString);
        return characters;

    }

}
