package View;

import Mode.Mode;
import Shape.Group;
import Shape.Object;
import Shape.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Canvas extends JPanel{

    private static Canvas instance;
    private static List<Object> objects = new ArrayList<>();
    public Rectangle SelectedArea = new Rectangle();
    private EventListener listener = null;
    private List<Object> selectObj = new ArrayList<>();
    public Line line = null;
    private Canvas(){
        this.setBackground(Color.WHITE);
    }
    public static Canvas getInstance(){
        if(instance == null){
            instance = new Canvas();
        }
        return instance;
    }
    public static List<Object> getCanvasObject(){
        return objects;
    }
    public void setMode(Mode mode)
    {
        removeMouseListener((MouseListener) listener);
        removeMouseMotionListener((MouseMotionListener) listener);
        listener = mode;
        addMouseListener(mode);
        addMouseMotionListener((MouseMotionListener) listener);
    }
    public void setSelectObj(int obj_index)
    {
        selectObj.add(objects.get(obj_index));
    }

    public boolean checkSelectedArea(Object obj){
        Point upperLeft = new Point(obj.getX1(), obj.getY1());
        Point lowerRight = new Point(obj.getX2(), obj.getY2());
        if(SelectedArea.contains(upperLeft) && SelectedArea.contains(lowerRight)){
            return true;
        }
        return false;
    }
    public void group(){
        List<Object> groupObj = new ArrayList<>();
        int grouparea_x1 = Integer.MAX_VALUE ,grouparea_x2 = - 1,grouparea_y1 = Integer.MAX_VALUE,grouparea_y2 =-1;
        for ( int i = 0 ; i < selectObj.size(); i++ )
        {
            Object object = selectObj.get(i);
            grouparea_x1 = Math.min(grouparea_x1,object.getX1());
            grouparea_x2 = Math.max(grouparea_x2,object.getX2());
            grouparea_y1 = Math.min(grouparea_y1,object.getY1());
            grouparea_y2 = Math.max(grouparea_y2,object.getY2());
            for ( int j = 0 ; j < objects.size(); j++ )
            {
                Object checkOjb  = objects.get(j);
                if ( object == checkOjb )
                {
                    List<Object> list = object.GetObjectList();
                    for ( Object o : list )
                    {
                        groupObj.add(o);
                    }
                    objects.remove(j);
                }
            }
        }
        Group group = new Group(grouparea_x1,grouparea_y1,Math.abs(grouparea_x1-grouparea_x2), Math.abs(grouparea_y1-grouparea_y2));
        group.SetGroup(groupObj);
        addShape(group);
        this.releaseObj();
    }
    public void ObjUngroup(int index)
    {
        List<Object> gList = selectObj.get(index).GetObjectList();
        for ( int i = 0 ; i < gList.size(); i++ )
        {
            Object obj = gList.get(i);
            objects.add(obj);
        }
        objects.remove(0);
    }
    public void releaseObj()
    {
        for ( int i = 0 ; i < selectObj.size() ; i++)
        {
            Object obj = selectObj.get(i);
            obj.setStatus(false);
        }
        selectObj.clear();
    }

    public void addShape(Object object){
        object.SetPoint();
        objects.add(object);
    }
    public void changeObjName(String name){
        if(selectObj != null){
            selectObj.get(0).changeName(name);
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!SelectedArea.isEmpty()) {
            int alpha = 85;
            g.setColor(new Color(37, 148, 216, alpha));
            g.fillRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
            g.setColor(new Color(37, 148, 216));
            g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
        }

        if ( selectObj != null )
        {
            for ( int i = 0 ; i < selectObj.size() ; i++ )
            {
                for ( int j = 0 ; j < 4 ; j++)
                {
                    g.setColor(new Color(0, 0, 0));
                    g.fillRect((int) selectObj.get(i).Connection_Point[j].getX(), (int) selectObj.get(i).Connection_Point[j].getY(),12,12);
                    g.setColor(new Color(0, 0, 0));
                    g.drawRect((int) selectObj.get(i).Connection_Point[j].getX(), (int) selectObj.get(i).Connection_Point[j].getY(),12,12);
                }
            }
        }

        for(int i = 0; i < objects.size(); i++){
            Object object = objects.get(i);
            object.draw(g);
            for ( int j = 0 ; j < object.LineStart.size() ; j++ )
            {
                Line line = object.LineStart.get(j);
                line.draw(g);
            }
        }
        if( line != null ){
            line.draw(g);
        }

        repaint();
    }

}
