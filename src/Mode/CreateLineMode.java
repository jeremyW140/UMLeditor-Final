package Mode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import Shape.*;
import Shape.Object;
import Shape.Line;

public class CreateLineMode extends Mode{
    private ObjectFactory objectFactory = new ObjectFactory();
    private int x,y;
    private List<Object> objects = canvas.getCanvasObject();
    private Point start = new Point();
    private boolean connected = false;
    private Point target = new Point();
    private int Source = -1;
    private int Target = -1;
    private int LineMode ;
    private Line tempLine;
    public CreateLineMode(int mode)
    {
        LineMode = mode;
    }

    public void mousePressed(MouseEvent e){
        start = FindPort(e.getPoint());
    }
    public Point FindPort(Point p)
    {
        for ( int i = 0 ; i < objects.size() ; i++)
        {
            Object obj = objects.get(i);
            for ( int j = 0 ; j < obj.Connection_Point.length ; j++)
            {
                if ( Math.abs(p.x - obj.Connection_Point[j].x) < 50 && Math.abs(p.y - obj.Connection_Point[j].y) < 50)
                {
                    if ( Source == -1 )
                    {
                        Source = i;
                    }
                    else
                    {
                        Target = i;
                    }
                    p.x = obj.Connection_Point[j].x + 5;
                    p.y = obj.Connection_Point[j].y + 5;
                    break;
                }
            }

        }
        return p;
    }
    public void mouseDragged(MouseEvent e){
        target.x = e.getX();
        target.y = e.getY();
        if ( Source == -1)
        {
            return;
        }
        target = FindPort(target);
        switch (LineMode) {
            case 1:
                tempLine = objectFactory.createLine("AssociationLine",start,target);
                break;
            case 2:
                tempLine = objectFactory.createLine("GeneralizationLine",start,target);
                break;
            case 3:
                tempLine = objectFactory.createLine("CompositionLine",start,target);
                break;
            default:
                tempLine = null;
        }
        canvas.line = tempLine;
        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e){
        if ( Source  >= 0 &&  Target >= 0  && Target != Source)
        {
            objects.get(Source).AddLineStart(tempLine);
            objects.get(Target).AddLineEnd(tempLine);
        }
        canvas.line = null;
        Source = -1;
        Target = -1;
    }
}
