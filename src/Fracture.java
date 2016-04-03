import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Fracture {

    private Color color;
    private int x, y,id;
    private final int number;
    private boolean selected, expand;

    Fracture(int x, int y,  int counter, int id, boolean selected , Color color , boolean expand) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.number = counter;
        this.selected = selected;
        this.color = color;
        this.expand=expand;
    }

    public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public void setFracture(int x, int y,  int counter, boolean selected)
    {
        this.x = x;
        this.y = y;
        this.id = counter;
        this.selected=selected;
    }
    
    
    public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics2D g2d) { 
        g2d.setColor(color);
        g2d.drawLine(x, y,x,y);

    }

    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, 1, 1);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            color = Color.GREEN;
        } else {
            color = Color.yellow;
        }
    }

    boolean isSelected() {
        return selected;
    }

    int getNumber() {
        return number;
    }
    

    int getId(){
    	return id;
    }
}
