package Mode;

import Shape.*;
import Shape.Class;
import Shape.Object;

import java.awt.*;

public class ObjectFactory implements ObjectFactoryInterface{

    @Override
    public Object createObj(String ObjType,int x,int y) {
        if(ObjType.equals("Class"))
        {
            return new Class(x,y);
        }
        else if(ObjType.equals("UseCase"))
        {
            return new UseCase(x,y);
        }
        return null;
    }

    @Override
    public Line createLine(String LineType,Point start,Point end) {
        if(LineType.equals("AssociationLine")){
            return new AssociationLine(start,end);
        }
        else if(LineType.equals("GeneralizationLine")){
            return new GeneralizationLine(start,end);
        }
        else if(LineType.equals("CompositionLine")){
            return new CompositionLine(start,end);
        }
        return null;
    }
}
