package org.academiadecodigo.bomberwoman.gameObjects;

import org.academiadecodigo.bomberwoman.ConsoleColors;
import org.academiadecodigo.bomberwoman.Constants;
import org.academiadecodigo.bomberwoman.Game;
import org.academiadecodigo.bomberwoman.Utils;
import org.academiadecodigo.bomberwoman.direction.Direction;
import org.academiadecodigo.bomberwoman.events.ObjectMoveEvent;
import org.academiadecodigo.bomberwoman.gameObjects.control.Destroyable;
import org.academiadecodigo.bomberwoman.gameObjects.control.Movable;

/**
 * Created by codecadet on 12/11/2017.
 */
public class NPC extends GameObject implements Movable, Destroyable {

    private Direction direction;
    private int counter;

    public NPC(int id, int x, int y) {
        super(id, GameObjectType.NPC.getDrawChar(), x, y, ConsoleColors.GREEN);
        direction = null;
        counter = 0;
    }


    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move() {

        if(counter % Constants.NPC_RANDONMNESS == 0) {
            direction = Utils.getRandomDirection();
        }

        Game.getInstance().getServerThread().broadcast(new ObjectMoveEvent(this, direction));

    }

}
