package Shape;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Class extends Object{
    private int body;
    BufferedImage img = null;
    public Class(int x,int y)
    {
        width = 100;
        height = 120;
        x1 = x;
        y1 = y;
        x2 = x + width;
        y2 = y+ height;
        body = height / 3;
        objName = "Class";

    }
    public void draw(Graphics g) {
        try {
            img = ImageIO.read(new File("img/class.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        g.drawImage(img,x1,y1,width,height,null);
        FontWidth = g.getFontMetrics(font).stringWidth(objName);
        double space = (width - FontWidth) / 2;
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(objName, x1 + (int)space, y1 + 25);
    }
}
