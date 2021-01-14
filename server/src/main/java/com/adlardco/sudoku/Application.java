package com.adlardco.sudoku;

import static spark.Spark.*;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.controller.GridController;
import com.adlardco.sudoku.controller.model.GridModel;
import com.adlardco.sudoku.service.grid.GridConverter;
import com.adlardco.sudoku.controller.InfoController;
import com.adlardco.sudoku.service.grid.GridModelValidator;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.List;

@AllArgsConstructor
public class Application {

    private static final String CONTENT_TYPE = "application/json";

    @NonNull
    private final ApplicationConfig config;
    @NonNull
    private final Gson gson;

    private void run() {
        port(config.getPort());
        Spark.staticFileLocation("public");
        disableCors();
        routes();
    }

    private void disableCors() {
        options("/*", (request, response) -> {
            List.of("Access-Control-Request-Headers", "Access-Control-Allow-Methods")
                    .forEach(header -> transferHeaderToResponse(header, request, response));
            return "";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

    private void transferHeaderToResponse(String header, Request request, Response response) {
        var headers = request.headers(header);
        if (headers != null) {
            response.header(header, headers);
        }
    }

    private void routes() {
        get("/api/info", (request, response) -> {
            response.type(CONTENT_TYPE);
            var controller = new InfoController();
            return gson.toJson(controller.info());
        });
        post("/api/grid", (request, response) -> {
            var grid = gson.fromJson(request.body(), GridModel.class);
            if(!new GridModelValidator(config).isValid(grid)) {
                response.status(HttpStatus.BAD_REQUEST_400);
                return "";
            }
            response.type(CONTENT_TYPE);
            var controller = new GridController(config, new GridConverter(config));
            return gson.toJson(controller.solve(grid));
        });
    }

    private static int getPort() {
        var environmentValues = System.getenv();
        var portText = environmentValues.get("PORT");
        return (portText == null || portText.trim().isEmpty()) ? 8080 : Integer.parseInt(portText.trim());
    }

    public static void main(String[] args) {
        var port = getPort();
        var config = new ApplicationConfig(port, 81, 15000);
        var application = new Application(config, new Gson());
        application.run();
    }
}