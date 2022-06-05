package Shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public abstract class Object extends Component{
    protected String objName;
    protected int FontWidth;
    protected boolean selected = false;
    protected final Font font = new Font(Font.DIALOG, Font.BOLD, 14);
    public Rectangle[] Connection_Point = new Rectangle[4];
    public List<Line> LineStart = new ArrayList<>();
    public List<Line> LineEnd = new ArrayList<>();
    protected int x1, y1, x2, y2,width,height;
    public int X_middle,Y_middle;
    public int getX1() {
        return x1;
    }
    public int getX2() {
        return x2;
    }
    public int getY1() {
        return y1;
    }
    public int getY2() {
        return y2;
    }
    public void ResetLocation(int move_X, int move_Y){

        this.x1 += move_X;
        this.y1 += move_Y;
        this.x2 = this.x1 + width;
        this.y2 = this.y1 + height;
        this.SetPoint();
    }
    public void SetPoint(){
        X_middle = (x1+x2)/2;
        Y_middle  = (y1+y2)/2;
        Point[] points = {new Point(X_middle-5, y1-3), new Point(x2-8, Y_middle-3), new Point(X_middle-5, y2-7), new Point(x1-4, Y_middle-3)};
        for ( int i = 0 ; i < points.length ; i++ )
        {
            Connection_Point[i] = new Rectangle(points[i],new Dimension(10,10));
        }
    }
    public boolean getStatus()
    {
        return this.selected;
    }
    public void AddLineStart(Line line){
        LineStart.add(line);
    }
    public void AddLineEnd(Line line){
        LineEnd.add(line);
    }
    public void SetGroup(List<Object> obj){

    }
    public List<Object> GetObjectList(){
        List<Object> l = new ArrayList<Object>();
        l.add(this);
        return l;
    }
    public void setStatus(boolean select)
    {
       this.selected = select;
    }
    public void changeName(String name){
        this.objName = name;
    }
    public abstract void draw(Graphics g);
}
