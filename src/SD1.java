// Java Program to Implement the RSA Algorithm
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;

public class SD1 extends JFrame{
    JPanel sPanel;
    private JLabel deKriptimiTxt;
    private JLabel kriptimiTxt;
    private JTextField kriptimiTextField;
    private JTextField deKriptimiTextField;
    private JButton kriptoButton;
    private JButton deKriptoButton;
    private JButton resetButton;
    private JTextField pKriptoField;
    private JTextField qKriptoField;
    private JTextField pDeKriptoField;
    private JTextField qDeKriptoField;
    private JLabel mesazhiKriptuarTxt;
    private JLabel mesazhiDekriptuarTxt;
    private JTextField eDeKriptoField;
    private JLabel eKriptoTxtField;
    private JLabel dDeKriptoTxtField;
    int p1,p2 = 0;
    int q1,q2 = 0;
    int z1,z2 = 0; //ϕ(n) "funksioni i Eulerit"
    int d2 = 0;
    int e1,e2;
    int i;

    String[] alfabeti;
public SD1() {
    alfabeti = new String[26];
    alfabeti[0] = "A";
    alfabeti[1] = "B";
    alfabeti[2] = "C";
    alfabeti[3] = "D";
    alfabeti[4] = "E";
    alfabeti[5] = "F";
    alfabeti[6] = "G";
    alfabeti[7] = "H";
    alfabeti[8] = "I";
    alfabeti[9] = "J";
    alfabeti[10] = "K";
    alfabeti[11] = "L";
    alfabeti[12] = "M";
    alfabeti[13] = "N";
    alfabeti[14] = "O";
    alfabeti[15] = "P";
    alfabeti[16] = "Q";
    alfabeti[17] = "R";
    alfabeti[18] = "S";
    alfabeti[19] = "T";
    alfabeti[20] = "U";
    alfabeti[21] = "V";
    alfabeti[22] = "W";
    alfabeti[23] = "X";
    alfabeti[24] = "Y";
    alfabeti[25] = "Z";

    kriptoButton.addActionListener(new ActionListener() { //Button per me Kriptu
        @Override
        public void actionPerformed(ActionEvent e) {
            if(kriptimiTextField.getText() == null || kriptimiTextField.getText().split("").equals("") ||
            pKriptoField.getText().equals("") || qKriptoField.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Ju lutem plotesoni te gjitha fushat ne menyre qe te kriptoni!");
            }else{
                updateKriptoValues();
                ArrayList<String> mesazhiKriptuar = kripto();
                mesazhiKriptuarTxt.setText(String.valueOf(mesazhiKriptuar));
                System.out.println("Mesazhi i kriptuar: " + mesazhiKriptuar);
            }
        }
    });

    resetButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetValues();
        }
    });

    deKriptoButton.addActionListener(new ActionListener() { // Buton per me De-Kriptu
        @Override
        public void actionPerformed(ActionEvent e) {
            if(deKriptimiTextField.getText() == null || deKriptimiTextField.getText().split("").equals("") ||
                    pDeKriptoField.getText().equals("") || qDeKriptoField.getText().equals("") || eDeKriptoField.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Ju lutem plotesoni te gjitha fushat ne menyre qe te de-kriptoni!");
            }else {
                updateDeKriptoValues();
                ArrayList<String> mesazhiDeKriptuar = deKripto();
                mesazhiDekriptuarTxt.setText(String.valueOf(mesazhiDeKriptuar));
                System.out.println("Mesazhi i de-kriptuar: " + mesazhiDeKriptuar);
            }
        }
    });
}
    public int gcd(int e, int z)
    {
        if (e == 0) {
            return z;
        }else {
            return gcd(z % e, e);
        }
    }

    public int findE1(int p, int q){
        for (e1 = 2; e1 < z1; e1++) {
            if (gcd(e1, z1) == 1) {
                break;
            }
        }
        return e1;
    }
    public void setE1(int e) {
        this.e1 = e;
    }


    public void updateKriptoValues(){
        p1 = Integer.parseInt(pKriptoField.getText());
        q1 = Integer.parseInt(qKriptoField.getText());
        z1 = (p1-1) * (q1-1);
        setE1(findE1(p1,q1));
        //per testim:
        //System.out.println(e);
        eKriptoTxtField.setText("e = "+e1);
    }

    //Metoda per te kriptuar mesazhin
    public ArrayList<String> kripto(){ //parameter i japim array me karaktere t'mesazhit qe duhet mu kriptu
        String mesazhiPerTuKriptuar = kriptimiTextField.getText();
        String[] mesazhiSplit = mesazhiPerTuKriptuar.split("");
        ArrayList<String> mesazhiList = new ArrayList<String>(Arrays.asList(mesazhiSplit)); //qikjo arraylist e man mesazhin qe ka mu kriptu, shkronje per shkronje
        ArrayList<Integer> tempArr1 = new ArrayList<Integer>(); //array qe permban mesazhin por ne numra
        for(String s : mesazhiList){
            for(int i=0; i<alfabeti.length; i++){
                if(s.equals(alfabeti[i])){
                    tempArr1.add(i);
                }
            }
        }
        ArrayList<Integer> tempArr2 = new ArrayList<Integer>(); //array qe permban mbetjet pas modulimit te fuqise se numrave ne tempArr1
        for(int nr : tempArr1){
            Double nrNeFuqi = Math.pow((double)nr, (double)e1);
            int nrNeFuqiInt = nrNeFuqi.intValue();
            int mbetja = nrNeFuqiInt % (p1*q1);
            tempArr2.add(mbetja);
        }
        ArrayList<String> tempArr3 = new ArrayList<String>(); //array qe permban mesazhin e kriptuar, shkronje per shkronje
        for(int nr: tempArr2){
            tempArr3.add(alfabeti[nr]);
        }
        return tempArr3;
    }

    public void resetValues(){
        p1 = 0;
        p2 = 0;

        q1 = 0;
        q2 = 0;

        e1 = 0;
        e2 = 0;

        z1 = 0;
        z2 = 0;

        d2 = 0;

        mesazhiKriptuarTxt.setText("");
        mesazhiDekriptuarTxt.setText("");

        kriptimiTextField.setText("");
        deKriptimiTextField.setText("");

        eKriptoTxtField.setText("");
        eDeKriptoField.setText("");

        pKriptoField.setText("");
        pDeKriptoField.setText("");

        qKriptoField.setText("");
        qDeKriptoField.setText("");

        dDeKriptoTxtField.setText("");
    }

    //metoda per te gjetur d2
    public int findD2(int p, int q, int e2){
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z2);

            // d is for private key exponent
            if (x % e2 == 0) {
                d2 = x / e2;
                break;
            }
        }
        return d2;
    }

    public void updateDeKriptoValues(){
        p2 = Integer.parseInt(pDeKriptoField.getText());
        q2 = Integer.parseInt(qDeKriptoField.getText());
        z2 = (p2-1) * (q2-1);
        e2 = Integer.parseInt(eDeKriptoField.getText());
        setD2(findD2(p2,q2,e2));
        dDeKriptoTxtField.setText("d = "+d2);
    }

    public void setD2(int d){
        this.d2 = d;
    }
    //metoda per te de-kriptuar mesazhin e kriptuar
    public ArrayList<String> deKripto(){
        updateDeKriptoValues();
        String mesazhiPerTuDeKriptuar = deKriptimiTextField.getText();
        String[] mesazhiSplit = mesazhiPerTuDeKriptuar.split(" ");
        ArrayList<String> mesazhiList = new ArrayList<String>(Arrays.asList(mesazhiSplit)); //qikjo arraylist e man mesazhin qe ka mu kriptu(mesazhin qe u shkru pi user), shkronje per shkronje
        //System.out.println(mesazhiList);

        ArrayList<Integer> tempArr1 = getIntegerArray(mesazhiList); //array qe permban mesazhin ne numra
        //System.out.println(tempArr1);

        ArrayList<Integer> tempArr2 = new ArrayList<Integer>(); //array qe permban mbetjet pas modulimit te fuqise se numrave ne tempArr1
        for(int numri : tempArr1){
            /*
            Double nrNeFuqi = Math.pow((double)numri, findD2(p2,q2,e2));
            int nrNeFuqiInt = nrNeFuqi.intValue();
            int mbetja = nrNeFuqiInt % (p2*q2);
            tempArr2.add(mbetja);
            */

            Double nrNeFuqi = Math.pow((double)numri, (double)findD2(p2,q2,e2));
            //System.out.println("Masi u ngrit en fuqi vlera ne double: " + nrNeFuqi);
            int nrNeFuqiInt = nrNeFuqi.intValue();
            //System.out.println("Masi u kthy ne int vlera ma nelt: " + nrNeFuqiInt);
            double mbetja = nrNeFuqi % (q2*p2);
            //System.out.println("Mbetja apo perfundimtarja: " + mbetja);
            tempArr2.add((int) mbetja);
        }
        //System.out.println(tempArr2);

        ArrayList<String> tempArr3 = new ArrayList<String>(); //array qe permban mesazhin e dekriptuar shkronje per shkronje
        for(int mb : tempArr2){
            tempArr3.add(alfabeti[mb]);
        }
        return tempArr3;
    }

    public ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);
                System.out.println("NumberFormat Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }
}
