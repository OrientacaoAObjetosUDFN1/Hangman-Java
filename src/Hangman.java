package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Hangman extends JFrame {

    private int errei = 0;
    private String [] palavras = {
        "ENFORCADO", "REPROVADO", "REARRUMADO", "FIGUEIRA",
        "EMPOSSADO", "MUDANÃ‡A", "REFORMA", "GIROSCÃ“PIO"
    };
    private String palavraSorteada;
    private String palavraEscondida;
    private JLabel lbEscondida;

    private JTextField tfLetra = new JTextField("",3);
    private JButton btOK = new JButton("OK");

    public Hangman(String [] palavras) {
        this.setPalavras(palavras);
        this.setPalavras(palavras);
        this.setTitle("Enforcado");
        this.setSize(600,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        palavraSorteada = palavras[(int)(Math.random()*palavras.length)];
        this.esconderPalavra();
        JPanel pn = new JPanel();
        pn.setLayout(new GridLayout(1,2));
        lbEscondida = new JLabel(palavraEscondida);
        pn.add(new JLabel());
        pn.add(lbEscondida);

        JPanel pn2 = new JPanel();
        pn2.add(tfLetra);
        pn2.add(btOK);

        btOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char letra = tfLetra.getText().toUpperCase().charAt(0);
                boolean acertou = verificarLetra(letra);
                if(acertou) {
                    substituirTraco(letra);
                    lbEscondida.setText(palavraEscondida);
                    if(!palavraEscondida.contains("_")){
                        JOptionPane.showMessageDialog(Hangman.this, "Parabens");
                    }
                }else {
                    errei ++;
                    repaint();
                    if (errei >=6) {
                        JOptionPane.showMessageDialog(Hangman.this, "Voce perdeu");
                    }
                }
                tfLetra.setText("");
                tfLetra.requestFocus();
            }
        });

        this.add(pn, BorderLayout.CENTER);
        this.add(pn2, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void esconderPalavra() {
        palavraEscondida = "";
        for (int i = 0; i < palavraSorteada.length(); i += 1) {
            palavraEscondida += "_ ";
        }
    }

    private boolean verificarLetra(char c) {
        for (int i = 0; i < palavraSorteada.length(); i += 1) {
            if (c == palavraSorteada.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private void substituirTraco(char c) {
        String mnt = "";
        for (int i = 0; i < palavraSorteada.length(); i += 1) {
            if (c == palavraSorteada.charAt(i)) {
                mnt += "" + c + " ";
            } else {
                int x = i * 2;
                mnt += "" + palavraEscondida.charAt(x) + " ";
            }    
        }
        palavraEscondida = mnt;                
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(110,100,110,300);
        g.drawLine(110,100,210,100);
        g.drawLine(210,100,210,120);
        if (errei > 0) {
            // CabeÃ§a
            g.drawOval(200,120,20,30);
        }
        if (errei > 1) {
            // Tronco
            g.drawLine(210,150,210,200);
        }
        if (errei > 2) {
            // Pernas
            g.drawLine(210,200,190,240);
        }
        if (errei > 3) {
            g.drawLine(210,200,230,240);
        }
        if (errei > 4) {
            // BraÃ§os
            g.drawLine(210,160,180,180);
        }
        if (errei > 5) {
            g.drawLine(210,160,240,180);
            g.setColor(new Color(255,0,0));
            g.fillOval(200,120,20,30);
        }
    }
    public String[] getPalavras() {
        return palavras;
    }

    public void setPalavras(String[] palavras) {
        this.palavras = palavras;
    }

    public static String[] pegarPalavra() {
        Scanner in = new Scanner(System.in);

        System.out.println("Digite a palavra");
        String [] palavra = new String[2];

        palavra[1] = in.nextLine();

        in.close();

        return palavra;
    }

    public static void main(String [] args) {
        new Hangman(pegarPalavra());
    }
}

