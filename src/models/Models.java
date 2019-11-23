package models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;

import Vue.Acceuil;
import Vue.PanelImage;

public class Models {

	private Acceuil a;
	
	public Acceuil getA() {
		return a;
	}

	public void setA(Acceuil a) {
		this.a = a;
	}

	static private Models m = new Models();
	
	private Models() {}	
	
	static public Models getModFich() {
		
		if(m==null) {
			m=new Models( );
		}
		return m;
	}
	
	public void reduireImage(BufferedImage monImage)
	{
		BufferedImage imageReduite = new BufferedImage((int)(monImage.getWidth()*0.9),(int)( monImage.getHeight()*0.9), monImage.getType());
		
		AffineTransform reduire = AffineTransform.getScaleInstance(0.9, 0.9);
		int interpolation = AffineTransformOp.TYPE_BICUBIC;
		AffineTransformOp retaillerImage = new AffineTransformOp(reduire, interpolation);
		
		retaillerImage.filter(monImage, imageReduite );
		monImage = imageReduite ;
		a.getPanneau().Repaint(monImage);
	}

	public void agrandirImage(BufferedImage monImage)
	{
		BufferedImage imageZoomer = new BufferedImage((int)(monImage.getWidth()*1.1),(int)( monImage.getHeight()*1.1), monImage.getType());
		
		AffineTransform agrandir = AffineTransform.getScaleInstance(1.1, 1.1);
		int interpolation = AffineTransformOp.TYPE_BICUBIC;
		AffineTransformOp retaillerImage = new AffineTransformOp(agrandir, interpolation);
		
		retaillerImage.filter(monImage, imageZoomer );
		
		monImage = imageZoomer ;
		a.getPanneau().Repaint(monImage);
	}

	public void filtreMoyenneur(BufferedImage monImage,PanelImage p)
	{
		BufferedImage image = new BufferedImage(monImage.getWidth(),monImage.getHeight(), monImage.getType());
		float[ ] masqueMedian = 
		{
				1/9f, 1/9f, 1/9f,
				1/9f, 1/9f, 1/9f,
				1/9f, 1/9f, 1/9f
		};
       
		Kernel masque = new Kernel(3, 3, masqueMedian);
		ConvolveOp opération = new ConvolveOp(masque);
		opération.filter(monImage, image);
		
		monImage = image;
		System.out.println("convolution effectuée");
		p.Repaint(monImage);
	}
	
	public void filtreSobbel(BufferedImage monImage,PanelImage p)
	{
		BufferedImage imageX = new BufferedImage(monImage.getWidth(),monImage.getHeight(), monImage.getType());
		float[ ] masqueSoblX = 
		{
				-1f, 0f, 1f,
				-2f, 0f, 2f,
				-1f, 0f, 1f
		};
       
		Kernel masqueX = new Kernel(3, 3, masqueSoblX);
		ConvolveOp opération = new ConvolveOp(masqueX);
		opération.filter(monImage, imageX);
		
		BufferedImage imageY = new BufferedImage(monImage.getWidth(),monImage.getHeight(), monImage.getType());
		float[ ] masqueSoblY = 
		{
			    -1f, -2f, 1f,
				0f, 0f, 0f,
				1f, 2f, 1f
		};
       
		Kernel masqueY = new Kernel(3, 3, masqueSoblY);
		ConvolveOp opération1 = new ConvolveOp(masqueY);
		opération1.filter(monImage, imageY);
		
		monImage = imageX;
		System.out.println("convolution effectuée");
		p.Repaint(monImage);

	}
	
