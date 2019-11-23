package Vue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import contolleur.Controleur;
import models.ModFich;
import models.Models;
import observe.Observateur;



public class Acceuil extends JFrame implements Observateur  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JMenuBar menuBar = new JMenuBar();
	
	private final JMenu fichierMenu = new JMenu();
	private final JMenuItem ouvrirMenu = new JMenuItem();
	private final JMenuItem enregistrerMenu =new JMenuItem();
	private final JMenuItem quitterMenu =new JMenuItem();
	private final JMenuItem fermpann =new JMenuItem();

	private final JMenu     EditionMenu = new JMenu();
	private final JMenuItem RetourMenu = new JMenuItem();
	private final JMenuItem agrandirMenu = new JMenuItem();
	private final JMenuItem reduireMenu = new JMenuItem();
	
	private final JMenu imageMenu = new JMenu();
	private final JMenuItem niveauGrisMenu = new JMenuItem();
	

	
	private final JMenu filtreMenu = new JMenu();
	private final JMenuItem filtremoyenneurMenu = new JMenuItem();
	
	private final JMenuItem filtreCannytMenu = new JMenuItem();
	private final JMenuItem filtreSobelMenu = new JMenuItem();
	private final JMenuItem filtrelaplacienMenu = new JMenuItem();
	private final JMenuItem filtrepriwittMenu = new JMenuItem();

	private final JMenu traitementMenu = new JMenu();
	private final JMenuItem assombrirMenu = new JMenuItem();
	private final JMenuItem brillanceMenu = new JMenuItem();
	private final JMenuItem binarisationMenu = new JMenuItem();
	
	private final JMenu aideMenu = new JMenu();
	private final JMenuItem aproposMenu = new JMenuItem();
	
	private final Welcome  panel=new Welcome();
	
	private   PanelImage panneau =null;
	
	private List<PanelImage> l =new ArrayList<PanelImage>();
	
	private ModFich m=ModFich.getModFich(); 
	private Models M=Models.getModFich();
	
	private Controleur	cf= Controleur.getCont(this);
	private JTabbedPane jTabbedPane=null;


	
	
	public PanelImage getPanneau() {
		return panneau;
	}
	public void setPanneau(PanelImage panneau) {
		this.panneau = panneau;
	}
	public List<PanelImage> getL() {
		return l;
	}
	public void setL(List<PanelImage> l) {
		this.l = l;
	}
	
	public JTabbedPane getjTabbedPane() {
		return jTabbedPane;
	}
	public void setjTabbedPane(JTabbedPane jTabbedPane) {
		this.jTabbedPane = jTabbedPane;
	}

	
	
	public Welcome getPanel() {
		return panel;
	}
	public Acceuil() {
		super();
		m.setO(this);
		M.setA(this);
		setBounds(150, 100,1000,600);
		setTitle("Traitement d'image ");
		jTabbedPane=new JTabbedPane();
	
      
		jTabbedPane.add("Accueil",panel);	
		getContentPane().add(jTabbedPane);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		try {
			creerMenu();
			desactiveMenu();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//
	}
	public void quitter(){
		setVisible(false);
		System.exit(DISPOSE_ON_CLOSE);
	}
	private void creerMenu()   {

		// construction du menu
		setJMenuBar(menuBar);	
		menuBar.add(fichierMenu);
		fichierMenu.setText("Fichier");
		fichierMenu.add(ouvrirMenu);
		ouvrirMenu.setText("ouvrir");
		ouvrirMenu.setActionCommand(Controleur.OUVRIR_ACTION);
		ouvrirMenu.addActionListener(cf);
		fichierMenu.addSeparator();

		fichierMenu.add(enregistrerMenu);
		enregistrerMenu.setText("enregistrer");
		enregistrerMenu.setActionCommand(Controleur.ENG_ACTION);
		enregistrerMenu.addActionListener(cf);
		fichierMenu.addSeparator();
		
		fichierMenu.add(quitterMenu);
		quitterMenu.setText("Quitter");
		quitterMenu.setActionCommand(Controleur.Quitter_ACTION);
		quitterMenu.addActionListener(cf);
		
		fichierMenu.add(fermpann);
		fermpann.setText("fermpann");
		fermpann.setActionCommand(Controleur.Fermer_pann_ACTION);
		fermpann.addActionListener(cf);
		
		menuBar.add(EditionMenu);
		EditionMenu.setText("Edition");
		EditionMenu.add(RetourMenu);
		RetourMenu.setText("Retour");
		RetourMenu.setActionCommand(Controleur.Retour_ACTION);
		RetourMenu.addActionListener(cf);
		EditionMenu.addSeparator();
		
		EditionMenu.add(agrandirMenu);
		agrandirMenu.setActionCommand(Controleur.Agrandir_ACTION);
		agrandirMenu.addActionListener(cf);
		agrandirMenu.setText("Agrandir");
		EditionMenu.add(reduireMenu);
		reduireMenu.setActionCommand(Controleur.Reduire_ACTION);
		reduireMenu.addActionListener(cf);
		reduireMenu.setText("Réduire");
		//imageMenu.addSeparator();
		
		menuBar.add(imageMenu);
		imageMenu.setText("Image");
		
		imageMenu.add(niveauGrisMenu);
		niveauGrisMenu.setActionCommand(Controleur.NiveGris_ACTION);
		niveauGrisMenu.addActionListener(cf);
		niveauGrisMenu.setText("Niveau de Gris");
		imageMenu.addSeparator();
		
		

		
		menuBar.add(filtreMenu);	
		filtreMenu.setText("Filtre");
		
		filtreMenu.add(filtremoyenneurMenu);
		filtremoyenneurMenu.setActionCommand(Controleur.Filter_Moy_ACTION);
		filtremoyenneurMenu.addActionListener(cf);
		filtremoyenneurMenu.setText("Filtre Moyenneur");
		
		


		filtreMenu.addSeparator();
		
		
		filtreMenu.add(filtreSobelMenu);
		filtreSobelMenu.setText("Filtre Sobel");
		filtreSobelMenu.setActionCommand(Controleur.Filter_Sobel_ACTION);		
		filtreSobelMenu.addActionListener(cf);
		
	
		filtreMenu.add(filtrepriwittMenu);
		filtrepriwittMenu.setText("Filtre Priwitt");
		filtrepriwittMenu.setActionCommand(Controleur.Filter_Priwitt_ACTION);		
		filtrepriwittMenu.addActionListener(cf);	
		
		filtreMenu.add(filtrelaplacienMenu);
		filtrelaplacienMenu.setText("Filtre Laplacien");
		filtrelaplacienMenu.setActionCommand(Controleur.Filter_LePlacien_ACTION);		
		filtrelaplacienMenu.addActionListener(cf);			
			
		filtreMenu.add(filtreCannytMenu);
		filtreCannytMenu.setText("Filtre Canny");
		filtreCannytMenu.setActionCommand(Controleur.Filter_Canny_ACTION);		
		filtreCannytMenu.addActionListener(cf);						
			
		
        menuBar.add(traitementMenu);
        traitementMenu.setText("Traitement");
		

		traitementMenu.add(binarisationMenu);
		binarisationMenu.setActionCommand(Controleur.binairisation_ACTION);		
		binarisationMenu.addActionListener(cf);			
		binarisationMenu.setText("Binarisation");
		traitementMenu.addSeparator();

		traitementMenu.add(assombrirMenu);
		assombrirMenu.setActionCommand(Controleur.Assomblir_ACTION);		
		assombrirMenu.addActionListener(cf);			
		assombrirMenu.setText("Assombrir");
		traitementMenu.addSeparator();

	    traitementMenu.add(brillanceMenu);
	    brillanceMenu.setActionCommand(Controleur.Brillance_ACTION);		
	    brillanceMenu.addActionListener(cf);
		brillanceMenu.setText("Brillance");
		
		
        menuBar.add(aideMenu);
        aideMenu.setText("Aide");
        aideMenu.add(aproposMenu);
        aproposMenu.setActionCommand(Controleur.Apropos_ACTION);		
        aproposMenu.addActionListener(cf);
        aproposMenu.setText("A propos");
		

		// ajouter le panneau de dessin 
        
		
	}  
	private  void  desactiveMenu(){
		enregistrerMenu.setEnabled(false);
		fermpann.setEnabled(false);

		RetourMenu.setEnabled(false);

	    filtremoyenneurMenu.setEnabled(false);
		
		filtreCannytMenu.setEnabled(false);
		 filtreSobelMenu.setEnabled(false);
		filtrelaplacienMenu.setEnabled(false);
	    filtrepriwittMenu.setEnabled(false);

	    niveauGrisMenu.setEnabled(false);
		assombrirMenu.setEnabled(false);
		 brillanceMenu.setEnabled(false);
		binarisationMenu.setEnabled(false);
		
		 agrandirMenu.setEnabled(false);
		 reduireMenu.setEnabled(false);
	
}
	private  void  activeMenu(){
		enregistrerMenu.setEnabled(true);
		fermpann.setEnabled(true);
		RetourMenu.setEnabled(true);
		
	    filtremoyenneurMenu.setEnabled(true);
	
		filtreCannytMenu.setEnabled(true);
		filtreSobelMenu.setEnabled(true);
		filtrelaplacienMenu.setEnabled(true);
	    filtrepriwittMenu.setEnabled(true);

	    niveauGrisMenu.setEnabled(true);
		assombrirMenu.setEnabled(true);
		 brillanceMenu.setEnabled(true);
		binarisationMenu.setEnabled(true);
		
		 agrandirMenu.setEnabled(true);
		 reduireMenu.setEnabled(true);
		
	}
	
	public void updateModFIch(JFileChooser fileOuvrirImage) {
		
		if (fileOuvrirImage.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			   panneau = new PanelImage();
			   l.add(panneau);
			   panneau.ajouterImage(new File(fileOuvrirImage.getSelectedFile().getAbsolutePath()));
			   
			   jTabbedPane.add(panneau.nomImage,panneau);
			   jTabbedPane.setSelectedComponent(panneau);
			   getContentPane().add(jTabbedPane);
			   activeMenu();
		}
		
	}
	public void doaprop() {
		String txt="Projet de Traitement d'image en Java:Swing,MVC";
		JOptionPane.showMessageDialog(null,txt);
	}
	
	public void fermerpann() {
		jTabbedPane.remove(jTabbedPane.getSelectedComponent());
		l.remove(jTabbedPane.getSelectedComponent());
	}
	
	public static void main(String args[]) 
	{
		try {
			Acceuil frame = new Acceuil();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
