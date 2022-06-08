package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Canvas;
public class MenuBar extends JMenuBar{
    public MenuBarHandler menubarhandler;
    private Canvas canvas;
    public MenuBar(Canvas c)
    {
        this.canvas = c;
        this.initialize();
    }
    private void initialize()
    {
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

        menubarhandler = new MenuBarHandler(canvas);
        changeObjName.addActionListener(menubarhandler);
        groupObject.addActionListener(menubarhandler);
        ungroupObject.addActionListener(menubarhandler);


        EditTab.add(changeObjName);
        EditTab.add(groupObject);
        EditTab.add(ungroupObject);

        menuBar.add(FileTab);
        menuBar.add(EditTab);
        this.add(menuBar);
    }
    private class MenuBarHandler implements ActionListener {
        public Canvas canvas;
        public MenuBarHandler(Canvas c)
        {
            this.canvas = c;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("ChangeName")){
                changeName();
            }
            else if (e.getActionCommand().equals("Group")){
                canvas.group();
            }
            else if (e.getActionCommand().equals("Ungroup")){
                canvas.ObjUngroup(0);
            }
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

}
