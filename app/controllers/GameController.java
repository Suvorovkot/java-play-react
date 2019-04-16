package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Game;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.GameService;
import tools.DBGame;

import javax.inject.Inject;
import java.util.List;

public class GameController extends Controller {

    @Inject
    private GameService gameService;

    public Result addGames() {
        JsonNode json = request().body().asJson();
        Game game = Json.fromJson(json, Game.class);
        gameService.createUpdateGame(game);
        return ok();
    }

    public Result deleteGames(long id) {
        gameService.deleteGame(id);
        return ok();
    }

    public Result updateGames(long id) {
        JsonNode json = request().body().asJson();
        Game game = Json.fromJson(json, Game.class);
        gameService.createUpdateGame(game);
        return ok();
    }

    public Result getGames() {
        List<Game> games = gameService.getGames();
        JsonNode jsonNode = Json.toJson(games);
        return ok(jsonNode).as("application/json");
    }

    public Result getGameById(long id) {
        Game game = gameService.getGameById(id);
        JsonNode jsonNode = Json.toJson(game);
        return ok(jsonNode).as("application/json");
    }
}
