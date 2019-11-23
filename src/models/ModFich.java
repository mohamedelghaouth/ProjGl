package models;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Vue.Acceuil;
import Vue.PanelImage;
import observe.Observable;
import observe.Observateur;

public class ModFich implements Observable {

	private static String exetension[]={"bmp", "gif", "jpg", "jpeg", "png"};

	private Observateur o;
	static private ModFich m = new ModFich();
	
	private ModFich() {}	
	
	private ModFich(Observateur po) {
		o=po;
	}
	
	static public ModFich getModFich() {
		
		if(m==null) {
			m=new ModFich( );
		}
		return m;
	}
	
	public Observateur getO() {
		return o;
	}

	public void setO(Observateur o) {
		this.o = o;
	}

	public void updateModFich(JFileChooser fileOuvrirImage) {
		
			o.updateModFIch(fileOuvrirImage);
		
	}

	public void doOuvrir() {
		
		JFileChooser fileOuvrirImage = new JFileChooser();
		fileOuvrirImage.setAcceptAllFileFilterUsed(false);
		FileFilter imagesFilter = new FileNameExtensionFilter("bmp,gif,jpg,jpeg, png",exetension);
        fileOuvrirImage.addChoosableFileFilter(imagesFilter);
        updateModFich(fileOuvrirImage);
	}
	public void doENrg() {
		
		Acceuil a = (Acceuil)o;
		PanelImage p= (PanelImage)a.getjTabbedPane().getSelectedComponent();
		String format ="JPG";
		JFileChooser fileEnregistrerImage = new JFileChooser();
		if (fileEnregistrerImage.showSaveDialog(a) == JFileChooser.APPROVE_OPTION)
		{
				File fichierEnregistrement = new File(fileEnregistrerImage.getSelectedFile().getAbsolutePath()+ ".JPG");
				try {
					ImageIO.write(p.getMonImage(), format, fichierEnregistrement);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	

}
