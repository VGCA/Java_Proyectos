
package Code;

import processing.core.PVector;

import java.util.ArrayList;

public class Snake {

    public ArrayList<Integer> posX = new ArrayList<>();
    public ArrayList<Integer> posY = new ArrayList<>();

    public boolean alive = true;


    private final PVector initialPosition1;
    private final PVector initialPosition2;
    private final BotSnakeMovement movement = new BotSnakeMovement();
    //Colores
    public int r, g, b;

    public Snake(int r, int g, int b, PVector initial1, PVector initial2) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.initialPosition1 = initial1;
        this.initialPosition2 = initial2;

        restart();
    }

    /**
     * Mueve la serpiente humana añadiendo la posicion nueva al inicio y
     * después elimina la última. Lo que le pasamos son las coordenadas de
     * un vector que solo pueda tener 1 o -1 de tal forma que solo se
     * mueva una casilla al sumarla
     *
     * @param x posición en X
     * @param y posición en Y
     */
    public void mover(int x, int y) {
        posX.addFirst(posX.getFirst() + x);
        posY.addFirst(posY.getFirst() + y);

        posX.removeLast();
        posY.removeLast();
    }

    /**
     * Movimiento de la serpiente de la máquina
     * @param apple objeto PVector
     * @param map boolean[][] vector del mapa
     */
    public void mover(PVector apple, boolean[][] map) {
        PVector headSnake = new PVector(posX.getFirst(), posY.getFirst());
        PVector tailSnake = new PVector(posX.getLast(), posY.getLast());

        PVector nextMove = movement.getNewPosition(map, headSnake, tailSnake, apple, posX.size());

        posX.addFirst(posX.getFirst() + (int) nextMove.x);
        posY.addFirst(posY.getFirst() + (int) nextMove.y);

        posX.removeLast();
        posY.removeLast();
    }

    /**
     * Añade una nueva posición
     */
    public void comer() {
        posX.add(posX.getLast());
        posY.add(posY.getLast());
    }

    /**
     * Resetea la partida
     */
    public void restart() {
        posX.clear();
        posY.clear();
        alive = true;

        posX.add((int) initialPosition1.x);
        posY.add((int) initialPosition1.y);
        posX.add((int) initialPosition2.x);
        posY.add((int) initialPosition2.y);
    }
}