	public void filtrePrwitt(BufferedImage monImage,PanelImage p)
	{
	
	try {
		
		

		  int [][] pixel= new int[monImage.getWidth()][monImage.getHeight()];
		  

		   
		  	  		int x,y,g;
		  	
		  	
		  	
		//***************************************************
		//Conversion enniveau du Gris


			for (int i = 0; i < monImage.getWidth(); i++) {
				for (int j = 0; j < monImage.getHeight(); j++) {

			

			
				Color pixelcolor= new Color(monImage.getRGB(i,j));

			
				int r=pixelcolor.getRed();
				int gb=pixelcolor.getGreen();
				int b=pixelcolor.getBlue();
				
		int hy=(r+gb+b)/3;
				  
					
			int rgb=new Color(hy,hy,hy).getRGB();
				
				// changer la couleur de pixel avec la nouvelle couleur inversée
				monImage.setRGB(i, j, rgb);

				}
			}	
			


		//***************************************************  	
		  	

				// parcourir les pixels de l'image
			for (int i = 0; i < monImage.getWidth(); i++) 
			{
				for (int j = 0; j < monImage.getHeight(); j++) 
				{
				
				// recuperer couleur de chaque pixel
				Color pixelcolor= new Color(monImage.getRGB(i, j));

				
				// recuperer les valeur rgb (rouge ,vert ,bleu) de cette couleur
				 pixel[i][j]=monImage.getRGB(i, j);

			
				}
		    }

		//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

			for (int i = 1; i < monImage.getWidth()-2; i++) 
			{
				for (int j = 1; j < monImage.getHeight()-2; j++) 
				{
					
		//pixel[i][j]=(pixel[i-1][j-1]+pixel[i-1][j]+pixel[i-1][j+1]+pixel[i][j-1]+pixel[i][j]+pixel[i][j+1]+pixel[i+1][j-1]+pixel[i+1][j]+pixel[i+1][j+1])/9;			
					
		x=(pixel[i][j+2]+pixel[i+1][j+2]+pixel[i+2][j+2])-(pixel[i][j]+pixel[i+1][j]+pixel[i+2][j]);
		y = (pixel[i + 2][j] + pixel[i + 2][j + 1] + pixel[i + 2][j + 2]) - (pixel[i][j] +  pixel[i][j + 1] + pixel[i][j + 2]);
		            
		g=Math.abs(x)+Math.abs(y);	


		//System.out.println(g);
		pixel[i][j]=g;

			}
		    }



		//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*





		//**********************************************************************************	
				
				
				
				for (int i = 0; i < monImage.getWidth(); i++) {
				for (int j = 0; j < monImage.getHeight(); j++) {

			

			
				Color pixelcolor= new Color(pixel[i][j]);

			
				int r=pixelcolor.getRed();
				int gb=pixelcolor.getGreen();
				int b=pixelcolor.getBlue();
				

				  
					
			int rgb=new Color(r,gb,b).getRGB();
				
				// changer la couleur de pixel avec la nouvelle couleur inversée
				monImage.setRGB(i, j, rgb);

				}
			}	
			


				p.repaint();


			}
			
			
			 catch (Exception e) {
				System.err.println("erreur -> "+e.getMessage());
			}
			
			System.out.println("fin");

			}

	public void filtreLaplacien(BufferedImage monImage,PanelImage p)
	{
		try {
			
			

	  int [][] pixel= new int[monImage.getWidth()][monImage.getHeight()];
	  

	   
	  	  		int x,y,g;
	  	
	  	
	  	
	//***************************************************
	//Conversion enniveau du Gris


		for (int i = 0; i < monImage.getWidth(); i++) {
			for (int j = 0; j < monImage.getHeight(); j++) {

		

		
			Color pixelcolor= new Color(monImage.getRGB(i,j));

		
			int r=pixelcolor.getRed();
			int gb=pixelcolor.getGreen();
			int b=pixelcolor.getBlue();
			
	int hy=(r+gb+b)/3;
			  
				
		int rgb=new Color(hy,hy,hy).getRGB();
			
			// changer la couleur de pixel avec la nouvelle couleur inversée
			monImage.setRGB(i, j, rgb);

			}
		}	
		


	//***************************************************  	
	  	

			// parcourir les pixels de l'image
		for (int i = 0; i < monImage.getWidth(); i++) 
		{
			for (int j = 0; j < monImage.getHeight(); j++) 
			{
			
			// recuperer couleur de chaque pixel
			Color pixelcolor= new Color(monImage.getRGB(i, j));

			
			// recuperer les valeur rgb (rouge ,vert ,bleu) de cette couleur
			 pixel[i][j]=monImage.getRGB(i, j);

		
			}
	    }

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

		for (int i = 1; i < monImage.getWidth()-2; i++) 
		{
			for (int j = 1; j < monImage.getHeight()-2; j++) 
			{
				
				
	x=-pixel[i][j]-pixel[i+1][j]-pixel[i+2][j]-pixel[i+1][j]+8*pixel[i+1][j+1]-pixel[i+1][j+2]-pixel[i+2][j]-pixel[i+2][j+1]-pixel[i+2][j+2];


	pixel[i][j]=x;

		}
	    }



	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*





	//**********************************************************************************	
			
			
			
			for (int i = 0; i < monImage.getWidth(); i++) {
			for (int j = 0; j < monImage.getHeight(); j++) {

		

		
			Color pixelcolor= new Color(pixel[i][j]);

		
			int r=pixelcolor.getRed();
			int gb=pixelcolor.getGreen();
			int b=pixelcolor.getBlue();
			

			  
				
		int rgb=new Color(r,gb,b).getRGB();
			
			// changer la couleur de pixel avec la nouvelle couleur inversée
			monImage.setRGB(i, j, rgb);

			}
		}	
		

		
			// enregistrement d'image
        p.repaint();
			


		}
		
		
		 catch (Exception e) {
			System.err.println("erreur -> "+e.getMessage());
		}

	}
	
