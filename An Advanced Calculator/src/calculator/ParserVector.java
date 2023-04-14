package calculator;
import java.util.*;

public class ParserVector {
    public void process_op(Stack<Vector> st, char op){
        Vector r = st.peek(); st.pop();
        Vector l = st.peek(); st.pop();
        switch (op) {
            case '+': st.push(Vector.add(r,l)); break;
            case '-': st.push(Vector.sub(l,r)); break;
            case '*': st.push(Vector.multiply(l,r)); break;
            case '/': st.push(Vector.divide(l,r)); break;
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
    public String evaluate(String s){
        int len=s.length();
        int run=0;
        String token="";
        ArrayList<String> tokens=new ArrayList<>();
        for(int i=0;i<len;i++){
            if(run==0){
                if(s.charAt(i)==' '){
                }
                else if(s.charAt(i)=='i'||s.charAt(i)=='j'||s.charAt(i)=='k'){
                    token=token+s.charAt(i);
                    tokens.add(token);
                    token=""+s.charAt(i);
                    run=0;
                    token="";
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
                else if((s.charAt(i)=='i'||s.charAt(i)=='j'||s.charAt(i)=='k')&&(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'||s.charAt(i-1)=='.')){
                    token=token+s.charAt(i);
                    tokens.add(token);
                    run=0;
                    token="";
                }
                else if((s.charAt(i)=='i'||s.charAt(i)=='j'||s.charAt(i)=='k')&&(s.charAt(i-1)<'a'||s.charAt(i-1)>'z')){
                    tokens.add(token);
                    token=""+s.charAt(i);
                    tokens.add(token);
                    run=0;
                    token="";
                }
                else if(s.charAt(i)>='a'){
                    if(s.charAt(i-1)>='a')
                        token=token+s.charAt(i);
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
        Stack<Vector> st= new Stack<Vector>();
        Stack<Character> op=new Stack<Character>();
        Vector x=calculate(tokens,st,op);
        String Result="";
        if(x==null)
            return "Invalid";
        if(x.scalar==false){
            if(x.x!=0)
                Result=Result+x.x+"i";
            if(x.y>0){
                Result=Result+"+"+x.y+"j";
            }
            else if(x.y<0){
                Result=Result+x.y+"j";
            }
            if(x.z>0){
                Result=Result+"+"+x.z+"k";
            }
            else if(x.z<0){
                Result=Result+x.z+"k";
            }
            if(Result.length()==0){
                Result="0";
            }
        }
        else{
            Result=Result+x.value;
        }
        return Result;
    }
    public Vector calculate(ArrayList<String> tokens,Stack<Vector> st, Stack<Character> op){
        int len=tokens.size();
        if(len==0){
            Vector a=new Vector();
            a.x=0;
            a.y=0;
            a.z=0;
            return a;
        }
        boolean got_paren=false;
        int paren_count=0;
        boolean vulnerable=true;
        for(int i=0;i<len;i++){
            Vector func_result;
            if(vulnerable&&tokens.get(i).equals("-")){
                Vector zero=new Vector();
                zero.scalar=true;
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
            else if(tokens.get(i).charAt(0)=='i'){
                Vector Number=new Vector();
                Number.x=1;
                st.push(Number);
                vulnerable=false;
            }
            else if(tokens.get(i).charAt(0)=='j'){
                Vector Number=new Vector();
                Number.y=1;
                st.push(Number);
                vulnerable=false;
            }
            else if(tokens.get(i).charAt(0)=='k'){
                Vector Number=new Vector();
                Number.z=1;
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
               functionsVector a=new functionsVector();
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
                if(s.charAt(len2-1)=='j'||s.charAt(len2-1)=='i'||s.charAt(len2-1)=='k'){
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
                Vector Number=new Vector();
                if(s.charAt(len3-1)=='i'){
                    Number.x=number;
                }
                else if(s.charAt(len3-1)=='j'){
                    Number.y=number;
                }
                else if(s.charAt(len3-1)=='k'){
                    Number.z=number;
                }
                else{
                    Number.scalar=true;
                    Number.vec=false;
                    Number.value=number;
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
}
class functionsVector{
    String functions[]={"dot", "cross","abs", "unit","angler", "angled"};
    int parameters[]={2,2,1,1,2,2};
    public Vector func_evaluate(ArrayList<String> tokens){
        String func=tokens.get(0);
        int func_num = 0;
        for (int i=0;i<functions.length;i++) {
            if(functions[i].equals(func)){
                func_num=i;
            }
        }
        Vector x=new Vector();
        int parameter=parameters[func_num];
        Vector func_parameters[]=new Vector[parameter];
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
                ParserVector b=new ParserVector();
                Stack<Vector> st =new Stack<Vector>();
                Stack<Character> op=new Stack<Character>();
                Vector funcvalue=b.calculate(funct,st,op);
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
                Stack<Vector> st =new Stack<Vector>();
                Stack<Character> op=new Stack<Character>();
                ParserVector b= new ParserVector();
                Vector funcvalue=b.calculate(funct,st,op);
                func_parameters[index]=funcvalue;
                index++;
                ArrayList<String> a=new ArrayList<String>();
                funct=a;

            }
        }
        if(func_num==0){
            x=calculator.Vector.dot(func_parameters[0],func_parameters[1]);
        }
        else if(func_num==1){
            x=calculator.Vector.cross(func_parameters[0],func_parameters[1]);
        }
        else if(func_num==2){
            x=calculator.Vector.abs(func_parameters[0]);
        }
        else if(func_num==3){
            x=calculator.Vector.unit(func_parameters[0]);
        }
        else if(func_num==4){
            x=calculator.Vector.angler(func_parameters[0],func_parameters[1]);
        }
        else if(func_num==5){
            x=calculator.Vector.angled(func_parameters[0],func_parameters[1]);
        }
        return x;
    }
}