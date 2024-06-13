
package Code;
import processing.core.*;

public class Apple extends PApplet{
    
    public PVector position = new PVector(0,0);
    
    public Apple(){
        
    }

    /**
     *Le da una nueva posicion a la manzana comprobando que ahí no haya serpiente
     * @param map booleano[][] vector
     */
    public void spawn(boolean[][] map){
        boolean done = false;
        while(!done){
            int x = (int) random(0,24);
            int y = (int) random(0,24);
            
            if(map[y][x]){
                done = true;
                position.x = x;
                position.y = y;
            }
        }
    }
    
    public PVector getPosition(){
        return position;
    }
}
