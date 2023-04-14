package calculator;

import java.util.ArrayList;
import java.util.Stack;

public class ParserBoolean {
    public int priority (String op) {
        if (op.equals("+") || op.equals("-"))
            return 1;
        if (op.equals("*") || op.equals("/"))
            return 2;
        if(op.equals(">") || op.equals("<")||op.equals("==") || op.equals(">=")||op.equals("<=") || op.equals("^")||op.equals("!^"))
            return -1;
        return 0;
    }
    public void process_op(Stack<Boolean> st, String op){
        if(op.equals("!")){
            Boolean r=st.peek(); st.pop();
            st.push(Boolean.not(r));
            return;
        }
        Boolean r = st.peek(); st.pop();
        Boolean l = st.peek(); st.pop();
        switch (op) {
            case "+": st.push(Boolean.add(r,l)); break;
            case "-": st.push(Boolean.sub(l,r)); break;
            case "*": st.push(Boolean.mul(l,r)); break;
            case "/": st.push(Boolean.div(l,r)); break;
            case ">": st.push(Boolean.greater(l, r)); break;
            case "<": st.push(Boolean.smaller(l, r)); break;
            case "==": st.push(Boolean.equal(l, r)); break;
            case ">=": st.push(Boolean.greaterOrEqual(l, r)); break;
            case "<=": st.push(Boolean.smallerOrEqual(l, r)); break;
            case "^": st.push(Boolean.xor(l, r)); break;
            case "!^": st.push(Boolean.xnor(l, r)); break;
            case "!": st.push(l);
                st.push(Boolean.not(r)); break;
            case "||": st.push(Boolean.or(r, l)); break;
            case "&&": st.push(Boolean.and(r, l)); break;
        }
    }
    public boolean is_op(String c){
        if(c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")||c.equals(">")||c.equals("<")||c.equals("==")||c.equals(">=")||c.equals("<=")||c.equals("^")||c.equals("!^")||c.equals("!")||c.equals("||")||c.equals("&&"))
            return true;
        else
            return false;
    }
    
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
                else if(s.charAt(i)=='.'){
                    if(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'){
                        token=token+s.charAt(i);
                    }
                    else{
                        tokens.add(token);
                        token=".";
                    }
                }
                else if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                    if(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'){
                        token=token+s.charAt(i);
                    }
                    else{
                        tokens.add(token);
                        token=""+s.charAt(i);
                    }
                }
                else if(s.charAt(i)>='a'&&s.charAt(i)<='z'){
                    if(s.charAt(i-1)>='a'&&s.charAt(i-1)<='z'){
                        token=token+s.charAt(i);
                    }
                    else{
                        tokens.add(token);
                        token=""+s.charAt(i);
                    }
                }
                else{
                    if(s.charAt(i-1)!=' '&&s.charAt(i-1)!='('&&s.charAt(i-1)!=')'&&s.charAt(i-1)!='.'&&(!(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'))&&(!(s.charAt(i-1)>='a'&&s.charAt(i-1)<='z')))
                    {
                        //tokens.add(token);
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
        Stack<Boolean> st= new Stack<>();
        Stack<String> op=new Stack<>();
        Boolean x=calculate(tokens,st,op);
        String Result="";
        if(x==null)
            return "Invalid";
        if(x.value==false){
            if(x.bvalue){
                return "true";
            }
            else{
                return "false";
            }
        }
        else{
            Result=Result+x.value;
        }
        return Result;
    }
    public Boolean calculate(ArrayList<String> tokens,Stack<Boolean> st, Stack<String> op){
        int len=tokens.size();
        if(len==0){
            Boolean a=new Boolean();
            a.bvalue=false;
            return a;
        }
        boolean got_paren=false;
        int paren_count=0;
        boolean vulnerable=true;
        for(int i=0;i<len;i++){
            if(vulnerable&&tokens.get(i).equals("-")){
                Boolean zero=new Boolean();
                zero.value=true;
                zero.bool=false;
                zero.vvalue=0;
                st.push(zero);
                op.push("-");
            }
            else if(tokens.get(i).equals("(")){
                op.push("(");
                vulnerable=true;
            }
            else if(tokens.get(i).equals(")")){
                while (!op.empty()&&(!op.peek().equals("("))) {
                    process_op(st, op.peek());
                    op.pop();
                }
                op.pop();
                vulnerable=false;
            }
            else if (is_op(tokens.get(i))) {
                String cur_op = tokens.get(i);
                while (true){
                    if(op.empty())
                        break;
                    if(op.peek().equals("("))
                        break;
                    if(priority(op.peek()) < priority(cur_op))
                        break;
                    process_op(st, op.peek());
                    op.pop();
                    //op.push(cur_op);
                }
                op.push(cur_op);
                vulnerable=false;
            }
            else if(tokens.get(i).equals("true")){
                Boolean x=new Boolean();
                x.bvalue=true;
                st.push(x);
            }
            else if(tokens.get(i).equals("false")){
                 Boolean x=new Boolean();
                 x.bvalue=false;
                st.push(x);
            }
            else{
                double number = 0;
                String s=tokens.get(i);
                int len2=s.length();
                int len3=len2;
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
                Boolean Number=new Boolean();
                Number.bool=false;
                Number.value=true;
                Number.vvalue=number;
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
}
