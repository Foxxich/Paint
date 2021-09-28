import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*; 

/**
 * This class is used to create JFrame with embedded functions and parameters.
 */
public class MenuFrame extends JFrame {                
    /**
	 *      //The serializable class Menu does not declare a static final serialVersionUID field of type long.
	 */
    private static final long serialVersionUID = 1L;
                        
    /**
     * private parameters for MenuBar.
     */
    private JMenu menu;
    private JMenuItem menuItem;
    private JMenuBar menuBar;
    private JRadioButtonMenuItem rbMenuItem;
    private MyPanel panel = new MyPanel();

    /**
     * INFO is used to show information about program.
     */
    final String INFO = "1. This program is called <PaintLess>. \n" +
                        "2. Program was created by Vadym Liss.\n" +
                        "3. This is a simple version of Paint.\n"+
                        "4. It was made to draw some figures, change their colours and sizes.\n"+
                        "5. The program allows to save and load files just in bin extension.\n"; 

    /**
     * INSTRUCTION is used to show instruction to the program.
     */
    final String INSTRUCTION =  "1.1) User can use this app for painting some figures, such as: square, rectangle, circle and triangle.\n"+
                                "1.2) In Polish, there are: kwadrat, prosotkąt, koło i trójkąt.\n"+
                                "2.1) To add a figure, user should choose menu+\n"+
                                "2.2) Then user should put a cursor on the name of figure+\n"+
                                "2.3) And press left mouse key on the name.\n"+
                                "2.4) The figure is automatically added on the panel.\n"+
                                "3.1) User can add on panel as many figures as user want.\n"+
                                "3.2) It is possible to mark figure as active.\n"+
                                "3.3) User can move every active figure.\n"+
                                "4.1) To change the colour,choose the figure,\n"+
                                "4.2) Put user's cursor on it, press right mouse key,\n"+
                                "4.3) And choose “Change colour”. After pressing on “Change colour”, user will be able to change the colour.\n"+
                                "5.1) To change the size, use the second mouse key and turn the wheel for increasing and decreasing.\n"+
                                "6.1) If user want to load/save file, please choose “File”,\n"+
                                "6.2) put cursor on “Save” or “Load” buttons.\n"+
                                "6.3) Then press left mouse key and user will be given new windows for saving/loading files.\n"+
                                "7.1) It is possible to use just bin file extension.\n"+
                                "8.1) If user wants to get some information about the program,\n"+
                                "8.2) Please choose “Info”, put cursor on “O autorze” button.\n"+
                                "8.3) Then press left mouse key and user will be given information about the program.\n";
                                  

    /**
     * This method is used create a menu.
     */
    @SuppressWarnings("unchecked")
    public MenuFrame()             
    {
        /*
         * PaintLess-name of program
         * menu - creates a menu
         */
        super("PaintLess");
        menu = new JMenu("Menu"); 
        
        /*
         * addActionListener - adds ActionListener to item square.
         * menu.add(menuItem) adds Item to menu.
         */
        menuItem = new JMenuItem("Kwadrat"); 
        menuItem.addActionListener(e -> {
            panel.setNextFigure("square");
        });
        menu.add(menuItem); 

        /*
         * addActionListener - adds ActionListener to item rectangle.
         * menu.add(menuItem) adds Item to menu.
         */
        menuItem = new JMenuItem("Prostokat");
        menuItem.addActionListener(e ->
            {
                panel.setNextFigure("rectangle");
            }
        );
        menu.add(menuItem); 

        /*
         * addActionListener - adds ActionListener to item circle.
         * menu.add(menuItem) adds Item to menu.
        */
        menuItem = new JMenuItem("Kolo");
        menuItem.addActionListener(e ->
            {
                panel.setNextFigure("circle");
            }
        );
        menu.add(menuItem); 

        /*
         * addActionListener - adds ActionListener to item triangle.
         * menu.add(menuItem) adds Item to menu.
         */
        menuItem = new JMenuItem("Trojkat");
        menuItem.addActionListener(e ->
            {
                panel.setNextFigure("triangle");
            }
        );
        menu.add(menuItem);
        
        menuBar = new JMenuBar(); 
        menuBar.add(menu);  // Add menubar to frame.

        /*
         * This method is used to create new menu for files.
         */
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu); 

