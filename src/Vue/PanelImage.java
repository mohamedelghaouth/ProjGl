package Vue;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import contolleur.Controleur;



public class PanelImage extends JPanel {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    BufferedImage monImage=null;
    BufferedImage imageOri=null;
    String nomImage;


	public PanelImage() {
		super();
		
		//setBounds(100,100,200,200);
	}
	
	public BufferedImage getMonImage() {
		return monImage;
	}

	public void setMonImage(BufferedImage monImage) {
		this.monImage = monImage;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(monImage != null) {
			g.drawImage(monImage,0,0,null);
			}
	}
	
	public void Repaint(BufferedImage pmonImage) {
    	monImage=pmonImage;
    	repaint();
    }
	
	public boolean ExisteImage(){
		 if(monImage==null) return false;
		  else return true;
	}

	public void ajouterImage(File fichierImage)
	{  
		try {
			 nomImage=fichierImage.getName();
			monImage = ImageIO.read(fichierImage);
			imageOri= ImageIO.read(fichierImage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint(); 
	
	}

	public void Retour(){
		monImage=imageOri;
		repaint();
		
	}

	
}
