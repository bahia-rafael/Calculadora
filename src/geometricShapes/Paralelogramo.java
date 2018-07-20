package geometricShapes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.SaveData;
import geometricShapes.Figuras;
import interfaceG.InterfaceGrafica;
/**
 * Classe utilizada para realizar o c�lculo de area do Paralelogramo
 * ,e apresentar os seus resultados na interface,e os salva em um arquivo txt.
 * @author R-CALC
 * @since 1.0	
 * @version 1.0	 
 */
public class Paralelogramo extends Figuras {
	private double altura;
	private double base;
	private JTextField Tbase;
	private JTextField Taltura;

	public void setArea(double altura, double base) {
		// Metodo para receber os valores necessarios para realizar os calculos
		this.altura = altura;
		this.base = base;
		calcArea();

	}

	protected boolean checkinformation(String opcao, String opcao2) {
		if (!opcao.isEmpty() && (!opcao2.isEmpty())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	protected void calcArea() {
		// Calculo da Area
		area = base * altura;
	}

	@Override
	public JPanel montaRequisitos() {
		painel2 = new JPanel();
		painel2.setLayout(null);
		painel2.setVisible(true);
		painel2.setBounds(0, 157, 994, 604);

		figura = "Paralelogramo";

		bCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkinformation((Taltura.getText()), (Tbase.getText()))) {
					try {
						setArea(Integer.parseInt(Taltura.getText()), Integer.parseInt(Tbase.getText()));
						dA = df.format(getArea());
						rArea.setText("�rea:      " + dA+" cm�");
						rArea.setBounds(550, 300,250, 30);
						rArea.setFont(fonte);
						painel2.add(rArea);
						painel2.repaint();
						salvarDates();

					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Por favor, preencha corretamente todos os campos!",
								"Aviso!", JOptionPane.WARNING_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, preencha corretamente todos os campos!", "Aviso!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		bCalcular.setFont(fonte);// O JButton recebendo a fonte
		bCalcular.setBounds(350, 307, 130, 40); // Informando a localizacao e o tamanho do JButton
		bCalcular.setVisible(true);// Informando que o JButton ficara vis�vel
		painel2.add(bCalcular);

		JLabel Lbase = new JLabel("Base:");
		Lbase.setFont(fonte);
		Lbase.setBounds(114, 45, 104, 24);
		Lbase.setVisible(true);
		painel2.add(Lbase);

		JLabel Laltura = new JLabel("Altura:");
		Laltura.setFont(fonte);
		Laltura.setBounds(114, 110, 104, 24);
		Laltura.setVisible(true);
		painel2.add(Laltura);

		Tbase = new JTextField();
		Tbase.setFont(fonte);
		Tbase.setBounds(213, 43, 145, 30);
		Tbase.setVisible(true);
		painel2.add(Tbase);

		Taltura = new JTextField();
		Taltura.setFont(fonte);
		Taltura.setBounds(213, 108, 145, 30);
		Taltura.setVisible(true);
		painel2.add(Taltura);

		image = new JLabel();
		image.setIcon(new ImageIcon(InterfaceGrafica.class.getResource("/figures/" + figura + ".jpg")));
		image.setBounds(400, 0, 360, 280);
		painel2.add(image);

		return painel2;

	}

	@Override
	protected void salvarDates() throws IOException {
		new SaveData(figura, dA);

	}

}