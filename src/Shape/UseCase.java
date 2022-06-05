package Shape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class UseCase extends Object {
    BufferedImage img = null;
    public UseCase(int x,int y)
    {
        width = 120;
        height = 90;
        x1 = x;
        y1 = y;
        x2=  x1 + width;
        y2 = y1 + height;
        objName = "Use Class";
    }
    public void draw(Graphics g) {
        try {
            img = ImageIO.read(new File("img/usecase.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        g.drawImage(img,x1,y1,width,height,null);
        FontWidth = g.getFontMetrics(font).stringWidth(objName);
        double space = (width - FontWidth) / 2;
        g.setFont(font);
        g.drawString(objName, x1 + (int)space, y1 + 50);
    }
}
