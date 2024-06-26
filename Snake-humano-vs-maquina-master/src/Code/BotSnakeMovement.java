
package Code;

import processing.core.*;

public class BotSnakeMovement extends PApplet{
    final int filas = 25;
    Dijkstra dij = new Dijkstra();
    int headNodo;
    int tailNodo;
    int snakeLength;
    boolean[][] mapb;
    int contador = 0;
    int[] longestRoad;
    int[][] grafo;
    PVector vHead = new PVector(0,0);
    PVector lastMove = new PVector(0,0);
    String direction = "";
    
    public PVector getNewPosition(boolean[][] map, PVector snakeHead,PVector snakeTail,
                                  PVector apple, int snakeLengh){
        this.snakeLength = snakeLengh;
        mapb = map;
        grafo = createGraph(map);
        vHead.set(snakeHead);
        int appleNodo = transformVectorIntoNodo(apple);
        headNodo = transformVectorIntoNodo(snakeHead);
        tailNodo = transformVectorIntoNodo(snakeTail);
        
        return getSolution(dij.dijkstra(grafo, appleNodo));
    }

    /**
     * Se toma la cabeza de la serpiente y comprobará todas las casillas
     * a las que puede acceder en este movimiento, y se irá por aquella que
     * que más rapido llegue hacia la manzana. También tiene en cuenta si
     * al entrar a esa posición se quedará encerrada. Si es así,
     * sencillamente no escojera ese camino.
     * @param dist int[][] vector
     * @return siguiente movimiento de la serpiente
     */
    private PVector getSolution(int[] dist){
        int originNodo = headNodo;
        
        int i = originNodo / filas;
        int j = originNodo % filas;
        
        int min = Integer.MAX_VALUE;
        int provisionalNodo = 0;
        
        //Arriba
        if (i > 0 && !direction.equals("abajo")) {
            if (checkMove((i - 1) * filas + j, tailNodo)) {
                if (dist[(i - 1) * filas + j] < min) {
                    min = dist[(i - 1) * filas + j];
                    provisionalNodo = (i - 1) * filas + j;
                }
            }
        }
        //Abajo
        if (i < filas - 1 && !direction.equals("arriba")) {
            if (checkMove((i + 1) * filas + j, tailNodo)) {
                if (dist[(i + 1) * filas + j] < min) {
                    min = dist[(i + 1) * filas + j];
                    provisionalNodo = (i + 1) * filas + j;
                }
            }
        }
        //Izquierda
        if (j > 0 && !direction.equals("derecha")) {
            if (checkMove(i * filas + j - 1, tailNodo)) {
                if (dist[i * filas + j - 1] < min) {
                    min = dist[i * filas + j - 1];
                    provisionalNodo = i * filas + j - 1;
                }
            }

        }
        //Derecha
        if (j < filas - 1 && !direction.equals("izquierda")) {
            if (checkMove(i * filas + j + 1, tailNodo)) {
                if (dist[i * filas + j + 1] < min) {
                    min = dist[i * filas + j + 1];
                    provisionalNodo = i * filas + j + 1;
                }
            }

        }
        
        //Esto significa que no puede acceder a la manzana y por tanto la mandamos por el camino mas largo
        if(min == Integer.MAX_VALUE){
            provisionalNodo = getLongestRoad();
        }
        else{
            contador = 0;
        }
        
        //Ya sabiendo a que nodo iremos, ahora le añadimos el movimeinto en sí.
        lastMove.set((provisionalNodo % filas)-vHead.x, (int) (provisionalNodo /filas) - vHead.y);
        
        //Aqui marcamos cual será la nueva direccion para que nunca pueda ir en contraria
        if(lastMove.x == 1){
            direction = "derecha";
        }
        else if(lastMove.x == -1){
            direction = "izquierda";
        }
        else if(lastMove.y == 1){
            direction = "abajo";
        }
        else if(lastMove.y == -1){
            direction = "arriba";
        }
        
        return lastMove;
    }
    


