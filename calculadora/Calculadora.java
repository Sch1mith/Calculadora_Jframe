package calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display;
    private StringBuilder input;
    private String operador;
    private double resultado;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        input = new StringBuilder();
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.PLAIN, 30));
            botao.addActionListener(this);
            panel.add(botao);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.charAt(0) == 'C') {
            input.setLength(0);
            display.setText("");
            resultado = 0;
            operador = null;
        } else if (comando.charAt(0) == '=') {
            calcular();
            display.setText(String.valueOf(resultado));
            input.setLength(0);
        } else if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            if (input.length() > 0) {
                resultado = Double.parseDouble(input.toString());
                operador = comando;
                input.setLength(0);
            }
        } else {
            input.append(comando);
            display.setText(input.toString());
        }
    }

    private void calcular() {
        if (input.length() > 0) {
            double num2 = Double.parseDouble(input.toString());

            switch (operador) {
                case "+":
                    resultado += num2;
                    break;
                case "-":
                    resultado -= num2;
                    break;
                case "*":
                    resultado *= num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado /= num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro: Divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}
