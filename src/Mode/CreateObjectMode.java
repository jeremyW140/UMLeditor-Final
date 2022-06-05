package Mode;

import java.awt.event.MouseEvent;


import Shape.Object;

public class CreateObjectMode extends Mode{
    private ObjectFactory objectFactory = new ObjectFactory();
    private int x,y;
    private int ObjectMode;
    private Object obj;
    public CreateObjectMode(int m)
    {
        ObjectMode = m;
    }
    public void mousePressed(MouseEvent e){
        x = e.getX();
        y = e.getY();
        switch (ObjectMode) {
            case 1:
                obj = objectFactory.createObj("Class",x,y);
                break;
            case 2:
                obj = objectFactory.createObj("UseCase",x,y);
                break;
            default:
                obj = null;
        }
        canvas.addShape(obj);
        canvas.repaint();
    }
}
