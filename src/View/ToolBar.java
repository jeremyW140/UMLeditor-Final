package View;

import Mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel{
    private Canvas _canvas;
    private static ToolBar toolBar ;
    private final Button[] ModeButtons = new Button[6];
    private final Mode[] Modes = new Mode[6];
    private Mode CURR_Mode;
    private final int SELECT = 0;
    private final int ASSOCIATION = 1;
    private final int GENERALIZATION = 2;
    private final int COMPOSITION = 3;
    private final int CLASS = 4;
    private final int USE_CASE = 5;
    private Button pressedButton = null;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    private ToolBar() {
        initialize();
    }
    public static ToolBar getToolBar(){
        if(toolBar == null){
            toolBar = new ToolBar();
        }
        return toolBar;
    }
    private void initialize() {
        this.setLayout(new GridLayout(6,0,0,0));
        _canvas = Canvas.getInstance();
        Icon SelectIcon = new ImageIcon(new ImageIcon("img/select.png").getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT));
        Icon AssociationIcon = new ImageIcon(new ImageIcon("img/associationline.png").getImage().getScaledInstance(100, 40, Image.SCALE_DEFAULT));
        Icon GeneralizationIcon = new ImageIcon(new ImageIcon("img/generalizationLine.png").getImage().getScaledInstance(110, 25, Image.SCALE_DEFAULT));
        Icon CompositionIcon = new ImageIcon(new ImageIcon("img/compositionline.png").getImage().getScaledInstance(110, 25, Image.SCALE_DEFAULT));
        Icon ClassIcon = new ImageIcon(new ImageIcon("img/class.png").getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT));
        Icon UseCase = new ImageIcon(new ImageIcon("img/usecase.png").getImage().getScaledInstance(100, 70, Image.SCALE_DEFAULT));

        Modes[SELECT] = new SelectMode();
        Modes[ASSOCIATION] = new CreateLineMode(1);
        Modes[GENERALIZATION] = new CreateLineMode(2);
        Modes[COMPOSITION] = new CreateLineMode(3);
        Modes[CLASS] = new CreateObjectMode(1);
        Modes[USE_CASE] = new CreateObjectMode(2);

        ModeButtons[SELECT] = new Button("Select",Modes[SELECT],SelectIcon);
        ModeButtons[ASSOCIATION] = new Button("Association line",Modes[ASSOCIATION],AssociationIcon);
        ModeButtons[GENERALIZATION] = new Button("Generation line",Modes[GENERALIZATION],GeneralizationIcon);
        ModeButtons[COMPOSITION] = new Button("Composition line",Modes[COMPOSITION],CompositionIcon);
        ModeButtons[CLASS] = new Button("Class",Modes[CLASS],ClassIcon);
        ModeButtons[USE_CASE] = new Button("Use case",Modes[USE_CASE],UseCase);


        for( JButton Btn : ModeButtons)
        {
            this.add(Btn);
            Btn.setActionCommand(this.getName());
        }
    }
    public class Button extends JButton{
        private Boolean isPressed;
        private Mode ButtonMode;
        public Button(String name, Mode mode,Icon icon) {
            super(icon);
            addActionListener(new ButtonHandler());
            isPressed = false;
            this.ButtonMode = mode;
        }
        private void setPressedColor(Color background,Color foreground) {
            this.setBackground(background);
            this.setForeground(foreground);
        }
        private Mode getButtonMode()
        {
            return this.ButtonMode;
        }

        private class ButtonHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e){
                if ( pressedButton != null )
                {
                    pressedButton.setPressedColor(new JButton().getBackground(),new JButton().getForeground());
                }
                pressedButton = (Button) e.getSource();
                CURR_Mode = pressedButton.getButtonMode();
                _canvas.setMode(CURR_Mode);
                _canvas.repaint();
                pressedButton.setPressedColor(Color.BLACK,Color.WHITE);

            }
        }
    }
}