package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.CategoriaDAO;
import model.Categoria;
import spark.Request;
import spark.Response;

public class CategoriaService {

	private CategoriaDAO categoriaDAO;

	public CategoriaService() {
		try {
			categoriaDAO = new CategoriaDAO("categoria.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		
		int id = categoriaDAO.getMaxId() + 1;

		Categoria categoria = new Categoria(id, nome);

		categoriaDAO.add(categoria);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Categoria categoria = (Categoria) categoriaDAO.get(id);
		
		if (categoria != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<categoria>\n" + 
            		"\t<id>" + categoria.getId() + "</id>\n" +
            		"\t<nome>" + categoria.getNome() + "</nome>\n";
        } else {
            response.status(404); // 404 Not found
            return "Categoria " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Categoria categoria = (Categoria) categoriaDAO.get(id);

        if (categoria != null) {
        	categoria.setNome(request.queryParams("nome"));

        	categoriaDAO.update(categoria);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Categoria não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Categoria categoria = (Categoria) categoriaDAO.get(id);

        if (categoria != null) {

            categoriaDAO.remove(categoria);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Categoria não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<categorias type=\"array\">");
		for (Categoria categoria : categoriaDAO.getAll()) {
			returnValue.append("\n<categoria>\n" + 
            		"\t<id>" + categoria.getId() + "</id>\n" +
            		"\t<nome>" + categoria.getNome() + "</nome>\n");
		}
		returnValue.append("</categorias>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}