	public void filterCanny(BufferedImage monImage,PanelImage p){
  
  	
		
		try {
		

  int [][] pixel= new int[monImage.getWidth()][monImage.getHeight()];
  

   
  	  		int x,y,g;
  	
  	
  	
//***************************************************
//Conversion enniveau du Gris


	for (int i = 0; i < monImage.getWidth(); i++) {
		for (int j = 0; j < monImage.getHeight(); j++) {

	

	
		Color pixelcolor= new Color(monImage.getRGB(i,j));

	
		int r=pixelcolor.getRed();
		int gb=pixelcolor.getGreen();
		int b=pixelcolor.getBlue();
		
int hy=(r+gb+b)/3;
		  
			
	int rgb=new Color(hy,hy,hy).getRGB();
		
		// changer la couleur de pixel avec la nouvelle couleur inversée
		monImage.setRGB(i, j, rgb);

		}
	}	
	


//***************************************************  	
  	

		// parcourir les pixels de l'image
	for (int i = 0; i < monImage.getWidth(); i++) 
	{
		for (int j = 0; j < monImage.getHeight(); j++) 
		{
		
		// recuperer couleur de chaque pixel
		Color pixelcolor= new Color(monImage.getRGB(i, j));

		
		// recuperer les valeur rgb (rouge ,vert ,bleu) de cette couleur
		 pixel[i][j]=monImage.getRGB(i, j);

	
		}
    }

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

	for (int i = 1; i < monImage.getWidth()-2; i++) 
	{
		for (int j = 1; j < monImage.getHeight()-2; j++) 
		{
			
//pixel[i][j]=(pixel[i-1][j-1]+pixel[i-1][j]+pixel[i-1][j+1]+pixel[i][j-1]+pixel[i][j]+pixel[i][j+1]+pixel[i+1][j-1]+pixel[i+1][j]+pixel[i+1][j+1])/9;			
			
x=-pixel[i][j]+pixel[i][j+2];
y=pixel[i][j]-pixel[i+2][j];            
g=Math.abs(x)+Math.abs(y);	


//System.out.println(g);
pixel[i][j]=g;

	}
    }



//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*





//**********************************************************************************	
		
		
		
		for (int i = 0; i < monImage.getWidth(); i++) {
		for (int j = 0; j < monImage.getHeight(); j++) {

	

	
		Color pixelcolor= new Color(pixel[i][j]);

	
		int r=pixelcolor.getRed();
		int gb=pixelcolor.getGreen();
		int b=pixelcolor.getBlue();
		

		  
			
	int rgb=new Color(r,gb,b).getRGB();
		
		// changer la couleur de pixel avec la nouvelle couleur inversée
		monImage.setRGB(i, j, rgb);

		}
	}	
	

	
		// enregistrement d'imag		
		p.repaint();

	}
	
	
	 catch (Exception e) {
		System.err.println("erreur -> "+e.getMessage());
	}
		p.repaint();

	System.out.println("fin");
	}
	
	public void imageEclaircie(BufferedImage monImage,PanelImage p)
	{
		/*
		 *    RescaleOp brillance = new RescaleOp(A, K, null);
		 *    1.  A< 1, l’image devient plus sombre.
   			  2.  A > 1, l’image devient  plus brillante.
   			  3. K est compris entre 0 et 256 et ajoute un éclairement .
		 */
		BufferedImage imgBrillant = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		RescaleOp brillance = new RescaleOp(1.2f, 0, null);
		
		brillance.filter(monImage, imgBrillant);
		monImage = imgBrillant;
		p.Repaint(monImage);
	}

	public void imageSombre(BufferedImage monImage,PanelImage p)
	{
		/* RescaleOp assombrir = new RescaleOp(A, K, null);
		 *    
		 *    1.  A < 1, l’image devient plus sombre.
   			  2.  A > 1, l’image devient  plus brillante.
   			  3.  K est compris entre 0 et 256 et ajoute un éclairement .
		 *    
		 */		
		BufferedImage imgSombre = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		RescaleOp assombrir = new RescaleOp(0.7f, 10, null);
		assombrir.filter(monImage, imgSombre);
		monImage = imgSombre;
		p.Repaint(monImage);

	}

	public void imageBinaire(BufferedImage monImage,PanelImage p)
	{   
		BufferedImage imgBinaire = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D surfaceImg = imgBinaire.createGraphics();
		surfaceImg.drawImage(monImage, null, null);   
		monImage = imgBinaire;
		p.Repaint(monImage);

	}

	public void imageEnNiveauGris(BufferedImage monImage,PanelImage p)
	{
		BufferedImage imageGris = new BufferedImage(monImage.getWidth(), monImage.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
		Graphics2D surfaceImg = imageGris.createGraphics();
		surfaceImg.drawImage(monImage, null, null);	      
		monImage = imageGris;
		p.Repaint(monImage);
 
	}
	
}
