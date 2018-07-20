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
 * Classe utilizada para realizar os c�lculos de volume e area da TampaEsferica
 * ,e apresentar os seus resultados na interface,e os salva em um arquivo txt.
 * @author R-CALC
 * @since 1.0	
 * @version 1.0	 
 */
public class TampaEsferica extends Figuras {
	private double raio;
	private double altura;
	private JTextField Traio;
	private JTextField Taltura;

	public void setArea(double raio, double altura) {
		// Metodo para o calculo da area da tampa esferica, recebe como parametro raio
		// da esfera e altura da tampa
		this.raio = raio;
		this.altura = altura;
		calcArea();

	}

	public void setVolume(double raio, double altura) {
		// Metodo para o calculo do volume da tampa esferica, recebe como parametro raio
		// da esfera e altura da tampa
		this.raio = raio;
		this.altura = altura;
		calcVolume();
	}

	private void calcVolume() {
		// Calculo Volume
		volume = 0.33 * 3.14 *Math.pow(altura, 2) * ((3 * raio) - altura);
	}

	@Override
	protected void calcArea() {
		// Calculo Area
		area = 2 * 3.14 * raio * altura;
	}

	protected boolean checkinformation(String opcao, String opcao2) {
		if (!opcao.isEmpty() && (!opcao2.isEmpty())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public JPanel montaRequisitos() {
		painel2 = new JPanel();
		painel2.setLayout(null);
		painel2.setVisible(true);
		painel2.setBounds(0, 157, 994, 604);

		figura = "Tampa Esf�rica";

		bCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkinformation((Traio.getText()), (Taltura.getText()))) {
					try {
						setArea(Integer.parseInt(Traio.getText()), Integer.parseInt(Taltura.getText()));
						setVolume(Integer.parseInt(Traio.getText()), Integer.parseInt(Taltura.getText()));
						dV = df.format(getVolume());
						dA = df.format(getArea());
						rArea.setText("�rea:      " + dA+" cm�");
						rVolume.setText(" Volume: " + dV+" cm�");
						rArea.setBounds(550, 300,250, 30);
						rVolume.setBounds(545, 320,250, 30);
						rVolume.setFont(fonte);
						rArea.setFont(fonte);
						painel2.add(rVolume);
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

		JLabel Lraio = new JLabel("Raio:");
		Lraio.setFont(fonte);
		Lraio.setBounds(114, 45, 104, 24);
		Lraio.setVisible(true);
		painel2.setLayout(null);
		painel2.add(Lraio);

		JLabel Laltura = new JLabel("Altura:");
		Laltura.setFont(fonte);
		Laltura.setBounds(114, 110, 104, 24);
		Laltura.setVisible(true);
		painel2.add(Laltura);

		Traio = new JTextField();
		Traio.setFont(fonte);
		Traio.setBounds(213, 43, 145, 30);
		Traio.setVisible(true);
		painel2.add(Traio);

		Taltura = new JTextField();
		Taltura.setFont(fonte);
		Taltura.setBounds(213, 108, 145, 30);
		Taltura.setVisible(true);
		painel2.add(Taltura);

		image = new JLabel();
		image.setIcon(new ImageIcon(InterfaceGrafica.class.getResource("/figures/" + figura + ".jpg")));
		image.setBounds(450, 0, 356, 280);
		painel2.add(image);

		return painel2;

	}

	@Override
	protected void salvarDates() throws IOException {
		new SaveData(figura, dA, dV);

	}
}