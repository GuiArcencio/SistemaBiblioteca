package main.java.br.ufscar.dc.pooa;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello Spark!");
    }
}
