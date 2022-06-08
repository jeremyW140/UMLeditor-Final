package Mode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import Shape.Object;
import Shape.Line;

public class SelectMode extends Mode {
    private List<Object> objects = canvas.getCanvasObject();
    int Start_X,Start_Y;
    private Object selected = null;
    private List<Line> selectedStartLine = new ArrayList<>();
    private List<Line> selectedEndLine = new ArrayList<>();;
    public SelectMode()
    {
    }
    public void mousePressed(MouseEvent e) {
        System.out.println(objects.size());
        Start_X = e.getX();
        Start_Y = e.getY();
        int x1,x2,y1,y2;
        canvas.releaseObj();
        for ( int i = objects.size() - 1 ; i >= 0  ; i-- )
        {
            Object obj = objects.get(i);
            x1 = obj.getX1();
            x2 = obj.getX2();
            y1 = obj.getY1();
            y2 = obj.getY2();
            if ( Start_X > x1 && Start_X < x2 && Start_Y > y1 && Start_Y < y2)
            {
                selected = obj;
                selectedStartLine = obj.LineStart;
                selectedEndLine = obj.LineEnd;
                obj.SetPoint();
                obj.setStatus(true); // Bug
                canvas.setSelectObj(i);
                return;
            }
        }
    }
    public void mouseDragged(MouseEvent e) {
        int move_x = e.getX() - Start_X;
        int move_y = e.getY() - Start_Y;
        if ( selected != null )
        {
            selected.ResetLocation(move_x,move_y);
            List<Object> gList = selected.GetObjectList();
            for ( int index = 0 ; index < gList.size() ; index++ )
            {
                Object obj = gList.get(index);
                selectedStartLine = obj.LineStart;
                selectedEndLine = obj.LineEnd;
                for ( int i = 0 ; i <selectedStartLine.size() ; i++ )
                {
                    selectedStartLine.get(i).resetStart(move_x,move_y);
                }
                for ( int i = 0 ; i <selectedEndLine.size() ; i++ )
                {
                    selectedEndLine.get(i).resetEnd(move_x,move_y);
                }
            }canvas.repaint();
            Start_X = e.getX();
            Start_Y = e.getY();
        }
        else
        {
            if(e.getX() > Start_X)
            {
                if ( e.getY() > Start_Y)
                {
                    canvas.SelectedArea.setBounds(Start_X, Start_Y,Math.abs(move_x), Math.abs(move_y));
                }
                else
                {
                    canvas.SelectedArea.setBounds(Start_X, e.getY(),Math.abs(move_x), Math.abs(move_y));
                }
            }
            else {
                if ( e.getY() > Start_Y)
                {
                    canvas.SelectedArea.setBounds(e.getX(), Start_Y,Math.abs(move_x), Math.abs(move_y));
                }
                else
                {
                    canvas.SelectedArea.setBounds(e.getX(), e.getY(),Math.abs(move_x), Math.abs(move_y));
                }
            }
            for ( int i = 0 ; i < objects.size() ; i++ )
            {
                Object obj = objects.get(i);
                if ( canvas.checkSelectedArea(obj) && !obj.getStatus())
                {
                    obj.SetPoint();
                    obj.setStatus(true);
                    canvas.setSelectObj(i);
                }
            }
        }
        canvas.repaint();
    }
    public void mouseReleased(MouseEvent e)
    {
        selected = null;
        canvas.SelectedArea = new Rectangle();
    }
}