    /**
     * Metodo donde se comprueba si al entrar a esa casilla se va a encerrar
     * @param nextNodo int
     * @param tailNodo int
     * @return true o false si se da con su cola o no
     */
    private boolean checkMove(int nextNodo, int tailNodo){
        boolean[][] map = createFutureMap();
        int[] dist = dij.dijkstra(createGraph(map), nextNodo);
        
        //Comprueba si puede acceder a su cola y si el recorrido que puede hacer sera mayor del que mida la serpiente
        if(dist[tailNodo] == Integer.MAX_VALUE && (numberOfPosibleMoves(dist)-5) < snakeLength){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     * Creamos un mapa booleano con la posicion de la serpiente movida
     * @return booleano[][] donde se elimina la cola del mapa y se dibuja
     * en otro sitio
     */
    private boolean[][] createFutureMap() {
        boolean[][] newMap = new boolean[filas][filas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {
                newMap[i][j] = mapb[i][j];
            }
        }

        PVector tail = transformIntoVector(tailNodo);
        PVector head = transformIntoVector(headNodo);

        //Ponemos la cola a negativo y buscamos su siguiente posicion en cola para tambien borrarla
        newMap[(int) tail.y][(int) tail.x] = true;

        if (tail.y > 0) {
            if (!newMap[(int) tail.y - 1][(int) tail.x]) {
                newMap[(int) tail.y - 1][(int) tail.x] = true;
            }
        }
        if (tail.y < filas - 1) {
            if (!newMap[(int) tail.y + 1][(int) tail.x]) {
                newMap[(int) tail.y + 1][(int) tail.x] = true;
            }
        }
        if (tail.x > 0) {
            if (!newMap[(int) tail.y][(int) tail.x - 1]) {
                newMap[(int) tail.y][(int) tail.x - 1] = true;
            }
        }
        if (tail.x < filas - 1) {
            if (!newMap[(int) tail.y][(int) tail.x + 1]) {
                newMap[(int) tail.y][(int) tail.x + 1] = true;
            }
        }

        //Ponemos la cabeza a false
        newMap[(int) head.y][(int) head.x] = false;

        return newMap;
    }


    /**
     * Cuenta todas las casillas a las que puedo acceder
     * @param dist int[]
     * @return int de posibles movimientos
     */
    private int numberOfPosibleMoves(int[] dist){
        int cont = 0;
        for(int i = 0;i<dist.length;i++){
            if(dist[i] != Integer.MAX_VALUE){
                cont++;
            }
        }
        return cont;
    }

    /**
     * Contruimos el graph. Para ello lo que hacemos recorrer cada casilla
     * de nuestro mapa. En cada casilla mira a ver si las casillas de su
     * alrededor son true. Si es así, significa que por ahí puede pasar y por
     * lo tanto lo marcamos como un 1.
     * @param matriz boolean[][] vector
     * @return grafo del juego
     */
    public int [][] createGraph(boolean[][]matriz){
        int matrizSize = matriz.length;
        int totalSize = matrizSize * matrizSize;
        int[][] graph = new int[totalSize][totalSize];
        
        //Inicializamos la matriz con todo a 0
        for (int i = 0; i < totalSize; i++) {
            for (int j = 0; j < totalSize; j++) {
                graph[i][j] = 0;
            }
        }
        

        for (int i = 0; i < matrizSize; i++) {
            for (int j = 0; j < matrizSize; j++) {
                if (i > 0) {
                    if (matriz[i - 1][j]) {
                        graph[i * matrizSize + j][(i - 1) * matrizSize + j] = 1;
                    }
                }
                if (i < matrizSize - 1) {
                    if (matriz[i + 1][j]) {
                        graph[i * matrizSize + j][(i + 1) * matrizSize + j] = 1;
                    }
                }
                if (j > 0) {
                    if (matriz[i][j - 1]) {
                        graph[i * matrizSize + j][i * matrizSize + (j - 1)] = 1;
                    }
                }
                if (j < matrizSize - 1) {
                    if (matriz[i][j + 1]) {
                        graph[i * matrizSize + j][i * matrizSize + (j + 1)] = 1;
                    }
                }
            }
        }
        
        return graph;
    }
    
    private int getLongestRoad(){
        contador++;

        //Creamos un mapa booleano donde la cola sea true
        boolean[][] mapWithTail = new boolean[mapb.length][mapb.length];
        for(int i = 0;i<mapb.length;i++){
            for(int j = 0;j<mapb.length;j++){
                mapWithTail[i][j] = mapb[i][j];
            }
        }
        int x = tailNodo % filas;
        int y = tailNodo / filas;
        mapWithTail[y][x] = true;

        int[] distWithoutTail = dij.dijkstra(grafo, headNodo);
        int[] distWithTail = dij.dijkstra(createGraph(mapWithTail), headNodo);

        //Significara que no puede acceder a su cola
        if (distWithTail[tailNodo] == Integer.MAX_VALUE) {
            if (contador == 1) {
                int nodoDestination = getFarestNodo(distWithoutTail);
                LongRoad longRoad = new LongRoad(mapb, headNodo, nodoDestination);
                longestRoad = longRoad.getLongestRoad();
            }
        } //Si puede acceder a su cola y por ello lo vamos a enviar ahí haciendo el camino mas largo.
        else {
            if (contador == 1) {
                LongRoad longRoad = new LongRoad(mapb, headNodo, tailNodo);
                longestRoad = longRoad.getLongestRoad();
            }
        }

        return longestRoad[contador];
    }
    
    private int getFarestNodo(int dist[]){
        int nodo = 0;
        int min = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                if (dist[i] >= min) {
                    nodo = i;
                    min = dist[i];
                }
            }
        }
        return nodo;
    }
    
    private int transformVectorIntoNodo(PVector p){
        int nodo = (int) (p.x + p.y * filas);
        return nodo;
    }
    private PVector transformIntoVector(int nodo) {
        int y = (int) nodo / filas;
        int x = nodo % filas;

        return new PVector(x, y);
    }
}
