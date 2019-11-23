package Vue;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

public class Welcome extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel = null;
	/**
	 * This is the default constructor
	 */
	public Welcome() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(-3, 3, 751, 484));
		jLabel.setText("Bienvenue");
		jLabel.setFont(new Font("Serif", Font.PLAIN,120)); 
        jLabel.setBackground(new Color(0, 2, 0));
		this.setSize(494, 249);
		this.setLayout(null);
		this.add(jLabel, null);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
