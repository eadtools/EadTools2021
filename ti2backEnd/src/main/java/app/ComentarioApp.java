package app;

import static spark.Spark.*;

import service.ComentarioService;

public class ComentarioApp{

    private static ComentarioService comentarioService = new ComentarioService();

    public static void main(String[] args) {
        port(5432);

        post("/Comentario", (request, response) -> comentarioService.add(request, response));

        get("/Comentario/:id", (request, response) -> comentarioService.get(request, response));

        get("/Comentario/update/:id", (request, response) -> comentarioService.update(request, response));

        get("/Comentario/delete/:id", (request, response) -> comentarioService.remove(request, response));

        get("/Comentario", (request, response) -> comentarioService.getAll(request, response));

    }
}
