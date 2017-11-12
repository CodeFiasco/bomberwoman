package org.academiadecodigo.bomberwoman.gameObjects;

import org.academiadecodigo.bomberwoman.ConsoleColors;
import org.academiadecodigo.bomberwoman.Constants;
import org.academiadecodigo.bomberwoman.Game;
import org.academiadecodigo.bomberwoman.gameObjects.powerups.PowerupFactory;

import static org.academiadecodigo.bomberwoman.ConsoleColors.BLUE;
import static org.academiadecodigo.bomberwoman.ConsoleColors.RED;

/**
 * Created by miro on 06/11/2017.
 */
public class GameObjectFactory {

    public static GameObject byType(int id, GameObjectType gameObjectType, int x, int y) {

        switch(gameObjectType) {

            case PLAYER:
                return new Player(id, x, y, id == Game.getInstance().getPlayerId());

            case BRICK:
                return new Brick(id, x, y);

            case WALL:
                return new Wall(id, x, y);

            case BLUE_WALL:
                return new GameObject(id, Constants.WALL_CHAR, x, y, ConsoleColors.BLUE);

            case BOMB:
                return new Bomb(id, x, y);

            case FLAME:
                return new Flame(id, x, y);

            case POWER_UP:
                return PowerupFactory.random(id, x, y);

            case NPC:
                return new NPC(id, x, y);

            case EMPTY:
            default:
                return new GameObject(id, " ", x, y);
        }
    }

    public static GameObject byString(String charString, int id, int x, int y) {

        String color = ConsoleColors.WHITE;
        if(charString.equals(Constants.WALL_CHAR_BLUE)) {

            color = BLUE;
            charString = Constants.WALL_CHAR;
        }
        else if(charString.equals(Constants.BRICK_CHAR)) {

            color = RED;
        }

        return new GameObject(id, charString, x, y, color);
    }
}