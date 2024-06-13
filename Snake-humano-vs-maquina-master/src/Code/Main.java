package Code;

import processing.core.*;

import java.util.Objects;

public class Main extends PApplet {

    public static void main(String[] args) {
        /**
         * Biblioteca que hará las animaciones.
         */
        PApplet.main(new String[]{Code.Main.class.getName()});
    }

    int filas = 25;
    int columnas = 25;
    int bs = 20;

    boolean map[][] = new boolean[filas][columnas];
    PVector direction = new PVector(1, 0);

    boolean greenBox = true;
    boolean purpleBox = true;

    Apple apple = new Apple();
    Snake humanSnake = new Snake(100, 200, 100, new PVector(2, 2), new PVector(2, 1));
    Snake botSnake = new Snake(100, 100, 200, new PVector(18, 18), new PVector(18, 19));

    @Override
    public void settings() {
        size(500, 540);
    }

    @Override
    public void setup() {
        frameRate(25);
        initGame();
    }

    @Override
    public void draw() {
        background(25);
        updateMap();
        drawMap();
        drawApple();

        playHumanSnake();
        playBotSnake(botSnake);
    }

    void initGame() {
        updateMap();
        apple.spawn(map);
    }

    /**
     * Método que irá actualizando el mapa
     * continuamente durante el juego.
     */
    void updateMap() {
        //Todo el mapa se es jugable porque está a true
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                map[i][j] = true;
            }
        }

        //Colocamos la serpiente máquina
        for (int i = 1; i < botSnake.posX.size(); i++) {
            map[botSnake.posY.get(i)][botSnake.posX.get(i)] = false;
        }

        //Colocamos la serpiente humana
        for (int i = 1; i < humanSnake.posX.size(); i++) {
            map[humanSnake.posY.get(i)][humanSnake.posX.get(i)] = false;
        }
    }

    /**
     * Método que dibuja el mapa de juego.
     */
    void drawMap() {
        //Dibujamos el cuadrado gris de abajo
        fill(100, 100, 100);
        rect(0, 500, width, 40);

        //Dibujamos las dos casillas del marcador
        fill(100, 200, 100);
        rect(30, 510, 210, 20);

        fill(100, 100, 200);
        rect(270, 510, 210, 20);

        // greenBox y purpleBox son las casillas por las
        // que se van a mover las serpientes
        if (!greenBox) {
            fill(250, 50, 50);
            rect(30, 510, 210, 20);
        }
        if (!purpleBox) {
            fill(250, 50, 50);
            rect(270, 510, 210, 20);
        }
    }

    /**
     * Método que dibuja la manzana en una
     * posición aleatoria del mapa
     */
    void drawApple() {
        fill(215, 0, 75);
        rect(apple.position.x * bs, apple.position.y * bs, bs, bs);
    }

    /**
     * Método que hace que tú juegues la serpiente verde
     */
    void playHumanSnake() {
        if (humanSnake.alive) {
            moveHumanSnake();
            drawSnake(humanSnake);
            detectBorder(humanSnake);
            snakeCrash(humanSnake, botSnake);
        }
    }

    /**
     * Dirección y si come o no la serpiente humana.
     */
    void moveHumanSnake() {
        humanSnake.mover((int) direction.x, (int) direction.y);
        eat(humanSnake);
    }

    /**
     * Comprueba si la cabeza de la serpiente (es decir, la posicion 0)
     * es la misma posicion que la de la manzana
     * @param snake objeto Snake
     */
    void eat(Snake snake) {
        if (snake.posX.getFirst() == apple.position.x &&
                snake.posY.getFirst() == apple.position.y) {
            apple.spawn(map);
            snake.comer();
        }
    }

    /**
     * Rellenamos con el color asignado y despues recorremos todas
     * sus posiciones dibujando en pantalla
     * @param snake objeto Snake
     */
    void drawSnake(Snake snake) {
        fill(snake.r, snake.g, snake.b);
        for (int i = 0; i < snake.posX.size(); i++) {
            rect(snake.posX.get(i) * bs, snake.posY.get(i) * bs, bs, bs);
        }
    }

    /**
     * Detecta si se sale del mapa la cabeza de la serpiente
     * @param snake objeto Snake
     */
    void detectBorder(Snake snake) {
        if (snake.posX.getFirst() < 0 || snake.posX.getFirst() > (columnas - 1) ||
                snake.posY.getFirst() < 0 || snake.posY.getFirst() > (filas - 1)) {
            snake.restart();
        }
    }

    /**
     * Comprueba si se choca consigo misma, para ello recorre todas sus
     * posiciones comprobando que no sean iguales a las de su cabeza.
     * Además, Comprueba si la s1 se choca con la serpiente s2.
     * Metodo igual que el anterior pero con la otra serpiente
     * @param s1 Objeto Snake humano
     * @param s2 Objeto Snake máquina
     */
    void snakeCrash(Snake s1, Snake s2) {

        if (s1.alive) {
            for (int i = 2; i < s1.posX.size(); i++) {
                if (Objects.equals(s1.posX.getFirst(), s1.posX.get(i))
                        && Objects.equals(s1.posY.getFirst(), s1.posY.get(i))) {
                    s1.restart();
                }
            }
        }

        if (s1.alive && s2.alive) {
            for (int i = 0; i < s2.posX.size(); i++) {
                if (Objects.equals(s1.posX.getFirst(), s2.posX.get(i))
                        && Objects.equals(s1.posY.getFirst(), s2.posY.get(i))) {
                    s1.restart();
                }
            }
        }
    }

    /**
     * Método principal que juega la serpiente de la máquina
     * @param bot objeto Snake
     */
    void playBotSnake(Snake bot) {
        if (bot.alive) {
            moveBotSnake(bot);
            drawSnake(bot);
            detectBorder(bot);
            snakeCrash(bot, humanSnake);
        }
    }

    /**
     * Método que mueve y come la serpiente
     * de la máquina
     * @param s1 obketo Snake
     */
    void moveBotSnake(Snake s1) {
        s1.mover(apple.getPosition(), map);
        eat(s1);
    }

    /**
     * Método que mata la serpiente
     * @param s objeto Snake
     */
    void deleteSanke(Snake s) {
        s.posX.clear();
        s.posY.clear();
        s.alive = false;
    }

    /**
     * Comienza la partida de 0
     */
    void restartGame() {
        humanSnake.restart();
        initGame();
    }

    /**
     * Método que crea los botones de movimiento
     * de la serpiente
     */
    @Override
    public void keyPressed() {
        if (key == 'w' || keyCode == UP) {
            if (direction.y != 1) {
                direction.set(0, -1);
            }
        }
        if (key == 's' || keyCode == DOWN) {
            if (direction.y != -1) {
                direction.set(0, 1);
            }
        }
        if (key == 'a' || keyCode == LEFT) {
            if (direction.x != 1) {
                direction.set(-1, 0);
            }
        }
        if (key == 'd' || keyCode == RIGHT) {
            if (direction.x != -1) {
                direction.set(1, 0);
            }
        }
        //Reinicia el juego
        if (key == ' ') {
            restartGame();
        }
    }

    /**
     * Comprueba si esta en el cuadrado verde el click.
     * Si es así cambiara el estado del snake muerto-vivo. Además,
     * comprueba si esta en el cuadrado morado el click.
     *
     */
    @Override
    public void mouseClicked() {

        if (mouseX >= 30 && mouseX <= 240 && mouseY >= 510 && mouseY <= 530) {
            greenBox = !greenBox;

            if (humanSnake.alive) {
                deleteSanke(humanSnake);
            } else {
                humanSnake.restart();
            }
        }

        if (mouseX >= 270 && mouseX <= 480 && mouseY >= 510 && mouseY <= 530) {
            purpleBox = !purpleBox;

            if (botSnake.alive) {
                deleteSanke(botSnake);
            } else {
                botSnake.restart();
            }
        }
    }

}
