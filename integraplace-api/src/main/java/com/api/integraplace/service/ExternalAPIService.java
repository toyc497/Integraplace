package com.api.integraplace.service;

import com.api.integraplace.DAO.CompetitorDAO;
import com.api.integraplace.form.CepInfoForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class ExternalAPIService {

    public CepInfoForm getCepInfo(String cep) throws URISyntaxException, IOException, InterruptedException {
        String uri = "http://viacep.com.br/ws/"+cep+"/json/";

        HttpRequest requestApi = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("User-Agent","Integraplace")
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(requestApi, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), CepInfoForm.class);

        }

        return null;

    }
}
