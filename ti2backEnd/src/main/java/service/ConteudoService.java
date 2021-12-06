package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.ConteudoDAO;
import model.Conteudo;
import spark.Request;
import spark.Response;


public class ConteudoService {

	private ConteudoDAO ConteudoDAO;

	public ConteudoService() {
		try {
			ConteudoDAO = new ConteudoDAO("Conteudo.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		int idAplicativo = Integer.parseInt(request.queryParams("idAplicativo"));
		String dica = request.queryParams("dica");
		String imagem = request.queryParams("imagem");
		String tutoriais = request.queryParams("tutoriais");
		String dispositivos = request.queryParams("dispositivos");

		int id = ConteudoDAO.getMaxId() + 1;

		Conteudo Conteudo = new Conteudo(id, idAplicativo, dica, imagem, tutoriais, dispositivos);

		ConteudoDAO.add(Conteudo);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Conteudo Conteudo = (Conteudo) ConteudoDAO.get(id);
		
		if (Conteudo != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<Conteudo>\n" + 
            		"\t<id>" + Conteudo.getId() + "</id>\n" +
            		"\t<idAplicativo>" + Conteudo.getIdAplicativo() + "</idAplicativo>\n" +
            		"\t<dica>" + Conteudo.getDica() + "</dica>\n" +
            		"\t<imagem>" + Conteudo.getImagem() + "</imagem>\n" +
            		"\t<tutoriais>" + Conteudo.getTutoriais() + "</tutoriais>\n" +
            		"\t<dispositivos>" + Conteudo.getDispositivos() + "</dispositivos>\n" + "<Conteudo>\n";
        } else {
            response.status(404); // 404 Not found
            return "Conteudo " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Conteudo Conteudo = (Conteudo) ConteudoDAO.get(id);

        if (Conteudo != null) {
        	Conteudo.setIdAplicativo(Integer.parseInt(request.queryParams("idAplicativo")));
        	Conteudo.setDica(request.queryParams("dica"));
        	Conteudo.setImagem(Integer.parseInt(request.queryParams("imagem")));
        	Conteudo.setTutoriais(Integer.parseInt(request.queryParams("tutoriais")));
        	Conteudo.setDispositivos(Integer.parseInt(request.queryParams("dispositivos")));

        	ConteudoDAO.update(Conteudo);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Conteudo não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Conteudo Conteudo = (Conteudo) ConteudoDAO.get(id);

        if (Conteudo != null) {

            ConteudoDAO.remove(Conteudo);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Conteudo não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<Conteudos type=\"array\">");
		for (Conteudo Conteudo : ConteudoDAO.getAll()) {
			returnValue.append("\n<Conteudo>\n" + 
            		"\t<id>" + Conteudo.getId() + "</id>\n" +
            		"\t<idAplicativo>" + Conteudo.getIdAplicativo() + "</idAplicativo>\n" +
            		"\t<dica>" + Conteudo.getDica() + "</dica>\n" +
            		"\t<imagem>" + Conteudo.getImagem() + "</imagem>\n" +
            		"\t<tutoriais>" + Conteudo.getTutoriais() + "</tutoriais>\n" +
            		"\t<dispositivos>" + Conteudo.getDispositivos() + "</dispositivos>\n" +
            		"</Conteudo>\n");
		}
		returnValue.append("</Conteudos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}