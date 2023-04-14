package calculator;
import java.util.*;
public class Calculus {
    public double intigrate(ArrayList<String> tokens){
        int index=0;
        boolean got_paren=false;
        int paren_count=0;
        ArrayList<String> funct=new ArrayList<> ();
        double func_parameters[]=new double[2];
        int found=0;
        for(int i=0;i<tokens.size();i++){
            if(got_paren==false&&(!tokens.get(i).equals("("))){
            }
            else if(got_paren==false&&tokens.get(i).equals("(")){
                got_paren=true;
                paren_count++;
                i++;
                while(!((tokens.get(i).equals(",")||tokens.get(i).equals(")"))&&paren_count==1)){
                    if(tokens.get(i).equals("(")){
                        paren_count++;
                    }
                    if(tokens.get(i).equals(")")){
                        paren_count--;
                    }
                    funct.add(tokens.get(i));
                    
                    i++;
                }
                if(tokens.get(i).equals(")")){
                    paren_count--;
                }
                if(paren_count==0)
                    got_paren=false;
                found++;
                if(found!=3){
                    Parser b=new Parser();
                    Stack<Double> st =new Stack<>();
                    Stack<Character> op=new Stack<>();
                    double funcvalue=b.calculate(funct,st,op);
                    func_parameters[index]=funcvalue;
                    index++;
                    ArrayList<String> a=new ArrayList<>();
                    funct=a;
                }
            }
            else if(got_paren==true){
                while(!((tokens.get(i).equals(",")||tokens.get(i).equals(")"))&&paren_count==1)){
                    if(tokens.get(i).equals("(")){
                        paren_count++;
                    }
                    if(tokens.get(i).equals(")")){
                        paren_count--;
                    }
                    funct.add(tokens.get(i));
                    
                    i++;
                }
                if(tokens.get(i).equals(")")){
                    paren_count--;
                }
                if(paren_count==0)
                    got_paren=false;
                found++;
                if(found!=3){
                    Stack<Double> st =new Stack<>();
                    Stack<Character> op=new Stack<>();
                    Parser b= new Parser();
                    double funcvalue=b.calculate(funct,st,op);
                    func_parameters[index]=funcvalue;
                    index++;
                    ArrayList<String> a=new ArrayList<>();
                    funct=a;
                }
            }
        }
        double sum=0;
        ArrayList<String> newfunct= new ArrayList<>();
        for(double i=func_parameters[0];i<func_parameters[1];i+=(0.0001)){
            int l=funct.size();
            double value=i+0.00005;
            for(int j=0;j<l;j++){
                if(funct.get(j).equals("X")){
                    String s="" +value;
                    newfunct.add(s);
                }
                else{
                    newfunct.add(funct.get(j));
                }
            }
            Stack<Double> st =new Stack<>();
            Stack<Character> op=new Stack<>();
            Parser b= new Parser();
            double funcvalue=b.calculate(newfunct,st,op);
            sum+=(0.0001*funcvalue);
            ArrayList<String> a=new ArrayList<>();
            newfunct=a;
        }
        return sum;
    }
    public double differentiate(ArrayList<String> tokens){
        int index=0;
        boolean got_paren=false;
        int paren_count=0;
        ArrayList<String> funct=new ArrayList<> ();
        double func_parameters[]=new double[1];
        int found=0;
        for(int i=0;i<tokens.size();i++){
            if(got_paren==false&&(!tokens.get(i).equals("("))){
            }
            else if(got_paren==false&&tokens.get(i).equals("(")){
                got_paren=true;
                paren_count++;
                i++;
                while(!((tokens.get(i).equals(",")||tokens.get(i).equals(")"))&&paren_count==1)){
                    if(tokens.get(i).equals("(")){
                        paren_count++;
                    }
                    if(tokens.get(i).equals(")")){
                        paren_count--;
                    }
                    funct.add(tokens.get(i));
                    
                    i++;
                }
                if(tokens.get(i).equals(")")){
                    paren_count--;
                }
                if(paren_count==0)
                    got_paren=false;
                found++;
                if(found!=2){
                    Parser b=new Parser();
                    Stack<Double> st =new Stack<>();
                    Stack<Character> op=new Stack<>();
                    double funcvalue=b.calculate(funct,st,op);
                    func_parameters[index]=funcvalue;
                    index++;
                    ArrayList<String> a=new ArrayList<>();
                    funct=a;
                }
            }
            else if(got_paren==true){
                while(!((tokens.get(i).equals(",")||tokens.get(i).equals(")"))&&paren_count==1)){
                    if(tokens.get(i).equals("(")){
                        paren_count++;
                    }
                    if(tokens.get(i).equals(")")){
                        paren_count--;
                    }
                    funct.add(tokens.get(i));
                    
                    i++;
                }
                if(tokens.get(i).equals(")")){
                    paren_count--;
                }
                if(paren_count==0)
                    got_paren=false;
                found++;
                if(found!=2){
                    Stack<Double> st =new Stack<>();
                    Stack<Character> op=new Stack<>();
                    Parser b= new Parser();
                    double funcvalue=b.calculate(funct,st,op);
                    func_parameters[index]=funcvalue;
                    index++;
                    ArrayList<String> a=new ArrayList<>();
                    funct=a;
                }
            }
        }
        ArrayList<String> newfunct= new ArrayList<>();
        int l=funct.size();
        double value=func_parameters[0]+0.00005;
        for(int j=0;j<l;j++){
            if(funct.get(j).equals("X")){
                String s="" +value;
                newfunct.add(s);
            }
            else{
                newfunct.add(funct.get(j));
            }
        }
        Stack<Double> st =new Stack<>();
        Stack<Character> op=new Stack<>();
        Parser b= new Parser();
        double funcvalue1=b.calculate(newfunct,st,op);
        ArrayList<String> a= new ArrayList<>();
        newfunct=a;
        value=func_parameters[0]-0.00005;
        for(int j=0;j<l;j++){
            if(funct.get(j).equals("X")){
                String s="" +value;
                newfunct.add(s);
            }
            else{
                newfunct.add(funct.get(j));
            }
        }
        Stack<Double> st2 =new Stack<>();
        Stack<Character> op2=new Stack<>();
        Parser c= new Parser();
        double funcvalue2=c.calculate(newfunct,st2,op2);
        double ans= (funcvalue1-funcvalue2)/0.0001;
        return ans;
    }
}
