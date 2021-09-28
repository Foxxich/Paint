import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * This class is used to create JPanel for adding objects on it.
 */

// MyPanel extends JPanel, which will eventually be placed in a JFrame.
public class MyPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * mouseX is the position of the mouse cursor relative to the window.
     * mouseY is the position of the mouse cursor relative to the window.
     * mouseButtonLeft is used to check if left mouse button was pressed.
     * mouseButtonRigth is used to check if right mouse button was pressed.
     * nextFigure is used to show names of every figure.
     * figureUnderCursor is the position of the mouse cursor relative to the figures on the JPanel.
     */
    private int mouseX = 0;
    private int mouseY = 0;
    private boolean mouseButtonLeft = false;
    private boolean mouseButtonRigth = false;
    private String nextFigure = "";
    private Figure figureUnderCursor = null;

    private ArrayList<Figure> figures = new ArrayList<>();

    /** 
     * This method is used to add Listeners.
     */
    public MyPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    /** 
     * This method is used to give a list of all figures.
     * @return list of all figures.
     */
    public ArrayList<Figure> getFigures() {
        return figures;
    }

    /** 
     * This method is used to add figures to ArrayList.
     * @param figures These are figures, which will be repainted.
     */
    public void setFigures(ArrayList<Figure> figures) {
        this.figures = figures;
        repaint();
    }

    /** 
     * This method is used to check the name of each figure.
     * @param name This is the string name of each figure.
     */
    public void setNextFigure(String name) {
        nextFigure = name;
    }

    /** 
     * This method is used for checking the cursor position.
     * If the mouse cursor is over the figure, we can move the chosen figure.
     * @return figure/null.
     */
    private Figure findFigure() {
        for (Figure figure : figures) {
            if (figure.isMouseOver(mouseX, mouseY))
                return figure;
        }
        return null;
    }

    /** 
     * This method is used for moving each figure.
     * @param dx is the position of the mouse cursor relative to the figure.
     * @param dy is the position of the mouse cursor relative to the figure.
     * @param figure is the chosen figure.
     */
    private void moveFigure(int dx, int dy, Figure figure) {
        figure.setX(figure.getX() + dx);
        figure.setY(figure.getY() + dy);
    }

    /** 
     * This method is used to change the cursor.
     * If the mouse cursor is over the figure, it has one look.
     * If the mouse cursor is not over the figure, it has another look.
     */
    private void changeCursor() {

        if (figureUnderCursor != null) {
            if (mouseButtonLeft)
                this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            else
                this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /** 
     * This method is used to change the figure colour.
     * @param figure is the figure, which will be repainted.
     */
    private void createContextMenu(Figure figure) {
        JMenuItem menuItem = new JMenuItem("Change color...");
        menuItem.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Choose fill color", figure.getColor());
            if (newColor != null)
                figure.setColor(newColor);
            repaint();
        });

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(menuItem);

        popupMenu.addSeparator();

        popupMenu.show(this, mouseX, mouseY);
    }

    /** 
     * This method is used to switch to the chosen figure.
     * @param figure is a chosen figure from the main Menu.
     */
    private void addFigure() {
        Figure figure;

        switch (nextFigure) {
            case "square":
                figure = new Square(mouseX, mouseY, 50);
                figures.add(figure);
                break;
            case "rectangle":
                figure = new Rectangle(mouseX, mouseY, 70, 50);
                figures.add(figure);
                break;
            case "circle":
                figure = new Circle(mouseX, mouseY, 50);
                figures.add(figure);
                break;
            case "triangle":
                figure = new Triangle(mouseX, mouseY, 100);
                figures.add(figure);
                break;
        }
        nextFigure = "";
        repaint();
    }

    /** 
     * Calls the UI delegate's paint method, if the UI delegate
     * is non-<code>null</code>.  We pass the delegate a copy of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes
     * (for example, <code>Graphics.translate</code>).
     * <p>
     * This method is used for custom painting.
     * @param g This is the graphics used to paint triangle in repaint() method. 
     */
    @Override
    public void paintComponent(Graphics g) {  // Custom painting is performed by the paintComponent method.
        super.paintComponent(g);// Clear the previous painting.

        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    /** 
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub.
    }

    /** 
     * This method is used for checking, which key was pressed.
     * Invoked when a mouse button has been released on a component.
     * @param e the event to be processed.
     */
    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseButtonLeft = true;
            addFigure();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseButtonRigth = true;
            if (figureUnderCursor != null)
                createContextMenu(figureUnderCursor);
        }

    }

    /** 
     * Invoked when a mouse button has been released on a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            mouseButtonLeft = false;
        if (e.getButton() == MouseEvent.BUTTON3)
            mouseButtonRigth = false;

    }

    /** 
     * This method is used for checking, if the mouse entered the component.
     * Invoked when the mouse enters a component.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * This method is used for checking, the mouse continued to be within the component.
     * Invoked when the mouse exits a component.
     * @param e the event to be processed.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /** 
     * This method is used for checking, if the mouse was dragged.
     * If the mouse was drugged,the program also checks, if there is a figure under cursor.
     * It is necessary for changing the cursor and doing other stuff.
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * @param e This is the action listener used to methods with mouse.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseButtonLeft) {
            changeCursor();
            if (figureUnderCursor != null)
                moveFigure(e.getX() - mouseX, e.getY() - mouseY, figureUnderCursor);
        }
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     * @param e the event to be processed.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        figureUnderCursor = findFigure();
        changeCursor();
    }

    /**
     * Used to check the position of cursor and the moving of the wheel.
     * Invoked when the mouse wheel is rotated.
     * @param e the event to be processed
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(figureUnderCursor != null)
        {
            if(e.getWheelRotation() > 0){
                figureUnderCursor.setSize(figureUnderCursor.getSize() - 0.1);
            }
                
            else if(e.getWheelRotation() < 0) {
                figureUnderCursor.setSize(figureUnderCursor.getSize() + 0.1);
            }
                
        }

        repaint();

    }

}
