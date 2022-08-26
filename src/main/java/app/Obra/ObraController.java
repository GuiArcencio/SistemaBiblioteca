package main.java.app.Obra;

import main.java.app.login.*;
import main.java.app.util.*;
import spark.*;
import java.util.*;
import static main.java.app.Application.obraDao;
import static main.java.app.util.JsonUtil.*;
import static main.java.app.util.RequestUtil.*;

public class ObraController {

    public static Route fetchAllObras = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("Obras", obraDao.findAll());
            return ViewUtil.render(request, model, Path.Template.OBRAS_ALL);
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(obraDao.findAll());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };

    public static Route fetchOneObra = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        Long id = Long.parseLong(getParamId(request));
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            Obra Obra = obraDao.find(id);
            model.put("Obra", Obra);
            return ViewUtil.render(request, model, Path.Template.OBRAS_ONE);
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(obraDao.find(id));
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };
    
}
