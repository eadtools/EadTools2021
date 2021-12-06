package app;

import static spark.Spark.*;

import service.AplicacaoService;

public class AplicacaoApp{

    private static AplicacaoService aplicacaoService = new AplicacaoService();

    public static void main(String[] args) {
        port(5432);

        post("/Aplicacao", (request, response) -> aplicacaoService.add(request, response));

        get("/Aplicacao/:id", (request, response) -> aplicacaoService.get(request, response));

        get("/Aplicacao/update/:id", (request, response) -> aplicacaoService.update(request, response));

        get("/Aplicacao/delete/:id", (request, response) -> aplicacaoService.remove(request, response));

        get("/Aplicacao", (request, response) -> aplicacaoService.getAll(request, response));

    }
}