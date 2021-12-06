package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {

	private UsuarioDAO usuarioDAO;

	public UsuarioService() {
		try {
			usuarioDAO = new UsuarioDAO("usuario.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		String senha = request.queryParams("senha");
		String email = request.queryParams("email");

		int id = usuarioDAO.getMaxId() + 1;

		Usuario usuario = new Usuario(id, nome, senha, email);

		usuarioDAO.add(usuario);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Usuario usuario = (Usuario) usuarioDAO.get(id);
		
		if (usuario != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<usuario>\n" + 
            		"\t<id>" + usuario.getId() + "</id>\n" +
            		"\t<nome>" + usuario.getNome() + "</nome>\n" +
            		"\t<senha>" + usuario.getSenha() + "</senha>\n" +
            		"\t<email>" + usuario.getEmail() + "</email>\n" + "<usuario>\n";
        } else {
            response.status(404); // 404 Not found
            return "Usuario " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Usuario usuario = (Usuario) usuarioDAO.get(id);

        if (usuario != null) {
        	usuario.setNome(request.queryParams("nome"));
        	usuario.setSenha(request.queryParams("senha"));
			usuario.setEmail(request.queryParams("email"));

        	usuarioDAO.update(usuario);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Usuario usuario = (Usuario) usuarioDAO.get(id);

        if (usuario != null) {

            usuarioDAO.remove(usuario);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<usuarios type=\"array\">");
		for (Usuario usuario : usuarioDAO.getAll()) {
			returnValue.append("\n<usuario>\n" + 
            		"\t<id>" + usuario.getId() + "</id>\n" +
            		"\t<nome>" + usuario.getNome() + "</nome>\n" +
            		"\t<senha>" + usuario.getSenha() + "</senha>\n" +
            		"\t<email>" + usuario.getEmail() + "</email>\n" +
            		"</usuario>\n");
		}
		returnValue.append("</usuarios>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}