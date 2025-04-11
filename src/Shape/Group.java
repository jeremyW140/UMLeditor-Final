package Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Group extends Object {
    public List<Object> ObjList = new ArrayList<>();
    private Rectangle bounds ;
    public Color selectedColor = new Color(103, 255, 15, 76);
    public Group(int x,int y,int width_,int height_)
    {
        x1 = x;
        y1 = y;
        width = width_;
        height = height_;
        x2 = x + width;
        y2 = y + height;

        bounds = new Rectangle(x,y,width_,height_);
    }
    public void setStatus(boolean select)
    {
        this.selected = select;
        if (select == true)
        {
            selectedColor = new Color(103, 255, 15, 76);
        }
        else
        {
            selectedColor = new Color(255, 255, 255, 0);
        }
    }
    public void SetGroup(List<Object> obj){
        this.ObjList = obj;
    }
    public List<Object> GetObjectList(){
        return this.ObjList;
    }
    public void ResetLocation(int move_X, int move_Y){
        for (Object obj : ObjList) {
            obj.x1 += move_X;
            obj.y1 += move_Y;
            obj.x2 = obj.x1 + obj.width;
            obj.y2 = obj.y1 + obj.height;
            for (Line line : obj.LineStart) {
                line.resetStart(move_X, move_Y);
            }
            for (Line line : obj.LineEnd) {
                line.resetEnd(move_X, move_Y);
            }
        }
        ResetArea(move_X, move_Y);
    }
    public void ResetArea(int move_X, int move_Y){
        bounds.setLocation(bounds.x + move_X, bounds.y + move_Y);
        for (Object obj : ObjList) {
            obj.x1 += move_X;
            obj.y1 += move_Y;
            obj.x2 = obj.x1 + obj.width;
            obj.y2 = obj.y1 + obj.height;
        }
        bounds.setLocation(bounds.x + move_X, bounds.y + move_Y);
        x1 = bounds.x;
        y1 = bounds.y;
        x2 = bounds.x + bounds.width;
        y2 = bounds.y + bounds.height;

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (Object obj : ObjList) {
            minX = Math.min(minX, obj.getX1());
            minY = Math.min(minY, obj.getY1());
            maxX = Math.max(maxX, obj.getX2());
            maxY = Math.max(maxY, obj.getY2());
        }
        bounds.setBounds(minX, minY, maxX - minX, maxY - minY);
    }
    public void SetPoint()
    {
        for ( int i = 0 ; i < Connection_Point.length; i++ )
        {
            this.Connection_Point[i] = new Rectangle(-100,-100,0,0);
        }
    }
    public void show(Graphics g){
        int offset = 10; // 突出的邊界大小
        g.setColor(selectedColor);
        g.fillRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
    }
    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < ObjList.size(); i++){
            Object obj  =ObjList.get(i);
            obj.draw(g);
            List<Line> lines = obj.LineStart;
            for ( int j = 0 ; j < lines.size() ;j++ )
            {
                lines.get(j).draw(g);
            }
        }
        this.show(g);
    }
}
