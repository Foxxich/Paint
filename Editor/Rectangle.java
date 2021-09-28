import java.awt.Color;
import java.awt.Graphics;
/**
 * This class is used to create rectangle with a few parametrs.
 */

public class Rectangle extends Figure {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * side_1 is the length of the one side.
     * side_2 is the length of the another side.
     */
    int side_1;
    int side_2;
    /**
     * This constructor is used to get parametrs from MenuFrame and add them.
     * @param x This is the x position, where will be build the rectangle.
     * @param y This is the y position, where will be build the rectangle.
     * @param side_1 This is the basic size of the one side of rectangle.
     * @param side_2 This is the basic size of the another side of rectangle.
     */
    Rectangle(int x, int y, int side_1,int side_2) {
        this.X = x;
        this.Y = y;
        this.side_1 = side_1;
        this.side_2 = side_2;
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

        int scaledSide_1 = (int) (size * side_1);
        int scaledSide_2 = (int) (size * side_2);

        if (x > X && x < X + scaledSide_1 && y < Y && y > Y - scaledSide_2)
            return true;
        else
            return false;
    }

    /**
     * This draw(Graphics g) method is used to paint the rectangle.
     * @param g This is the graphics used to paint rectangle in repaint() method. 
     */
    @Override
    public void draw(Graphics g) {

        int scaledSide_1 = (int) (size * side_1);
        int scaledSide_2 = (int) (size * side_2);

        g.setColor(Color.BLACK);
        g.drawLine(X, Y, X+scaledSide_1, Y);
        g.drawLine(X+scaledSide_1, Y, X+scaledSide_1, Y-scaledSide_2);
        g.drawLine(X+scaledSide_1, Y-scaledSide_2, X, Y-scaledSide_2);
        g.drawLine(X, Y-scaledSide_2, X, Y);

        g.setColor(color);
        g.fillRect(X + 1, Y - scaledSide_2 + 1, scaledSide_1 - 1, scaledSide_2 - 1);
    }

    

}