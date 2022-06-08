package View;

import javax.swing.*;
import java.awt.*;
import View.MenuBar;
public class UMLFrame extends JFrame {
    private ToolBar toolBar;
    public Canvas canvas;
    public UMLFrame()
    {
        super("UMLeditor");
        toolBar = ToolBar.getToolBar();
        canvas = Canvas.getInstance();
        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas,BorderLayout.CENTER);
        this.setJMenuBar(new MenuBar(canvas));
    }
}
