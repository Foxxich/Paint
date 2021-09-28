import java.awt.Color;
import java.awt.Graphics;

import javax.swing.text.AttributeSet.ColorAttribute;
/**
 * This class is used to create circle with a few parametrs.
 */

public class Circle extends Figure {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * diamater is a length of circle diameter.
     */
    int diamater;
    /**
     * This constructor is used to get parametrs from MenuFrame and add them.
     * @param x This is the x position, where will be build the circle.
     * @param y This is the y position, where will be build the circle.
     * @param side This is the basic size of diameter of circle.
     */
    Circle(int x, int y, int diamater) {
        this.X = x;
        this.Y = y;
        this.diamater = diamater;
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
        double centerx = X + size*diamater/2;
        double centery = Y + size*diamater/2;

        if (Math.sqrt((x-centerx)*(x-centerx) + (y-centery)*(y-centery)) <= diamater*size/2)
            return true;
        else
            return false;
    }

    /**
     * This draw(Graphics g) method is used to paint the circle.
     * @param g This is the graphics used to paint circle in repaint() method. 
     */
    @Override
    public void draw(Graphics g) {
        int scaledDiamater = (int) (diamater * size);

        g.setColor(color);
        g.fillOval(X,Y,scaledDiamater,scaledDiamater);
        g.setColor(Color.BLACK);
        g.drawOval(X,Y,scaledDiamater,scaledDiamater);
    }

    

}