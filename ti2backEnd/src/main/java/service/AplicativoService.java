package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.AplicativoDAO;
import model.Aplicativo;
import spark.Request;
import spark.Response;


public class AplicativoService {

	private AplicativoDAO aplicativoDAO;

	public AplicativoService() {
		try {
			aplicativoDAO = new AplicativoDAO("aplicativo.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		int data = Integer.parseInt(request.queryParams("data"));
		String nome = request.queryParams("nome");
		int nota = Integer.parseInt(request.queryParams("nota"));

		int id = aplicativoDAO.getMaxId() + 1;

		Aplicativo aplicativo = new Aplicativo(id, nome, nota);

		aplicativoDAO.add(aplicativo);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Aplicativo aplicativo = (Aplicativo) aplicativoDAO.get(id);
		
		if (aplicativo != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<aplicativo>\n" + 
            		"\t<id>" + aplicativo.getId() + "</id>\n" +
            		"\t<nome>" + aplicativo.getNome() + "</nome>\n" +
            		"\t<nota>" + aplicativo.getNota() + "</nota>\n" + "<aplicativo>\n";
        } else {
            response.status(404); // 404 Not found
            return "Aplicativo " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Aplicativo aplicativo = (Aplicativo) aplicativoDAO.get(id);

        if (aplicativo != null) {
        	aplicativo.setNome(request.queryParams("nome"));
        	aplicativo.setNota(Integer.parseInt(request.queryParams("nota")));

        	aplicativoDAO.update(aplicativo);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Aplicativo não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Aplicativo aplicativo = (Aplicativo) aplicativoDAO.get(id);

        if (aplicativo != null) {

            aplicativoDAO.remove(aplicativo);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Aplicativo não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<aplicativos type=\"array\">");
		for (Aplicativo aplicativo : aplicativoDAO.getAll()) {
			returnValue.append("\n<aplicativo>\n" + 
            		"\t<id>" + aplicativo.getId() + "</id>\n" +
            		"\t<nome>" + aplicativo.getNome() + "</nome>\n" +
            		"\t<nota>" + aplicativo.getNota() + "</nota>\n" +
            		"</aplicativo>\n");
		}
		returnValue.append("</aplicativos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}