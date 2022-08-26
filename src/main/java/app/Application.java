package main.java.app;

import main.java.app.Obra.*;
import main.java.app.Usuario.*;
import main.java.app.index.*;
import main.java.app.login.*;
import main.java.app.util.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class Application {

    // Declare dependencies
    public static ObraDAO obraDao;
    public static UsuarioDAO usuarioDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        obraDao = new ObraDAO();
        usuarioDao = new UsuarioDAO();

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        before("*",                  Filters.addTrailingSlashes);
        before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX,          IndexController.serveIndexPage);
        get(Path.Web.OBRAS,          ObraController.fetchAllObras);
        get(Path.Web.ONE_OBRA,       ObraController.fetchOneObra);
        get(Path.Web.LOGIN,          LoginController.serveLoginPage);
        post(Path.Web.LOGIN,         LoginController.handleLoginPost);
        post(Path.Web.LOGOUT,        LoginController.handleLogoutPost);
        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);

    }

}
