package app.Obra;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import app.StandardResponse.StandardResponse;
import app.StandardResponse.StatusResponse;

public class ControllerObras {
    public static void main(String[] args) {

        get("/hello", (req, res) -> "Hello World");
    }
}
