package app;

import static spark.Spark.*;

import service.ConteudoService;

public class ConteudoApp {

    private static ConteudoService conteudoService = new ConteudoService();

    public static void main(String[] args) {
        port(5432);

        post("/Conteudo", (request, response) -> conteudoService.add(request, response));

        get("/Conteudo/:id", (request, response) -> conteudoService.get(request, response));

        get("/Conteudo/update/:id", (request, response) -> conteudoService.update(request, response));

        get("/Conteudo/delete/:id", (request, response) -> conteudoService.remove(request, response));

        get("/Conteudo", (request, response) -> conteudoService.getAll(request, response));

    }
}