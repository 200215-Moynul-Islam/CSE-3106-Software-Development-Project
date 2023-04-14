
package calculator;

import java.util.*;
public class ParserComplex {
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
        Stack<ComplexNumber> st= new Stack<ComplexNumber>();
        Stack<Character> op=new Stack<Character>();
        ComplexNumber x=calculate(tokens,st,op);
        String Result="";
        if(x.real!=0)
            Result=Result+x.real;
        if(x.imazinary>0){
            Result=Result+"+"+x.imazinary+"j";
        }
        else if(x.imazinary<0){
            Result=Result+x.imazinary+"j";
        }
        if(Result.length()==0){
            Result="0";
        }
        return Result;
    }
    public ComplexNumber calculate(ArrayList<String> tokens,Stack<ComplexNumber> st, Stack<Character> op){
        int len=tokens.size();
        if(len==0){
            ComplexNumber a=new ComplexNumber();
            a.real=0;
            a.imazinary=0;
            return a;
        }
        boolean got_paren=false;
        int paren_count=0;
        boolean vulnerable=true;
        for(int i=0;i<len;i++){
            ComplexNumber func_result;
            if(vulnerable&&tokens.get(i).equals("-")){
                ComplexNumber zero=new ComplexNumber();
                st.push(zero);
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
            else if(tokens.get(i).charAt(0)=='j'){
                ComplexNumber Number=new ComplexNumber();
                Number.imazinary=1;
                Number.real=0;
                st.push(Number);
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
               functionsComplex a=new functionsComplex();
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
                int len3=len2;
                if(s.charAt(len2-1)=='j'){
                    len2--;
                }
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
                ComplexNumber Number=new ComplexNumber();
                if(s.charAt(len3-1)=='j'){
                    Number.imazinary=number;
                    Number.real=0;
                }
                else{
                    Number.imazinary=0;
                    Number.real=number;
                }
                st.push(Number);
                vulnerable=false;
            }
        }
        while (!op.empty()) {
            process_op(st, op.peek());
            op.pop();
        }
        return st.peek();
    }
    
    public void process_op(Stack<ComplexNumber> st, char op){
        ComplexNumber r = st.peek(); st.pop();
        ComplexNumber l = st.peek(); st.pop();
        switch (op) {
            case '+': st.push(ComplexNumber.add(r,l)); break;
            case '-': st.push(ComplexNumber.sub(l,r)); break;
            case '*': st.push(ComplexNumber.multiply(l,r)); break;
            case '/': st.push(ComplexNumber.divide(l,r)); break;
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
class functionsComplex{
    String functions[]={"mod","argr","argd"};
    int parameters[]={1,1,1};
    public ComplexNumber func_evaluate(ArrayList<String> tokens){
        String func=tokens.get(0);
        int func_num = 0;
        for (int i=0;i<functions.length;i++) {
            if(functions[i].equals(func)){
                func_num=i;
            }
        }
        ComplexNumber x=new ComplexNumber();
        int parameter=parameters[func_num];
        ComplexNumber func_parameters[]=new ComplexNumber[parameter];
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
                ParserComplex b=new ParserComplex();
                Stack<ComplexNumber> st =new Stack<ComplexNumber>();
                Stack<Character> op=new Stack<Character>();
                ComplexNumber funcvalue=b.calculate(funct,st,op);
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
                Stack<ComplexNumber> st =new Stack<ComplexNumber>();
                Stack<Character> op=new Stack<Character>();
                ParserComplex b= new ParserComplex();
                ComplexNumber funcvalue=b.calculate(funct,st,op);
                func_parameters[index]=funcvalue;
                index++;
                ArrayList<String> a=new ArrayList<String>();
                funct=a;

            }
        }
        if(func_num==0){
            x=calculator.ComplexNumber.mod(func_parameters[0]);
        }
        if(func_num==1){
            x=calculator.ComplexNumber.argr(func_parameters[0]);
        }
        if(func_num==2){
            x=calculator.ComplexNumber.argd(func_parameters[0]);
        }
        return x;
    }
}