package org.academiadecodigo.bomberwoman.gameObjects.control;

import org.academiadecodigo.bomberwoman.direction.Direction;

/**
 * Created by codecadet on 07/11/17.
 */
public interface Movable {

    Direction getDirection();

    void move();

}
