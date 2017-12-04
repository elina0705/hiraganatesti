package hiraganatesti;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class Quiz extends JFrame{
	public Hiragana test;
	private JTextField vastaus;
	String vast;
	boolean check = false;
	int index = 0;
	int laskuri = 1;
	int oikein = 0;
	final int AMOUNT = 45;
	
	public Quiz() {
		setBounds(0,0,480,320);
		setLocationRelativeTo(null);
		Random randomGenerator = new Random();
		Hiragana test = new Hiragana();
		// array from which the quiz fetches the image it needs to display
		String images[] = {"", "a.png", "e.png", "u.png", "i.png", "o.png", "ka.png", "ke.png", "ku.png", "ki.png", "ko.png", "sa.png", "se.png", "su.png", "shi.png", "so.png", "ta.png", "te.png", "tsu.png", "chi.png", "to.png", "na.png", "ne.png", "nu.png", "ni.png", "no.png", "ha.png", "he.png", "fu.png", "hi.png", "ho.png", "ma.png", "me.png", "mu.png", "mi.png", "mo.png", "ra.png", "re.png", "ru.png", "ri.png", "ro.png", "ya.png", "yo.png", "yu.png", "wa.png", "wo.png"};

		getContentPane().setLayout(null);
		
		// heading 
		JLabel lblTunnistaHiraganat = new JLabel("Tunnista hiraganat");
		lblTunnistaHiraganat.setFont(new Font("Book Antiqua", Font.PLAIN, 23));
		lblTunnistaHiraganat.setBounds(10, 11, 225, 29);
		getContentPane().add(lblTunnistaHiraganat);
		
		// label to which the image of the hiragana in question will be displayed
		JLabel lblkana = new JLabel("");
		lblkana.setBounds(25, 92, 139, 125);
		getContentPane().add(lblkana);
		
		// text field for the answer
		vastaus = new JTextField();
		vastaus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		vastaus.setBounds(205, 147, 89, 44);
		getContentPane().add(vastaus);
		vastaus.setColumns(10);
		
		// label which shows up at the end of the quiz to tell the result
		JLabel lblLoppu = new JLabel("");
		lblLoppu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoppu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoppu.setBounds(130, 39, 243, 23);
		getContentPane().add(lblLoppu);
		
		// button to start the quiz
		JButton btnAloita = new JButton("Aloita");
		btnAloita.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAloita.setBounds(205, 73, 89, 23);
		getContentPane().add(btnAloita);
		btnAloita.setVisible(true);
		
		// button to submit answer
		JButton btnVastaa = new JButton("Vastaa");
		btnVastaa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVastaa.setBounds(205, 209, 89, 23);
		getContentPane().add(btnVastaa);
		btnVastaa.setEnabled(false); // button is not enabled until the quiz has started
		
		// label which displays a counter for asked questions
		JLabel lblLaskuri = new JLabel("Kysymys 0/5");
		lblLaskuri.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaskuri.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLaskuri.setBounds(205, 107, 89, 29);
		getContentPane().add(lblLaskuri);
		
		
		// label which displays a counter for correct answers 
		JLabel lblTulos = new JLabel("Oikein 0/5");
		lblTulos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTulos.setBounds(324, 147, 89, 44);
		getContentPane().add(lblTulos);
		
		// displays a background image
		JLabel tausta = new JLabel();
		tausta.setBounds(0, 0, 464, 281);
		getContentPane().add(tausta);
		tausta.setIcon(new ImageIcon("src\\images\\bg2.png"));
		
		// functionality for the beginning button
		btnAloita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblLoppu.setText("");
				btnAloita.setVisible(false); // hides the start button
				btnVastaa.setEnabled(true); // enables the answer button
				if (laskuri > 5)
					laskuri = 1;
				oikein = 0;
				lblLaskuri.setText("Kysymys " + laskuri +"/5");
				lblTulos.setText("Oikein " + oikein + "/5");
				index = randomGenerator.nextInt(AMOUNT) + 1; // randomizes a number which decides what image is shown
				test.checkIndex(index, laskuri); // sets the number into an array for later checking
				String image = images[index];
				lblkana.setIcon(new ImageIcon("src\\images\\" + image)); //shows the image
				vastaus.setText("");			
			}
		});
		
		// functionality for the answering button
		btnVastaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vast = vastaus.getText();
				check = test.check(index, vast); // checks whether the answer is correct or not
				if (check == true)
					oikein++;
				laskuri++;
				
				// creates a random number to mark which character to ask next and checks that the same question won't be asked twice
				if (laskuri <= 5){
				do {
					index = randomGenerator.nextInt(AMOUNT) + 1;	
				} while(test.checkIndex(index, laskuri) == false); // the loop continues until checkIndex returns true
				
				lblLaskuri.setText("Kysymys " + laskuri +"/5");
				
				// sets the image for the question 
				String image = images[index];
				lblkana.setIcon(new ImageIcon("src\\images\\" + image));
				vastaus.setText(""); // clears the textfield
				vastaus.requestFocusInWindow(); // sets the cursor back to the textfield
				}
				else {
					lblLoppu.setText("Vastasit oikein " + oikein + " kysymykseen!");
					btnAloita.setVisible(true); // shows the begin button again
					btnVastaa.setEnabled(false); 
					lblkana.setIcon(new ImageIcon());
					vastaus.setText("");
				}
				lblTulos.setText("Oikein " + oikein + "/5");
			}
		});		
	}	
	
	public static void main(String[] args) {
		JFrame frame = new Quiz();
		frame.setVisible(true);
	}	
}
