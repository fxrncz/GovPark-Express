package Design_Libraries;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Container extends JPanel{

    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBotLeft() {
        return roundBotLeft;
    }

    public void setRoundBotLeft(int roundBotLeft) {
        this.roundBotLeft = roundBotLeft;
        repaint();
    }

    public int getRoundBotRight() {
        return roundBotRight;
    }

    public void setRoundBotRight(int roundBotRight) {
        this.roundBotRight = roundBotRight;
        repaint();
    }
    
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBotLeft = 0;
    private int roundBotRight = 0;
    
    
    public Container() {
        this.setOpaque(false);
    }
    
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(createRoundTopLeft());
        
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        
        if (roundBotRight > 0) {
            area.intersect(new Area(createRoundBotRight()));
        }
        
        if (roundBotLeft > 0) {
            area.intersect(new Area(createRoundBotLeft()));
        }
        
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, width)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }
    
    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, width)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }
    
    private Shape createRoundBotLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBotLeft);
        int roundY = Math.min(height, roundBotLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, width)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
    
        
    private Shape createRoundBotRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBotRight);
        int roundY = Math.min(height, roundBotRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, width)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
}
