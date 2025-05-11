package com.api.integraplace.service;


import com.api.integraplace.DAO.CompetitorDAO;
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
public class CompetitorService {

    public CompetitorDAO analyseCompetitorPendence(String cnpj_competitor) throws URISyntaxException, IOException, InterruptedException {
        cnpj_competitor = cnpj_competitor.replaceAll("[^0-9]", "");
        String uriApi = "https://portaldatransparencia.gov.br/sancoes/consulta/resultado?paginacaoSimples=true&tamanhoPagina=100&offset=0&direcaoOrdenacao=asc&colunaOrdenacao=nomeSancionado&cadastro=1%2C2&cpfCnpj="
                + cnpj_competitor
                + "&colunasSelecionadas=linkDetalhamento%2Ccadastro%2CcpfCnpj%2CnomeSancionado%2CufSancionado%2Corgao%2CcategoriaSancao%2CdataPublicacao%2CvalorMulta%2Cquantidade&_=1726340505644";

        HttpRequest requestApi = HttpRequest.newBuilder()
                .uri(new URI(uriApi))
                .header("User-Agent","Integraplace")
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(requestApi, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), CompetitorDAO.class);

        }

        return null;
    }

}
