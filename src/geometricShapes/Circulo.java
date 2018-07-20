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
 * Classe utilizada para realizar os c�lculos de comprimento e area do Circulo
 * ,e apresentar os seus resultados na interface,e os salva em um arquivo txt.
 * @author R-CALC
 * @since 1.0	
 * @version 1.0
 */
public class Circulo extends Figuras {
	private double comprimento;
	private double raio;
	private JTextField Traio;

	public void setArea(double raio) {
		// Metodo para receber os valores necessarios para realizar os calculos
		this.raio = raio;
		calcArea();

	}

	public double getComprimento() {
		// Metodo para retornar o valor do comprimento calculada
		return comprimento;
	}

	public void setComprimento(double raio) {
		// Metodo para receber os valores necessarios para realizar os calculos
		this.raio = raio;

		calcComp();

	}

	@Override
	protected void calcArea() {
		// Calculo da Area
		area = 3.14 * Math.pow(raio, 2);
	}

	private void calcComp() {
		// Calculo do Comprimento
		this.comprimento = 2 * 3.14 * raio;
	}

	protected boolean checkinformation(String opcao) {
		if (!opcao.isEmpty()) {
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
		figura = "C�rculo";

		bCalcular.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (checkinformation((Traio.getText()))) {
					try {
						setArea(Integer.parseInt(Traio.getText()));
						setComprimento(Integer.parseInt(Traio.getText()));
						dA = df.format(getArea());
						dV = df.format(getComprimento());
						rArea.setText("�rea:      " + dA+" cm�");
						rVolume.setText("Comp.:    " + dV+ " cm"); // Comprimento e n�o volume
						rVolume.setBounds(545, 320,250, 30);
						rArea.setBounds(550, 300,250, 30);
						rArea.setFont(fonte);
						rVolume.setFont(fonte);
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
		painel2.add(Lraio);

		Traio = new JTextField();
		Traio.setFont(fonte);
		Traio.setBounds(213, 43, 145, 30);
		Traio.setVisible(true);
		painel2.add(Traio);

		image = new JLabel();
		image.setIcon(new ImageIcon(InterfaceGrafica.class.getResource("/figures/" + figura + ".jpg")));
		image.setBounds(480, 0, 356, 280);
		painel2.add(image);

		return painel2;
	}

	@Override
	protected void salvarDates() throws IOException {
		new SaveData(figura, dA, dV + "*");

	}

}
