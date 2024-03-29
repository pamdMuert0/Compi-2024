package compi;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.text.Element;

public class TxtArea extends JTextArea {
    private JTextArea area;

    public TxtArea(JTextArea txt){
        area = txt;
        setBackground(Color.gray);
        setEditable(false);
        setText("1");
    }

    public void actualiza(){
        String num = obtenerNumero();
        setText(num);
    }

    private String obtenerNumero(){
        int pos = area.getDocument().getLength();
        Element root = area.getDocument().getDefaultRootElement();
        String text = "1" + System.getProperty("line.separator");
        for(int i = 2; i < root.getElementIndex(pos) + 2; i++) {
            text += i + System.getProperty("line.separator");
        }
        return text;
    }
}
