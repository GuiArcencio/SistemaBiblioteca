package main.java.app.index;

import main.java.app.util.*;
import spark.*;
import java.util.*;
import static main.java.app.Application.*;

public class IndexController {
    public static Route serveIndexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", usuarioDao.getAllUserNames());
        return ViewUtil.render(request, model, Path.Template.INDEX);
    };
}
