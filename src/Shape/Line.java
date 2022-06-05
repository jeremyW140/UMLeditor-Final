package Shape;

import java.awt.*;

public abstract class Line extends Object {
    public Point front, end;
    public Line() {
    }
    public Line(Point front, Point end) {
        this.front = front;
        this.end = end;
    }
    public void resetStart(int move_X,int move_Y)
    {
        this.x1 += move_X;
        this.y1 += move_Y;
    }
    public void resetEnd(int move_X,int move_Y)
    {
        this.x2 += move_X;
        this.y2 += move_Y;
    }
    public void draw(Graphics g) {

    }

}
