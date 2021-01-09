package com.adlardco.sudoku;

import static spark.Spark.*;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.controller.GridController;
import com.adlardco.sudoku.controller.model.GridModel;
import com.adlardco.sudoku.service.grid.GridConverter;
import com.adlardco.sudoku.controller.InfoController;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import spark.Spark;

@AllArgsConstructor
public class Application {

    private static final String CONTENT_TYPE = "application/json";

    @NonNull private final ApplicationConfig config;
    @NonNull private final Gson gson;

    private void run() {
        port(config.getPort());
        Spark.staticFileLocation("public");
        routes();
    }

    private void routes() {
        get("/api/info", (request, response) -> {
            response.type(CONTENT_TYPE);
            var controller = new InfoController();
            return gson.toJson(controller.info());
        });
        post("/api/grid", (request, response) -> {
            var grid = gson.fromJson(request.body(), GridModel.class);
            response.type(CONTENT_TYPE);
            var controller = new GridController(config, new GridConverter(config));
            return gson.toJson(controller.solve(grid));
        });
    }

    public static void main(String[] args) {
        var config = new ApplicationConfig(8080, 81, 15000);
        var application = new Application(config, new Gson());
        application.run();
    }
}