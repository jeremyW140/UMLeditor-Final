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
        for(int i = 0; i < ObjList.size(); i++){
            Object obj = ObjList.get(i);
            obj.x1 += move_X;
            obj.y1 += move_Y;
            obj.x2 = obj.x1 + width;
            obj.y2 = obj.y1 + height;
            for ( int j = 0 ; j < obj.LineStart.size() ; j++ ){
                obj.LineStart.get(j).resetStart(move_X,move_Y);
                obj.LineStart.get(j).resetEnd(move_X,move_Y);

            }
        }
        ResetArea(move_X,move_Y);
    }
    public void ResetArea(int move_X, int move_Y){
        bounds.setLocation(bounds.x + move_X, bounds.y + move_Y);
        x1 = bounds.x;
        y1 = bounds.y;
        x2 = bounds.x + bounds.width;
        y2 = bounds.y + bounds.height;
    }
    public void SetPoint()
    {
        for ( int i = 0 ; i < Connection_Point.length; i++ )
        {
            this.Connection_Point[i] = new Rectangle(-100,-100,0,0);
        }
    }
    public void show(Graphics g){
        int offset = 10;
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
