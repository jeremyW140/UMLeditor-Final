package Mode;

import Shape.Object;
import Shape.Line;

import java.awt.*;

public interface ObjectFactoryInterface {
    Object createObj(String objType,int x,int y);
    Line createLine(String objType, Point start,Point end);
}
