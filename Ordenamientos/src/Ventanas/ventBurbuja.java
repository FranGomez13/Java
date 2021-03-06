package Ventanas;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class ventBurbuja extends JFrame {
	public JLabel lblcomp1;
	public JLabel lblMenor;
	public JLabel lblCheck;
	public JLabel lblcomp2;
	public JSlider slider;
	public JLabel lblLinea6;
	public JLabel lblLinea5;
	public JLabel lblLinea4;
	public JLabel lblLinea3;
	public JLabel lblLinea2;
	public JLabel lblLinea1;
	
	public ventBurbuja() {
		setTitle("Ordenamiento Burbuja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(600, 350);
		setLocationRelativeTo(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(485, 278, 89, 23);
		getContentPane().add(btnVolver);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.BLUE);
		separator.setBounds(210, 0, 2, 312);
		getContentPane().add(separator);
		
		lblcomp1 = new JLabel("");
		lblcomp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomp1.setBounds(222, 11, 100, 14);
		getContentPane().add(lblcomp1);
		
		lblMenor = new JLabel("<");
		lblMenor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMenor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenor.setBounds(332, 11, 46, 14);
		getContentPane().add(lblMenor);
		
		lblcomp2 = new JLabel("");
		lblcomp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomp2.setBounds(407, 11, 100, 14);
		getContentPane().add(lblcomp2);
		
		lblCheck = new JLabel("");
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setBounds(517, 11, 46, 14);
		getContentPane().add(lblCheck);
		
		slider = new JSlider();
		slider.setValue(100);
		slider.setBounds(232, 36, 275, 23);
		getContentPane().add(slider);
		
		lblLinea1 = new JLabel("for (int i = 0; i<A.length-2; i++){");
		lblLinea1.setBounds(10, 61, 190, 14);
		getContentPane().add(lblLinea1);
		
		lblLinea2 = new JLabel("      for (int j = A.length-1; j>i; j--){");
		lblLinea2.setBounds(10, 86, 190, 14);
		getContentPane().add(lblLinea2);
		
		lblLinea3 = new JLabel("             if (A[j]<A[j-1])");
		lblLinea3.setBounds(10, 111, 190, 14);
		getContentPane().add(lblLinea3);
		
		lblLinea4 = new JLabel("                Intercambiar(j, j-1);");
		lblLinea4.setBounds(10, 136, 190, 14);
		getContentPane().add(lblLinea4);
		
		lblLinea5 = new JLabel("      \t}");
		lblLinea5.setBounds(10, 161, 190, 14);
		getContentPane().add(lblLinea5);
		
		lblLinea6 = new JLabel("}");
		lblLinea6.setBounds(10, 186, 190, 14);
		getContentPane().add(lblLinea6);
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
