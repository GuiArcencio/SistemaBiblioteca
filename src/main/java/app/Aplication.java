package app;
import static spark.Spark.*;

public class Aplication {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello Spark!");
    }
}
