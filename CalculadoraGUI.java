import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraGUI {
    private JFrame frame;
    private JTextField campoDisplay;
    private JButton[] botoesNumeros;
    private JButton[] botoesOperadores;
    private JButton botaoIgual;
    private JButton botaoClear;

    private double numero1;
    private double numero2;
    private char operador;

    public CalculadoraGUI() {
        criarInterface();
    }

    private void criarInterface() {
        frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        campoDisplay = new JTextField(20);
        campoDisplay.setEditable(false);
        campoDisplay.setHorizontalAlignment(JTextField.RIGHT);

        JPanel painelNumeros = new JPanel(new GridLayout(4, 3));
        botoesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botoesNumeros[i] = new JButton(String.valueOf(i));
            botoesNumeros[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton botao = (JButton) e.getSource();
                    campoDisplay.setText(campoDisplay.getText() + botao.getText());
                }
            });
            painelNumeros.add(botoesNumeros[i]);
        }

        JPanel painelOperadores = new JPanel(new GridLayout(5, 1));
        botoesOperadores = new JButton[4];
        String[] operadores = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            botoesOperadores[i] = new JButton(operadores[i]);
            botoesOperadores[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton botao = (JButton) e.getSource();
                    operador = botao.getText().charAt(0);
                    numero1 = Double.parseDouble(campoDisplay.getText());
                    campoDisplay.setText("");
                }
            });
            painelOperadores.add(botoesOperadores[i]);
        }

        botaoIgual = new JButton("=");
        botaoIgual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numero2 = Double.parseDouble(campoDisplay.getText());
                double resultado = 0;
                switch (operador) {
                    case '+':
                        resultado = numero1 + numero2;
                        break;
                    case '-':
                        resultado = numero1 - numero2;
                        break;
                    case '*':
                        resultado = numero1 * numero2;
                        break;
                    case '/':
                        if (numero2!= 0) {
                            resultado = numero1 / numero2;
                        } else {
                            JOptionPane.showMessageDialog(frame, "Erro: divisão por zero!");
                            return;
                        }
                        break;
                }
                campoDisplay.setText(String.valueOf(resultado));
            }
        });
        painelOperadores.add(botaoIgual);

        botaoClear = new JButton("C");
        botaoClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campoDisplay.setText("");
            }
        });
        painelOperadores.add(botaoClear);

        frame.add(campoDisplay, BorderLayout.NORTH);
        frame.add(painelNumeros, BorderLayout.CENTER);
        frame.add(painelOperadores, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI();
            }
        });
    }
}