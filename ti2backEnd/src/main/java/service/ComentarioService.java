package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.ComentarioDAO;
import model.Comentario;
import spark.Request;
import spark.Response;


public class ComentarioService {

	private ComentarioDAO comentarioDAO;

	public ComentarioService() {
		try {
			comentarioDAO = new ComentarioDAO("comentario.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		int data = Integer.parseInt(request.queryParams("data"));
		String texto = request.queryParams("texto");
		int superComentario = Integer.parseInt(request.queryParams("superComentario"));
		int idUsuario = Integer.parseInt(request.queryParams("idUsuario"));
		int idAplicativo = Integer.parseInt(request.queryParams("idAplicativo"));

		int id = comentarioDAO.getMaxId() + 1;

		Comentario comentario = new Comentario(id, data, texto, superComentario, idUsuario, idAplicativo);

		comentarioDAO.add(comentario);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Comentario comentario = (Comentario) comentarioDAO.get(id);
		
		if (comentario != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<comentario>\n" + 
            		"\t<id>" + comentario.getId() + "</id>\n" +
            		"\t<data>" + comentario.getData() + "</data>\n" +
            		"\t<texto>" + comentario.getTexto() + "</texto>\n" +
            		"\t<superComentario>" + comentario.getSuperComentario() + "</superComentario>\n" +
            		"\t<idUsuario>" + comentario.getIdUsuario() + "</idUsuario>\n" +
            		"\t<idAplicativo>" + comentario.getidAplicativo() + "</idAplicativo>\n" + "<comentario>\n";
        } else {
            response.status(404); // 404 Not found
            return "Comentario " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Comentario comentario = (Comentario) comentarioDAO.get(id);

        if (comentario != null) {
        	comentario.setData(Integer.parseInt(request.queryParams("data")));
        	comentario.setTexto(request.queryParams("texto"));
        	comentario.setSuperComentario(Integer.parseInt(request.queryParams("superComentario")));
        	comentario.setIdUsuario(Integer.parseInt(request.queryParams("idUsuario")));
        	comentario.setIdAplicativo(Integer.parseInt(request.queryParams("idAplicativo")));

        	comentarioDAO.update(comentario);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Comentario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Comentario comentario = (Comentario) comentarioDAO.get(id);

        if (comentario != null) {

            comentarioDAO.remove(comentario);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Comentario não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<comentarios type=\"array\">");
		for (Comentario comentario : comentarioDAO.getAll()) {
			returnValue.append("\n<comentario>\n" + 
            		"\t<id>" + comentario.getId() + "</id>\n" +
            		"\t<data>" + comentario.getData() + "</data>\n" +
            		"\t<texto>" + comentario.getTexto() + "</texto>\n" +
            		"\t<superComentario>" + comentario.getSuperComentario() + "</superComentario>\n" +
            		"\t<idUsuario>" + comentario.getIdUsuario() + "</idUsuario>\n" +
            		"\t<idAplicativo>" + comentario.getIdAplicativo() + "</idAplicativo>\n" +
            		"</comentario>\n");
		}
		returnValue.append("</comentarios>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}