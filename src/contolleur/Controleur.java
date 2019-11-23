package contolleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import Vue.Acceuil;
import Vue.PanelImage;
import models.ModFich;
import models.Models;

public class Controleur implements ActionListener  {

	private Acceuil f;
	static private Controleur c = null;

	public final static String ENG_ACTION = "enregistrer";// probléme dans l'image final enregistrer
	public final static String OUVRIR_ACTION = "ouvrir";// la position de l'image au debut n'est pas parfait
	
	public final static String Quitter_ACTION = "quiter";
	public final static String Retour_ACTION = "retour";//il ya un probleme a resoudre
	public final static String Agrandir_ACTION = "Agrandir";
	public final static String Reduire_ACTION = "reduire";
	
	public final static String NiveGris_ACTION = "nivgris";
	
	public final static String Filter_Moy_ACTION = "filtermoy";
	public final static String Filter_Canny_ACTION = "Canny";
	public final static String Filter_Sobel_ACTION = "Sobel";
	public final static String Filter_LePlacien_ACTION = "LePlacien";
	public final static String Filter_Priwitt_ACTION = "Priwitt";
	
	public final static String binairisation_ACTION = "binairisation";
	public final static String Assomblir_ACTION = "Assomblir";
	public final static String Brillance_ACTION = "Brillance";
	
	public final static String Apropos_ACTION = "apropos";

	public final static String Fermer_pann_ACTION = "fermerpan";//vide :a faire

	
	private Controleur(Acceuil pf) {
		f=pf;
	}
   static public Controleur getCont(Acceuil pf) {
		
		if(c==null) {
			c=new Controleur(pf);
		}
		return c;
	}
	
	

	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		if (action == ENG_ACTION)    {ModFich.getModFich().doENrg();} 
		else if (action == OUVRIR_ACTION)   {ModFich.getModFich().doOuvrir();}
		else if (action == Quitter_ACTION)  {f.quitter();}
		
		else if (action == Retour_ACTION)  
		{	
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			p.Retour();
		}
		else if (action == Agrandir_ACTION) 
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().agrandirImage(p.getMonImage());
		}
		else if (action == Reduire_ACTION) 
		{	
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().reduireImage(p.getMonImage());
		}
		
		else if (action == NiveGris_ACTION)  
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().imageEnNiveauGris(p.getMonImage(),p);
			
		}
		
		
		else if (action == Filter_Moy_ACTION) 
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().filtreMoyenneur(p.getMonImage(),p);
		}
		
		else if (action == Filter_Canny_ACTION) 
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().filterCanny(p.getMonImage(),p);
		}
		else if (action == Filter_Sobel_ACTION) 
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().filtreSobbel(p.getMonImage(),p);
		}
		else if (action == Filter_LePlacien_ACTION) 
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().filtreLaplacien(p.getMonImage(),p);
		}
		
		else if (action == Filter_Priwitt_ACTION)  
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().filtrePrwitt(p.getMonImage(),p);
		}
		
		else if (action == binairisation_ACTION)  
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().imageBinaire(p.getMonImage(),p);
			
		}
		else if (action == Brillance_ACTION)  
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().imageEclaircie(p.getMonImage(),p);
			
		}
		else if (action == Assomblir_ACTION)  
		{
			PanelImage p =(PanelImage)f.getjTabbedPane().getSelectedComponent();
			Models.getModFich().imageSombre(p.getMonImage(),p);
			
		}
		
		else if (action == Apropos_ACTION)  
		{
			f.doaprop();
			
		}
		else if (action == Fermer_pann_ACTION)  
		{
			f.fermerpann();			
		}
	}

	}


