package app;

import static spark.Spark.*;

import service.CategoriaService;

public class CategoriaApp {
	private static CategoriaService categoriaService = new CategoriaService();

    public static void main(String[] args) {
        port(5432);

        post("/Categoria", (request, response) -> categoriaService.add(request, response));

        get("/Categoria/:id", (request, response) -> categoriaService.get(request, response));

        get("/Categoria/update/:id", (request, response) -> categoriaService.update(request, response));

        get("/Categoria/delete/:id", (request, response) -> categoriaService.remove(request, response));

        get("/Categoria", (request, response) -> categoriaService.getAll(request, response));

    }
}
