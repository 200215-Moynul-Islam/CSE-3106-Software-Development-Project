package calculator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Calculator extends JFrame{
    private Container c;
    private JTextField input,output;
    private ImageIcon icon;
    private Font font;
    private JPanel numberPad,operationMode;
    private int mode=0;
    
    void GUI(){
        c=this.getContentPane();
        c.setLayout(null);
        
        //setting the icon of the calculator frame
        icon=new ImageIcon(getClass().getResource("Calculator_icon.png"));
        setIconImage(icon.getImage());
        
        //setting the size and location of the frame
        setSize(700,500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        //setting the title of the frame and activating default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        
        //creating an object of the font class
        font=new Font("Arial",Font.PLAIN,20);
        
        //creating input text field
        createInputField();
        
        //creating output text field
        createOutputField();
        
        //working on the numberpad
        createNumberPad();
        
        //working on the operationMode pad
        createOperationMode();
        
        
        
        //activating the visibility of the calculator frame
        setVisible(true);
    }
    
    void createInputField(){
        input=new JTextField();
        input.setBounds(20,5,650,40);
        input.setHorizontalAlignment(JTextField.RIGHT);
        input.setFont(font);
        c.add(input);
        
        input.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String expression=input.getText();
                String result="";
                if(mode<3){
                    Parser Evaluator=new Parser();
                    result=Evaluator.evaluate(expression);
                }else if(mode==3){
                    ParserBigNumber Evaluator=new ParserBigNumber();
                    result=Evaluator.evaluate(expression);
                }else if(mode==4){
                    ParserComplex Evaluator=new ParserComplex();
                    result=Evaluator.evaluate(expression);
                }
                else if(mode==5){
                    ParserVector Evaluator=new ParserVector();
                    result=Evaluator.evaluate(expression);
                }
                else if(mode==6){
                    result=UnitConverter.converter(expression);
                }
                else if(mode==7){
                    result=BaseConverter.converter(expression);
                }
                else if(mode==8){
                    ParserBoolean Evaluator=new ParserBoolean();
                    result=Evaluator.evaluate(expression);
                }
                else if(mode==9){
                    result=constants.evaluate(expression);
                }

                output.setText(result);
                output.setCaretPosition(0);
            }
        });
    }
    
    void createOutputField(){
        output=new JTextField();
        //output.setEditable(false);
        output.setBounds(20,45,650,40);
        output.setHorizontalAlignment(JTextField.RIGHT);
        output.setFont(font);
        c.add(output);
    }
    
    void createNumberPad(){
        numberPad=new JPanel();
        numberPad.setLayout(null);
        numberPad.setBounds(350,100,300,370);
        String[] btName={"AC","X","(",")","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
        JButton[] bt=new JButton[btName.length];
        
        //adding the buttons of the number pad to the numberPad panel
        for(int i=0;i<bt.length;i++){
            int row=i/4,col=i%4;
            bt[i]=new JButton(btName[i]);
            bt[i].setBounds((col*70)+5,row*70,70,70);
            numberPad.add(bt[i]);
        }
        c.add(numberPad);//adding the numberPad panel to the frame
        
        
        //action listener for all the buttons of the numberPad
        for(int i=0;i<bt.length;i++){
            int j=i;
            bt[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(j==18){
                        String expression=input.getText();
                        String result="";
                        if(mode<3){
                            Parser Evaluator=new Parser();
                            result=Evaluator.evaluate(expression);
                        }else if(mode==3){
                            ParserBigNumber Evaluator=new ParserBigNumber();
                            result=Evaluator.evaluate(expression);
                        }else if(mode==4){
                            ParserComplex Evaluator=new ParserComplex();
                            result=Evaluator.evaluate(expression);
                        }
                        else if(mode==5){
                            ParserVector Evaluator=new ParserVector();
                            result=Evaluator.evaluate(expression);
                        }
                        else if(mode==6){
                            result=UnitConverter.converter(expression);
                        }
                        else if(mode==7){
                            result=BaseConverter.converter(expression);
                        }
                        else if(mode==8){
                            ParserBoolean Evaluator=new ParserBoolean();
                            result=Evaluator.evaluate(expression);
                        }
                        else if(mode==9){

                        }
                        
                        output.setText(result);
                        output.setCaretPosition(0);
                    }else{
                        String currentText=input.getText();
                        if(j==0){
                            currentText="";
                        }else if(j==1){
                            currentText=currentText.substring(0,currentText.length()-1);
                        }else{
                            currentText=currentText+bt[j].getText();
                        }
                        input.setText(currentText);
                    }
                }
            });   
        }
    }
    
    void createOperationMode(){
        operationMode=new JPanel();
        operationMode.setLayout(null);
        operationMode.setBounds(50,100,300,370);
        String[] btName={"General","Differentiation","Integration","Big Number","Complex Number","Vector","Unit Converter","Base Converter","Boolean Algebra","Constants"};
        JButton[] bt=new JButton[btName.length];
        
        //adding the buttons of the operationMode pad to the operationMode panel
        for(int i=0;i<bt.length;i++){
            int row=i/2,col=i%2;
            bt[i]=new JButton(btName[i]);
            bt[i].setBounds((col*140)+5,row*70,140,70);
            if(i==0){
                bt[i].setBackground(Color.LIGHT_GRAY);
            }
            operationMode.add(bt[i]);
        }
        c.add(operationMode);//adding the numberPad panel to the frame
        
        
        //action listener for all the buttons of the operationMode pad
        for(int i=0;i<bt.length;i++){
            int j=i;
            bt[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    bt[mode].setBackground(null);
                    mode=j;
                    bt[mode].setBackground(Color.LIGHT_GRAY);
                }
            });   
        }
    }
    public static void main(String[] args){
        Calculator frame=new Calculator();
        frame.GUI();
    }
}