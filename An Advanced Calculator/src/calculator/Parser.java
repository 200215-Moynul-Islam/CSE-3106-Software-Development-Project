package calculator;
import java.util.*;
import static java.lang.Double.NaN;
import java.text.DecimalFormat;
public class Parser {
    public String evaluate(String s){
        int len=s.length();
        int run=0;
        String token="";
        ArrayList<String> tokens=new ArrayList<>();
        for(int i=0;i<len;i++){
            if(run==0){
                if(s.charAt(i)==' '){
                }
                else{
                    run=1;
                    token=token+s.charAt(i);
                }
            }
            else{
                if(s.charAt(i)==' '){
                    run=0;
                    if(token.length()!=0)
                        tokens.add(token);
                    token="";
                }
                else if(s.charAt(i)=='('){
                    tokens.add(token);
                    token="(";
                }
                else if(s.charAt(i)==')'){
                    tokens.add(token);
                    token=")";
                }
                else if(s.charAt(i)==','){
                    tokens.add(token);
                    token=",";
                }
                else if(s.charAt(i)=='.'){
                    if(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'){
                        token=token+s.charAt(i);
                    }
                    else{
                        tokens.add(token);
                        token=".";
                    }
                }
                else if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/'){
                    tokens.add(token);
                    token=""+s.charAt(i);
                }
                else if(s.charAt(i)=='j'){
                    if(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'||s.charAt(i-1)=='.'){
                        token=token+s.charAt(i);
                    }
                    else{
                        tokens.add(token);
                        token="j";
                    }
                }
                else if(s.charAt(i)>='a'){
                    if(s.charAt(i-1)>='a'&&s.charAt(i-1)!='j'){
                        token=token+s.charAt(i);
                    }
                    else{
                        tokens.add(token);
                        token=""+s.charAt(i);
                    }
                }
                else{
                    if(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'||s.charAt(i-1)=='.'){
                        token=token+s.charAt(i);
                    }
                    else{
                        tokens.add(token);
                        token=""+s.charAt(i);
                    }
                }
            }
        }
        if(run==1){
            tokens.add(token);
        }
        Stack<Double> st =new Stack<>();
        Stack<Character> op=new Stack<>();
        double x=calculate(tokens,st,op);
        DecimalFormat df = new DecimalFormat("#.##########");
        String result=df.format(x);
        return result;
    }
    public double calculate(ArrayList<String> tokens,Stack<Double> st, Stack<Character> op){
        int len=tokens.size();
        if(len==0)
            return 0;
        boolean got_paren=false;
        int paren_count=0;
        boolean vulnerable=true;
        for(int i=0;i<len;i++){
            double func_result;
            if(vulnerable&&tokens.get(i).equals("-")){
                st.push((double) 0);
                op.push('-');
            }
            else if(tokens.get(i).equals("(")){
                op.push('(');
                vulnerable=true;
            }
            else if(tokens.get(i).equals(")")){
                while (op.peek() != '(') {
                    process_op(st, op.peek());
                    op.pop();
                }
                op.pop();
                vulnerable=false;
            }
            else if (is_op(tokens.get(i))) {
                char cur_op = tokens.get(i).charAt(0);
                while (!op.empty() && priority(op.peek()) >= priority(cur_op)){
                    process_op(st, op.peek());
                    op.pop();
                    //op.push(cur_op);
                }
                op.push(cur_op);
                vulnerable=false;
            }
            else if(tokens.get(i).charAt(0)>='a'){
               ArrayList<String> func=new ArrayList<>();
               func.add(tokens.get(i));
               i++;
               while(!(got_paren==true&&paren_count==0)){
                    func.add(tokens.get(i));
                    if(tokens.get(i).equals("(")){
                        got_paren=true;
                        paren_count++;
                    }
                    if(tokens.get(i).equals(")")){
                        paren_count--;
                    }
                    i++;
               }
               functions a=new functions();
               func_result=a.func_evaluate(func);
               st.push(func_result);
               got_paren=false;
               paren_count=0;
               i--;
               vulnerable =false;
            }
            else{
                double number = 0;
                String s=tokens.get(i);
                int len2=s.length();
                int j=0;
                while(true){
                    if(j>=len2){
                        break;
                    }
                    if(s.charAt(j)=='.'){
                        break;
                    }
                    number=number*10+((int) s.charAt(j)-48);
                    j++;
                }
                double fl=0;
                j=len2-1;
                while(j>=0){
                    if(s.charAt(j)=='.')
                        break;
                    fl=(double) (fl/10)+(double)((int) s.charAt(j)-48)/10;
                    j--;
                }
                if(j!=-1)
                number+=fl;
                st.push(number);
                vulnerable=false;

            }
        }
        while (!op.empty()) {
            process_op(st, op.peek());
            op.pop();
        }
        return st.peek();
    }
    
    public void process_op(Stack<Double> st, char op){
        double r = st.peek(); st.pop();
        double l = st.peek(); st.pop();
        switch (op) {
            case '+': st.push(l + r); break;
            case '-': st.push(l - r); break;
            case '*': st.push(l * r); break;
            case '/': st.push(l / r); break;
        }
    }
    public boolean is_op(String c){
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }
    public int priority (char op) {
        if (op == '+' || op == '-')
            return 1;
        if (op == '*' || op == '/')
            return 2;
        return -1;
    }
}
class functions{
    String functions[]={"fact","comb","perm","pow","exp","ln",
        "sinr","sind","cosr","cosd","tanr","tand","cotr","cotd","secr","secd","cosecr","cosecd",
        "sinh","cosh","tanh","coth","sech","cosech",
        "log","abs","intg","diff",
        "arcsinr","arcsind","arccosr","arccosd", "arctanr","arctand", "arccotr","arccotd", "arcsecr", "arcsecd", "arccosecr","arccosecd","fibo"};
    int parameters[]={1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1};
    public double func_evaluate(ArrayList<String> tokens){
        String func=tokens.get(0);
        int func_num = 0;
        for (int i=0;i<functions.length;i++) {
            if(functions[i].equals(func)){
                func_num=i;
            }
        }
        double x=0;
        if(func_num==26){
            Calculus a=new Calculus();
            x= a.intigrate(tokens);
            return x;
        }
        if(func_num==27){
            Calculus a=new Calculus();
            x= a.differentiate(tokens);
            return x;
        }
        int parameter=parameters[func_num];
        double func_parameters[]=new double[parameter];
        int index=0;
        boolean got_paren=false;
        int paren_count=0;
        ArrayList<String> funct=new ArrayList<String> ();
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
                //paren_count=0;
                Parser b=new Parser();
                Stack<Double> st =new Stack<Double>();
                Stack<Character> op=new Stack<Character>();
                double funcvalue=b.calculate(funct,st,op);
                func_parameters[index]=funcvalue;
                index++;
                ArrayList<String> a=new ArrayList<String>();
                funct=a;
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
                //paren_count=0;
                Stack<Double> st =new Stack<Double>();
                Stack<Character> op=new Stack<Character>();
                Parser b= new Parser();
                double funcvalue=b.calculate(funct,st,op);
                func_parameters[index]=funcvalue;
                index++;
                ArrayList<String> a=new ArrayList<String>();
                funct=a;

            }
        }
        if(func_num==0){
            if(func_parameters[0]==(int) func_parameters[0]){
                x=(double) calculator.Generator.factorial((int) func_parameters[0]);
            }
            else{
                x=NaN;
            }
        }else if(func_num==1){
            if(func_parameters[0]==(int)func_parameters[0]&&func_parameters[1]==(int)func_parameters[1])
                x=(double) calculator.Generator.combination((int) func_parameters[0], (int) func_parameters[1]);
            else
                x=NaN;
        }
        else if(func_num==2){
            if(func_parameters[0]==(int)func_parameters[0]&&func_parameters[1]==(int)func_parameters[1])
                x=(double) calculator.Generator.permutation((int) func_parameters[0], (int) func_parameters[1]);
            else
                x=NaN;
        }
        else if(func_num==3){
            x=calculator.Generator.power(func_parameters[0], func_parameters[1]);
        }
        else if(func_num==4){
            x=calculator.Generator.exponent(func_parameters[0]);
        }
        else if(func_num==5){
            if(parameters[0]<=0){
                x=NaN;
            }
            else{
                x=calculator.Generator.ln(func_parameters[0]);
            }
        }
        else if(func_num==6){
            x=calculator.Generator.sinr(func_parameters[0]);
        }
        else if(func_num==7){
            x=calculator.Generator.sind(func_parameters[0]);
        }
        else if(func_num==8){
            x=calculator.Generator.cosr(func_parameters[0]);
        }
        else if(func_num==9){
            x=calculator.Generator.cosd(func_parameters[0]);
        }
        else if(func_num==10){
            x=calculator.Generator.tanr(func_parameters[0]);
        }
        else if(func_num==11){
            x=calculator.Generator.tand(func_parameters[0]);
        }
        else if(func_num==12){
            x=calculator.Generator.cotr(func_parameters[0]);
        }
        else if(func_num==13){
            x=calculator.Generator.cotd(func_parameters[0]);
        }
        else if(func_num==14){
            x=calculator.Generator.secr(func_parameters[0]);
        }
        else if(func_num==15){
            x=calculator.Generator.secd(func_parameters[0]);
        }
        else if(func_num==16){
            x=calculator.Generator.cosecr(func_parameters[0]);
        }
        else if(func_num==17){
            x=calculator.Generator.cosecd(func_parameters[0]);
        }
        else if(func_num==18){
            x=calculator.Generator.sinh(func_parameters[0]);
        }
        else if(func_num==19){
            x=calculator.Generator.cosh(func_parameters[0]);
        }
        else if(func_num==20){
            x=calculator.Generator.tanh(func_parameters[0]);
        }
        else if(func_num==21){
            x=calculator.Generator.coth(func_parameters[0]);
        }
        else if(func_num==22){
            x=calculator.Generator.sech(func_parameters[0]);
        }
        else if(func_num==23){
            x=calculator.Generator.cosech(func_parameters[0]);
        }
        else if(func_num==24){
            if(func_parameters[0]<=0||func_parameters[0]==1||func_parameters[1]<=0){
                x=NaN;
            }
            else{
                x=calculator.Generator.log(func_parameters[0], func_parameters[1]);
            }
        }
        else if(func_num==25){
            x=calculator.Generator.abs(func_parameters[0]);
        }
        else if(func_num==28){
            x=calculator.Generator.arcsinr(func_parameters[0]);
        }
        else if(func_num==29){
            x=calculator.Generator.arcsind(func_parameters[0]);
        }
        else if(func_num==30){
            x=calculator.Generator.arccosr(func_parameters[0]);
        }
        else if(func_num==31){
            x=calculator.Generator.arccosd(func_parameters[0]);
        }
        else if(func_num==32){
            x=calculator.Generator.arctanr(func_parameters[0]);
        }
        else if(func_num==33){
            x=calculator.Generator.arctand(func_parameters[0]);
        }
        else if(func_num==34){
            x=calculator.Generator.arccotr(func_parameters[0]);
        }
        else if(func_num==35){
            x=calculator.Generator.arccotd(func_parameters[0]);
        }
        else if(func_num==36){
            x=calculator.Generator.arcsecr(func_parameters[0]);
        }
        else if(func_num==37){
            x=calculator.Generator.arcsecd(func_parameters[0]);
        }
        else if(func_num==38){
            x=calculator.Generator.arccosecr(func_parameters[0]);
        }
        else if(func_num==39){
            x=calculator.Generator.arccosecd(func_parameters[0]);
        }else if(func_num==40){
            if(func_parameters[0]==(int)func_parameters[0]){
                x=(double)calculator.Generator.fibonacci((int) func_parameters[0]);
            }
            else{
                x=NaN;
            }
        }
        /*String functions[]={"fact","comb","perm","pow","exp","ln","sinr","sind","cosr","cosd","tanr","tand","cotr","cotd","secr","secd","cosecr","cosecd","sinh","cosh","tanh","coth","sech","cosech","log"};
        int parameters[]=    {   1,     2,    2,     2,    1,   1,    1,      1,     1,     1,     1,     1,     1,     1,      1,    1,     1,       1,       1,     1,     1,     1,     1,      1,      2};*/
        return x;
    }
}