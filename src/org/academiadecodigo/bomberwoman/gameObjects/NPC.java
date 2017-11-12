package org.academiadecodigo.bomberwoman.gameObjects;

import org.academiadecodigo.bomberwoman.ConsoleColors;
import org.academiadecodigo.bomberwoman.Constants;
import org.academiadecodigo.bomberwoman.Game;
import org.academiadecodigo.bomberwoman.Utils;
import org.academiadecodigo.bomberwoman.direction.Direction;
import org.academiadecodigo.bomberwoman.events.ObjectMoveEvent;
import org.academiadecodigo.bomberwoman.events.RefreshScreenEvent;
import org.academiadecodigo.bomberwoman.gameObjects.control.Destroyable;
import org.academiadecodigo.bomberwoman.gameObjects.control.Movable;
import org.academiadecodigo.bomberwoman.threads.logic.CollisionDetector;

import java.util.Random;

/**
 * Created by codecadet on 12/11/2017.
 */
public class NPC extends GameObject implements Movable, Destroyable {

    private Direction direction;

    public NPC(int id, int x, int y) {
        super(id, GameObjectType.NPC.getDrawChar(), x, y, ConsoleColors.GREEN);
        direction = Direction.randomDirection();
    }


    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean move() {

        synchronized (Game.getInstance().getServerThread().getGameObjectMap()) {

            if (Game.getInstance().getServerThread().getGameObjectMap().get(this.getId()) == null) {
                return true;
            }

            if (new Random().nextInt(100) < Constants.NPC_ODD) {
                direction = direction.differentDirection();
            }

            int newX = getX() + direction.getHorizontal();
            int newY = getY() + direction.getVertical();

            if (CollisionDetector.canMove(newX, newY)) {

                GameObject gameObjectAt = Utils.getObjectAt(Game.getInstance().getServerThread().getGameObjectMap().values(), newX, newY);

                if (gameObjectAt != null) {

                    System.out.println(gameObjectAt.getRepresentation());

                    if (gameObjectAt instanceof Player) {

                        Game.getInstance().getServerThread().removeObject(gameObjectAt.getId());
                    }

                }

                Game.getInstance().getServerThread().broadcast(new ObjectMoveEvent(this, direction).toString());
                System.out.println("moved 212e3213rewfew");

            } else {

                direction = direction.differentDirection();
            }
        }

        Game.getInstance().getServerThread().broadcast(new RefreshScreenEvent());

        return false;

    }

}
