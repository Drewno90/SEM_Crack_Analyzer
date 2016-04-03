import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

class DrawPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Fracture> fractures = new ArrayList<>();

    public void addFracture(Fracture f) {
        fractures.add(f);
        repaint();
    }
    public void deleteFracture(ArrayList<Fracture> fractures2){
    	fractures.clear();
    }
    public ArrayList<Fracture> getFractures() {
        return fractures;
    }
    
    public Fracture getFracture(int x, int y ,ArrayList<Fracture> fractures){
    	Fracture f = null;
    	for(Fracture s: getFractures())
    	{
    		if(x==s.getX() && y==s.getY()){
    			f=s;break;}
    	}
    	
    	return f;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Fracture fracture : fractures) {
            fracture.draw(g2d);
        }
       
    }

    @Override
    public Dimension getPreferredSize() {
    	if(MyFrame.targetImg.getWidth()!=0 && MyFrame.targetImg.getHeight()!=0)
        return new Dimension(MyFrame.targetImg.getWidth(), MyFrame.targetImg.getHeight());
    	else
    		return new Dimension(0,0); 
    }
}