        menuItem = new JMenuItem("Save");

        menuItem.addActionListener(new ActionListener() 
        {
            /**
             * This actionPerformed(ActionEvent e) method is used to save
             * painted figures in the binary extension file.
             * @param e This is the action listener used to methods with mouse.
             * @param JFileChooser This is file chooser.
             * @param userSelection This is used to select files.
             */
            public void actionPerformed(ActionEvent e) 
            {                    
                JFileChooser fileChooser = new JFileChooser(Paths.get(".").toAbsolutePath().toString());
                fileChooser.setDialogTitle("Specify a file to save");   
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    /*
                     * This method is used to get the Path for saving the file.
                     */
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                    /*
                     * This method is used to give bin extension.
                     * If the user has given the name like bin.bin , the program will use.
                     * It as the name + extension.
                     */
                    if(fileChooser.getSelectedFile().getName().endsWith(".bin")==false)
					    filePath += ".bin";
                    
                    /*
                     * This method is used to write the file.
                     */    
                    try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filePath))){
                        writer.writeObject(panel.getFigures());
                    }
                    /*
                     * This method is used to show an error.
                     */
                    catch (IOException exception)
                    {
                        System.out.println("Error during saving to location: " + filePath);
                    }
                }
            }
        });


        menu.add(menuItem);

        /*
         * This method is used to load files.
         */
        menuItem = new JMenuItem("Load");

        menuItem.addActionListener(e-> {
                /*
                 * This method is used to load the files.
                 * FileNameExtensionFilter - filter for using just bin files.
                 */

                JFileChooser fileChooser = new JFileChooser(Paths.get(".").toAbsolutePath().toString());
                fileChooser.setDialogTitle("Select an image");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary files", "bin");
                fileChooser.addChoosableFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) 
                {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    /*
                     * This method is used to load the file.
                     */
                    try(ObjectInputStream loader = new ObjectInputStream(new FileInputStream(filePath))){
                        
                        panel.setFigures((ArrayList<Figure>)loader.readObject());
                    } 
                    /*
                     * This method is used to show an error.
                     */
                    catch (IOException exception)
                    {
                        System.out.println("Error during loading from location: " + filePath + ", " + exception.getClass());
                    } 
                    /*
                     * This method is used to show an error.
                     */
                    catch (ClassNotFoundException exception)
                    {
                        System.out.println("Error during loading from location: " + filePath + ", " + exception.getClass());
                    }
                    /*
                     * This method is used to show an error.
                     */
                    catch (ClassCastException exception)
                    {
                        System.out.println("Error during loading from location: " + filePath + ", " + exception.getClass());
                    } 
                    
                }
        });

        menu.add(menuItem);

        /*
         * This menu is used to show information.
         */
        menu = new JMenu("Info");

        /*
         * menuItem adds submenu with information about the author and program.
         */
        menuItem = new JMenuItem("O autorze");
        menuItem.addActionListener(e-> {
            JOptionPane.showMessageDialog(this, INFO);
        }
        );
        menu.add(menuItem);

        /*
         * menuItem adds submenu with instruction to the program.
         */
        menuItem = new JMenuItem("Instrukcja");
        menuItem.addActionListener(e-> {
            JOptionPane.showMessageDialog(this, INSTRUCTION);
        }
        );
        menu.add(menuItem);

        menuBar.add(menu);

        this.setJMenuBar(menuBar); 
        this.setContentPane(panel);
        this.setSize(400,400);    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        this.setVisible(true);  
        

    } 
} 
