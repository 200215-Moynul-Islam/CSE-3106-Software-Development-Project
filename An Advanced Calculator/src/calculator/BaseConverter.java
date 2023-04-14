
package calculator;


public class BaseConverter {
    public static int evaluate(char ch){
        if(ch>='A'){
            return (int) ch-55;
            
        }
        else
            return (int) ch-48;
    }
    public static String evaluate(String ch){
        
        if(ch.equals("1")||ch.equals("2")||ch.equals("3")||ch.equals("4")||ch.equals("5")||ch.equals("6")||ch.equals("7")||ch.equals("8")||ch.equals("9")){
            return ch;
        }
        else if(ch.equals("10"))
            return "A";
        else if(ch.equals("11"))
            return "B";
        else if(ch.equals("12"))
            return "C";
        else if(ch.equals("13"))
            return "D";
        else if(ch.equals("14"))
            return "E";
        else if(ch.equals("15"))
            return "F";
        return "";
    }
    public static String discardPoint(String s){
        String ans="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                break;
            }
            else{
                ans=ans+s.charAt(i);
            }
        }
        return ans;
    }
    
    public static String DToB(String s){
        String ans="";
        String before_point="";
        int i;
        for(i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                break;
            }
            else{
                before_point=before_point+s.charAt(i);
            }
        }
        String after_point="0.";
        i++;
        for( ;i<s.length();i++){
            after_point=after_point+s.charAt(i);
        }
        while(true){
            if(ArithmeticOperation.checkZero(before_point)){
                break;
            }
            String divResult=ArithmeticOperation.divide(before_point,"2");
            divResult=discardPoint(divResult);
            String rem=ArithmeticOperation.sub(before_point,ArithmeticOperation.multiply(divResult, "2"));
            before_point=divResult;
            ans=ans+rem;
        }
        String rev="";
        for(int j=ans.length()-1;j>=0;j--){
            rev=rev+ans.charAt(j);
        }
        ans=rev;
        if(ArithmeticOperation.checkZero(after_point)){
            return ans;
        }
        ans=ans+'.';
        int limit=0;
        while(limit<15){
            if(ArithmeticOperation.checkZero(after_point)){
                break;
            }
            String rem=ArithmeticOperation.multiply(after_point,"2");
            rem=discardPoint(rem);
            ans=ans+rem;
            after_point=ArithmeticOperation.sub(ArithmeticOperation.multiply(after_point,"2"),rem);
            limit++;
        }
        return ans;
    }

    public static String DToO(String s){
        String ans="";
        String before_point="";
        int i;
        for(i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                break;
            }
            else{
                before_point=before_point+s.charAt(i);
            }
        }
        String after_point="0.";
        i++;
        for( ;i<s.length();i++){
            after_point=after_point+s.charAt(i);
        }
        while(true){
            if(ArithmeticOperation.checkZero(before_point)){
                break;
            }
            String divResult=ArithmeticOperation.divide(before_point,"8");
            divResult=discardPoint(divResult);
            String rem=ArithmeticOperation.sub(before_point,ArithmeticOperation.multiply(divResult, "8"));
            before_point=divResult;
            ans=ans+rem;
        }
        String rev="";
        for(int j=ans.length()-1;j>=0;j--){
            rev=rev+ans.charAt(j);
        }
        ans=rev;
        if(ArithmeticOperation.checkZero(after_point)){
            return ans;
        }
        ans=ans+'.';
        int limit=0;
        while(limit<15){
            if(ArithmeticOperation.checkZero(after_point)){
                break;
            }
            String rem=ArithmeticOperation.multiply(after_point,"8");
            rem=discardPoint(rem);
            ans=ans+rem;
            after_point=ArithmeticOperation.sub(ArithmeticOperation.multiply(after_point,"8"),rem);
            limit++;
        }
        return ans;
    }
    
    public static String DToH(String s){
        System.out.println("ENTERED");
        String ans="";
        String before_point="";
        int i;
        for(i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                break;
            }
            else{
                before_point=before_point+s.charAt(i);
            }
        }
        String after_point="0.";
        i++;
        for( ;i<s.length();i++){
            after_point=after_point+s.charAt(i);
        }
        while(true){
            if(ArithmeticOperation.checkZero(before_point)){
                break;
            }
            String divResult=ArithmeticOperation.divide(before_point,"16");
            divResult=discardPoint(divResult);
            String rem=ArithmeticOperation.sub(before_point,ArithmeticOperation.multiply(divResult, "16"));
            before_point=divResult;
            ans=ans+evaluate(rem);
        }
        String rev="";
        for(int j=ans.length()-1;j>=0;j--){
            rev=rev+ans.charAt(j);
        }
        ans=rev;
        if(ArithmeticOperation.checkZero(after_point)){
            return ans;
        }
        ans=ans+'.';
        int limit=0;
        while(limit<15){
            if(ArithmeticOperation.checkZero(after_point)){
                break;
            }
            String rem=ArithmeticOperation.multiply(after_point,"16");
            rem=discardPoint(rem);
            ans=ans+evaluate(rem);
            after_point=ArithmeticOperation.sub(ArithmeticOperation.multiply(after_point,"16"),rem);
            limit++;
        }
        return ans;
    }
    
    public static String BToO(String s){
        return DToO(BToD(s));
    }
    
    public static String BToD(String s){
        int a=s.length();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                a=i;
                break;
            }
        }
        double result=0;
        for(int i=0;i<a;i++){
            int x=a-i-1;
            double y=Generator.power(2,x);
            result+=(y*evaluate(s.charAt(i)));
        }
        for(int i=a+1;i<s.length();i++){
            int x=a-i;
            double y=Generator.power(2, x);
            result+=(y*evaluate(s.charAt(i)));
        }
        String ans=""+result;
        ans=ArithmeticOperation.discardLastZero(ans);
        return ans;
    }
    
    public static String BToH(String s){
        return DToH(BToD(s));
    }
    
    public static String OToB(String s){
        return DToB(OToD(s));
    }
    
    public static String OToD(String s){
        int a=s.length();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                a=i;
                break;
            }
        }
        double result=0;
        for(int i=0;i<a;i++){
            int x=a-i-1;
            double y=Generator.power(8,x);
            result+=(y*evaluate(s.charAt(i)));
        }
        for(int i=a+1;i<s.length();i++){
            int x=a-i;
            double y=Generator.power(8, x);
            result+=(y*evaluate(s.charAt(i)));
        }
        String ans=""+result;
        ans=ArithmeticOperation.discardLastZero(ans);
        return ans;
    }
    
    public static String OToH(String s){
        return DToH(OToD(s));
    }
    
    public static String HToB(String s){
        return DToB(HToD(s));
    }
    
    public static String HToO(String s){
        return DToO(HToD(s));
    }
    
    public static String HToD(String s){
        int a=s.length();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                a=i;
                break;
            }
        }
        double result=0;
        for(int i=0;i<a;i++){
            int x=a-i-1;
            double y=Generator.power(16,x);
            result+=(y*evaluate(s.charAt(i)));
        }
        for(int i=a+1;i<s.length();i++){
            int x=a-i;
            double y=Generator.power(16, x);
            result+=(y*evaluate(s.charAt(i)));
        }
        String ans=""+result;
        ans=ArithmeticOperation.discardLastZero(ans);
        return ans;
    }
    
    public static String converter(String s){
        String type="";
        int i;
        for(i=0;i<s.length();i++){
            if(s.charAt(i)=='(')
                break;
            type=type+s.charAt(i);
        }
        String inp="";
        i++;
        for( ;i<s.length();i++){
            if(s.charAt(i)==')')
                break;
            inp=inp+s.charAt(i);
        }
        String ans="";
        if(type.equals("DToB")){
            ans=DToB(inp);
        }
        else if(type.equals("DToO")){
            ans=DToO(inp);
        }
        else if(type.equals("DToH")){
            ans=DToH(inp);
        }
        else if(type.equals("BToO")){
            ans=BToO(inp);
        }
        else if(type.equals("BToD")){
            ans=BToD(inp);
        }
        else if(type.equals("BToH")){
            ans=BToH(inp);
        }
        else if(type.equals("OToB")){
            ans=OToB(inp);
        }
        else if(type.equals("OToD")){
            ans=OToD(inp);
        }
        else if(type.equals("OToH")){
            ans=OToH(inp);
        }
        else if(type.equals("HToB")){
            ans=HToB(inp);
        }
        else if(type.equals("HToO")){
            ans=HToO(inp);
        }
        else if(type.equals("HToD")){
            ans=HToD(inp);
        }
        return ans;
    }

}
