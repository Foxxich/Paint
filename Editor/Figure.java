import java.awt.Graphics;
import java.io.Serializable;
import java.awt.Color;
/**
 * This abstract class is used to implement some parameters for every object.
 */

public abstract class Figure implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * X is the position, where will be build the object.
     */
    protected int X;
    /** 
     * Y is the position, where will be build the object.
     */
    protected int Y;
    /** 
     * size is the side length.
     */
    protected double size;
    /** 
     * color is the set colour.
     */
    protected Color color;

    /**
     * This abstract boolean method is used to get parametrs and check
     * if the mouse cursor is over the object or it is not.
     * @param x This is the x position, where will be build the object.
     * @param y This is the y position, where will be build the object.
     * @return Nothing.
     */
    public abstract boolean isMouseOver(int x, int y);

    /**
     * This is a method ,which sets given colour as object colour.
     * @param color This is the set colour.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This is a method used to change the parametr color.
     * @return changes the colour of chosen figure.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This is a method used to change the size of each object.
     * @param size This is the side length of chosen object.
     */
    public void setSize(double size){
        if(size < 0.1)
            this.size = 0.1;
        else if (size > 10)
            this.size = 10;
        else
            this.size = size;
        
    }

    /**
     * This is a method used to make X=the position of mouse cursor.
     * @param x This is the x position, where will be build the object.
     */
    public void setX(int x) {
        X = x;
    } 

    /**
     * This is a method used to make Y=the position of mouse cursor.
     * @param y This is the y position, where will be build the object.
     */
    public void setY(int y) {
        Y = y;
    } 

    /**
     * This is a method used to get the size of object.
     * And change the size, if the 2nd mouse key wheel is turned.
     * @return the new size of chosen figure.
     */
    public double getSize()
    {
        return size;
    }

    /**
     * This is a method used to change standart.
     * X to the X coordinate of mouse cursor.
     * @return the X position of mouse cursor relative to the window.
     */
    public int getX(){
        return X;
    }

    /**
     * This is a method used to change standart.
     * Y to the Y coordinate of mouse cursor.
     * @return the Y position of mouse cursor relative to the window.
     */
    public int getY(){
        return Y;
    }

    /**
     * This abstract method is used to paint the objects.
     * @param g This is the graphics used to paint objects in repaint() method. 
     */
    public abstract void draw(Graphics g);
}