import java.awt.Color;
import java.awt.Graphics;
/**
 * This class is used to create triangle with a few parametrs.
 */

public class Triangle extends Figure {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * side is the side length.
     */
    int side;
    /**
     * This constructor is used to get parametrs from MenuFrame and add them.
     * @param x This is the x position, where will be build the triangle.
     * @param y This is the y position, where will be build the triangle.
     * @param side This is the basic size of each side of triangle.
     */
    Triangle(int x, int y, int side) {
        this.X=x;
        this.Y=y;
        this.side = side;
        this.size = 1;
        this.color = Color.BLUE;
        
    }

    /**
     * This boolean method is used to get parametrs and check.
     * if the mouse cursor is over the figure or it is not.
     * @param x This is the x position of mouse cursor relative to the window.
     * @param y This is the y position of mouse cursor relative to the window.
     * @return true if the mouse is over the figure. False otherwise.
     */
    @Override
    public boolean isMouseOver(int x, int y) {

        int scaledSide = (int) (size * side);

        if (x <= X+ scaledSide && y >= -x + (Y+X) && y <= Y )             
            return true;
        else
            return false;
    }

    /**
     * This draw(Graphics g) method is used to paint the triangle.
     * @param g This is the graphics used to paint triangle in repaint() method. 
     */
    @Override
    public void draw(Graphics g) {

        int scaledSide = (int) (size * side);
        
        int xpoints[] = {X, X + scaledSide, X + scaledSide};
        int ypoints[] = {Y, Y, Y - scaledSide};


        g.setColor(color);
        g.fillPolygon(xpoints, ypoints, 3);

        g.setColor(Color.BLACK);
        g.drawPolygon(xpoints, ypoints, 3);
    }

    

}