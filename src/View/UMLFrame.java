package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UMLFrame extends JFrame {
    // GridBagConstraints c = new GridBagConstraints();
    private ToolBar toolBar;
    public Canvas canvas;

    public UMLFrame()
    {
        super("UMLeditor");
        toolBar = ToolBar.getToolBar();
        canvas = Canvas.getInstance();

        this.add(toolBar, BorderLayout.WEST);
        this.add(canvas,BorderLayout.CENTER);
        this.initMenuBar();
    }

    public void initMenuBar(){
        JMenuBar menuBar;
        JMenu FileTab;
        JMenu EditTab;
        JMenuItem groupObject;
        JMenuItem ungroupObject;
        JMenuItem changeObjName;
        menuBar = new JMenuBar();

        FileTab = new JMenu("File");
        EditTab = new JMenu("Edit");

        changeObjName = new JMenuItem("ChangeName");
        groupObject = new JMenuItem("Group");
        ungroupObject = new JMenuItem("Ungroup");

        changeObjName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeName();
            }
        });

        groupObject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.group();
            }
        });

        ungroupObject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.ObjUngroup(0);
            }
        });


        EditTab.add(changeObjName);
        EditTab.add(groupObject);
        EditTab.add(ungroupObject);

        menuBar.add(FileTab);
        menuBar.add(EditTab);
        this.setJMenuBar(menuBar);
    }

    private void changeName(){
        JFrame frame = new JFrame("Change Name");
        frame.setSize(400, 100);
        frame.setLayout(new BorderLayout());

        JTextField inputText = new JTextField("Object Name");
        frame.add(inputText, BorderLayout.NORTH);

        JButton confirm = new JButton("OK");
        frame.add(confirm, BorderLayout.WEST);

        JButton cancel = new JButton("Cancel");
        frame.add(cancel, BorderLayout.CENTER);

        frame.setVisible(true);

        confirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.changeObjName(inputText.getText());
                frame.dispose();
            }
        });

        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}
