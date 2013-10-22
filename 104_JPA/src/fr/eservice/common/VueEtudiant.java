package fr.eservice.common;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VueEtudiant extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	private static int
		WIDTH = 300,
		HEIGHT = 450;
	
	private JTextArea infoZone;
	
	private JButton
		btn_search,
		btn_create,
		btn_prev,
		btn_next,
		btn_save;
	
	private HashMap<FIELD, JTextField> textField = new HashMap<VueEtudiant.FIELD, JTextField>();
	
	public enum FIELD {
		ID, 
		FIRSTNAME("prénom"), 
		LASTNAME("nom"), 
		AGE;
		
		String label;
		private FIELD() {
			label = name().toLowerCase();
		}
		private FIELD( String label ) {
			this.label = label;
		}
	}
	
	public VueEtudiant() {
		super(true);
		setLayout( null );
		
		int y = 5;
		int h = 0;
		int w = WIDTH - 10;
		
		infoZone = new JTextArea();
		// infoZone.setPreferredSize( new Dimension(WIDTH, 100) );
		infoZone.setEditable(false);
		infoZone.setBackground(Color.WHITE);
		infoZone.setBounds( 5, y, w, (h = 100) );
		add( infoZone );
		
		JPanel top_buttons = new JPanel( new FlowLayout() );
		btn_create = new JButton("Nouveau");
		top_buttons.add( btn_create );
		btn_search = new JButton("Rechercher ...");
		top_buttons.add( btn_search );
		y += h;
		top_buttons.setBounds( 5, y, w, (h = 30) );
		add( top_buttons );
		
		GridLayout fieldsLayout = new GridLayout(0, 2);
		JPanel pFields = new JPanel( fieldsLayout );
		
		y += h;
		h = 0;
		for( FIELD field : FIELD.values() ) {
			pFields.add( new JLabel(field.label) );
			JTextField tf = new JTextField();
			if ( field == FIELD.ID ) {
				tf.setEditable(false);
				tf.setEnabled(false);
			}
			textField.put(field, tf);
			pFields.add( tf );
			h += 30;
		}
		pFields.setBounds( 5, y, w, h );
		add(pFields);
		
		btn_save = new JButton("Sauvegarder");
		y += h;
		btn_save.setBounds( 5, y, w, (h = 30) );
		add(btn_save);
		
		JPanel cursor_panel = new JPanel( new GridLayout(0, 2) );
		btn_prev = new JButton("< Précédant");
		cursor_panel.add(btn_prev);
		btn_next = new JButton("Suivant >");
		cursor_panel.add(btn_next);
		y += h;
		cursor_panel.setBounds( 5, y, w, (h = 30) );
		add(cursor_panel);
		
		HEIGHT = y + h + 25;
		
	}
	
	public JFrame showFrame() {
		JFrame frame = new JFrame("Etudiants");
		
		frame.getContentPane().add( this );
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		return frame;
	}

	public static void main(String[] args) {
		VueEtudiant vueEtudiant = new VueEtudiant();
		JFrame frame = vueEtudiant.showFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		vueEtudiant.getInfoZone().setText(
			"TEST ZONE INFO\n" +
			"TEST ZONE INFO\n" +
			"TEST ZONE INFO\n" +
			"TEST ZONE INFO" 
		);
	}
	
	public void setField(FIELD id, String text) {
		JTextField tf = textField.get(id);
		if ( tf == null ) return;
		tf.setText(text);
	}
	
	public void focus(FIELD field) {
		JTextField tf = textField.get(field);
		if ( tf != null ) tf.requestFocus();
	}
	
	public String getField(FIELD field) {
		JTextField tf = textField.get(field);
		if ( tf == null ) return ""; 
		return tf.getText();
	}
	
	/** Getters / Setters **/
	
	public JTextArea getInfoZone() {
		return infoZone;
	}

	public JButton getSearchButton() {
		return btn_search;
	}

	public JButton getPreviousButton() {
		return btn_prev;
	}

	public JButton getNextButton() {
		return btn_next;
	}

	public JButton getSaveButton() {
		return btn_save;
	}

	public JButton getCreateButton() {
		return btn_create;
	}




	
}
