import java.awt.Color;
import java.awt.Graphics;
/**
  * This class is used to create square with a few parametrs.
*/

public class Square extends Figure {

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
     * @param x This is the x position, where will be build the square.
     * @param y This is the y position, where will be build the square.
     * @param side This is the basic size of each side of square.
     */
    Square(int x, int y, int side) {
        this.X = x;
        this.Y = y;
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

        if (x > X && x < X + scaledSide && y < Y && y > Y - scaledSide)
            return true;
        else
            return false;
    }

    /**
     * This draw(Graphics g) method is used to paint the square.
     * @param g This is the graphics used to paint square in repaint() method. 
     */
    @Override
    public void draw(Graphics g) {
        int scaledSide = (int) (size * side);

        g.setColor(Color.BLACK);
        g.drawLine(X, Y, X+scaledSide, Y);
        g.drawLine(X+scaledSide, Y, X+scaledSide, Y-scaledSide);
        g.drawLine(X+scaledSide, Y-scaledSide, X, Y-scaledSide);
        g.drawLine(X, Y-scaledSide, X, Y);

        g.setColor(color);
        g.fillRect(X + 1, Y - scaledSide + 1, scaledSide - 1, scaledSide - 1);
    }

    

}