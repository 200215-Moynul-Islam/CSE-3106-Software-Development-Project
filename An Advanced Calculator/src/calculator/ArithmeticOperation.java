package calculator;

public class ArithmeticOperation {
    public static String addZeroIfPoint(String s){
        if(s.length()==0){
            return "0";
        }
        if(s.charAt(0)=='.'){
            String st="0";
            for(int i=0;i<s.length();i++){
                st=st+s.charAt(i);
            }
            return st;
        }
        return s;
    }
    public static String discardLastZero(String s){
        int len=s.length();
        int lastDigit=-1;
        for(int i=len-1;i>=0;i--){
            if(s.charAt(i)!='0'){
                lastDigit = i;
                break;
            }
        }
        int pointPosition=pointIndex(s);
        String st="";
        if(pointPosition==lastDigit){
            for(int i=0;i<pointPosition;i++){
                st=st+s.charAt(i);
            }
        }
        else if(pointPosition==len){
            st=s;
        }
        else{
            for(int i=0;i<=lastDigit;i++){
                st=st+s.charAt(i);
            }
        }
        if(st.length()==0){
            st="0";
        }
        return st;
    }
    public static boolean checkZero(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='0'&&s.charAt(i)!='-'&&s.charAt(i)!='.')
                return false;
        }
        return true;
    }
    public static int pointIndex(String s){
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)=='.'){
                return i;
            }
        }
        return s.length();
    }
    public static String discardZero(String s){
        int firstDigit=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='0'){
                firstDigit=i;
                break;
            }
        }
        String wzero="";
        for(int i=firstDigit;i<s.length();i++){
            wzero=wzero+s.charAt(i);
        }
        s=wzero;
        if(s.length()==0){
            s="0";
        }
        return s;
    }
    public static String absolute(String s){
        String s2="";
        if(s.charAt(0)=='-'){
            for(int i=1;i<s.length();i++){
                s2=s2+s.charAt(i);
            }
            return s2;
        }
        return s;
    }
    public static String add(String firstNumber,String secondNumber){
        if(firstNumber.equals("NaN")||secondNumber.equals("NaN")){
            return "NaN";
        }
        if(firstNumber.equals("Infinity")||secondNumber.equals("Infinity")){
            return "Infinity";
        }
        String answer="";
        String finalAnswer="";
        int firstNumberLength=firstNumber.length();
        int secondNumberLength=secondNumber.length();
        String absoluteFirstNumber="",absoluteSecondNumber="";
        if(firstNumber.charAt(0)=='-'){
            for(int i=1;i<firstNumberLength;i++){
                absoluteFirstNumber=absoluteFirstNumber+firstNumber.charAt(i);
            }
        }
        else{
            absoluteFirstNumber=firstNumber;
        }
        if(secondNumber.charAt(0)=='-'){
            for(int i=1;i<secondNumberLength;i++){
                absoluteSecondNumber=absoluteSecondNumber+secondNumber.charAt(i);
            }
        }
        else{
            absoluteSecondNumber=secondNumber;
        }
        if(firstNumber.charAt(0)=='-'&&secondNumber.charAt(0)=='-'){
            finalAnswer=finalAnswer+'-';
            String temporaryAnswer=add(absoluteFirstNumber,absoluteSecondNumber);
            finalAnswer=finalAnswer+temporaryAnswer;
            return finalAnswer;
        }
        else if(firstNumber.charAt(0)=='-'&&secondNumber.charAt(0)!='-'){
            return sub(absoluteSecondNumber,absoluteFirstNumber);
        }
        else if(firstNumber.charAt(0)!='-'&&secondNumber.charAt(0)=='-'){
            return sub(absoluteFirstNumber,absoluteSecondNumber);
        }
        int firstNumberPointPosition=-1,secondNumberPointPosition=-1,i,j,maximumDigitsAfterPoints,firstNumberDigitsAfterPoints,secondNumberDigitsAfterPoints,carry=0;
        for(i=0;i<firstNumberLength;i++){
            if(firstNumber.charAt(i)=='.'){
                firstNumberPointPosition=i;
            }
        }
        if(firstNumberPointPosition==-1){
            firstNumberPointPosition=firstNumberLength;
            firstNumber=firstNumber+'.';
            firstNumberLength++;
        }
        for(i=0;i<secondNumberLength;i++){
            if(secondNumber.charAt(i)=='.'){
                secondNumberPointPosition=i;
            }
        }
        if(secondNumberPointPosition==-1){
            secondNumberPointPosition=secondNumberLength;
            secondNumber=secondNumber+'.';
            secondNumberLength++;
        }
        firstNumberDigitsAfterPoints=firstNumberLength-firstNumberPointPosition-1;
        secondNumberDigitsAfterPoints=secondNumberLength-secondNumberPointPosition-1;
        if(firstNumberDigitsAfterPoints>secondNumberDigitsAfterPoints){
            maximumDigitsAfterPoints=firstNumberDigitsAfterPoints;
        }
        else{
            maximumDigitsAfterPoints=secondNumberDigitsAfterPoints;
        }
        for(i=firstNumberPointPosition+maximumDigitsAfterPoints,j=secondNumberPointPosition+maximumDigitsAfterPoints;i>=0||j>=0;i--,j--){
            if((i>=0&&i<firstNumberLength)&&(j>=0&&j<secondNumberLength)){
                if(firstNumber.charAt(i)=='.'){
                    answer=answer+'.';
                }
                else{
                    int digit1=firstNumber.charAt(i)-48;
                    int digit2=secondNumber.charAt(j)-48;
                    answer=answer+(char) ((digit1+digit2+carry)%10+48);
                    carry=(digit1+digit2+carry)/10;
                }
            }
            else if(i>=0&&i<firstNumberLength){
                if(firstNumber.charAt(i)=='.'){
                    answer=answer+'.';
                }
                else{
                    int digit1=firstNumber.charAt(i)-48;
                    answer=answer+(char) ((digit1+carry)%10+48);
                    carry=(digit1+carry)/10;
                }
            }
            else if(j>=0&&j<secondNumberLength){
                if(secondNumber.charAt(j)=='.'){
                    answer=answer+'.';
                }
                else{
                    int digit2=secondNumber.charAt(j)-48;
                    answer=answer+(char) ((digit2+carry)%10+48);
                    carry=(digit2+carry)/10;
                }
            }
        }
        if(carry>0)
        answer=answer+(char) (carry+48);
        int answerLength=answer.length();
        for(i=answerLength-1;i>=0;i--){
            finalAnswer=finalAnswer+answer.charAt(i);
        }
        finalAnswer=discardZero(finalAnswer);
        finalAnswer=discardLastZero(finalAnswer);
        int l=finalAnswer.length();
        String st="";
        for(int k=0;k<1000&&k<l;k++){
            st=st+finalAnswer.charAt(k);
        }
        if(pointIndex(finalAnswer)>1000){
            return "Infinity";
        }
        st=addZeroIfPoint(st);
        return st;
    }
    public static String sub(String firstNumber,String secondNumber){
        if(firstNumber.equals("NaN")||secondNumber.equals("NaN")){
            return "NaN";
        }
        if(firstNumber.equals("Infinity")||secondNumber.equals("Infinity")){
            return "Infinity";
        }
        String answer="";
        String finalAnswer="";
        int firstNumberLength=firstNumber.length();
        int secondNumberLength=secondNumber.length();
        String absoluteFirstNumber="",absoluteSecondNumber="";
        if(firstNumber.charAt(0)=='-'){
            for(int i=1;i<firstNumberLength;i++){
                absoluteFirstNumber=absoluteFirstNumber+firstNumber.charAt(i);
            }
        }
        else{
            absoluteFirstNumber=firstNumber;
        }
        if(secondNumber.charAt(0)=='-'){
            for(int i=1;i<secondNumberLength;i++){
                absoluteSecondNumber=absoluteSecondNumber+secondNumber.charAt(i);
            }
        }
        else{
            absoluteSecondNumber=secondNumber;
        }
        if(firstNumber.charAt(0)=='-'&&secondNumber.charAt(0)=='-'){
            String temporaryAnswer=sub(absoluteFirstNumber,absoluteSecondNumber);
            if(temporaryAnswer.charAt(0)=='-'){
                int l5=temporaryAnswer.length();
                for(int i=1;i<l5;i++){
                    finalAnswer=finalAnswer+temporaryAnswer.charAt(i);
                }
                return finalAnswer;
            }
            else{
                finalAnswer=finalAnswer+'-'+temporaryAnswer;
                return finalAnswer;
            }
        }
        else if(firstNumber.charAt(0)=='-'&&secondNumber.charAt(0)!='-'){
            String temporaryAnswer=add(absoluteSecondNumber,absoluteFirstNumber);
            if(temporaryAnswer.charAt(0)=='-'){
                int l5=temporaryAnswer.length();
                for(int i=1;i<l5;i++){
                    finalAnswer=finalAnswer+temporaryAnswer.charAt(i);
                }
                return finalAnswer;
            }
            else{
                finalAnswer=finalAnswer+'-'+temporaryAnswer;
                return finalAnswer;
            }
        }
        else if(firstNumber.charAt(0)!='-'&&secondNumber.charAt(0)=='-'){
            String temporaryAnswer=add(absoluteFirstNumber,absoluteSecondNumber);
            return temporaryAnswer;
        }
        int firstNumberPointPosition=-1,secondNumberPointPosition=-1,i,j,maximumDigitsAfterPoints,firstNumberDigitsAfterPoints,secondNumberDigitsAfterPoints,carry=0;
        for(i=0;i<firstNumberLength;i++){
            if(firstNumber.charAt(i)=='.'){
                firstNumberPointPosition=i;
            }
        }
        if(firstNumberPointPosition==-1){
            firstNumberPointPosition=firstNumberLength;
            firstNumber=firstNumber+'.';
            firstNumberLength++;
        }
        for(i=0;i<secondNumberLength;i++){
            if(secondNumber.charAt(i)=='.'){
                secondNumberPointPosition=i;
            }
        }
        if(secondNumberPointPosition==-1){
            secondNumberPointPosition=secondNumberLength;
            secondNumber=secondNumber+'.';
            secondNumberLength++;
        }
        firstNumberDigitsAfterPoints=firstNumberLength-firstNumberPointPosition-1;
        secondNumberDigitsAfterPoints=secondNumberLength-secondNumberPointPosition-1;
        if(firstNumberDigitsAfterPoints>secondNumberDigitsAfterPoints){
            maximumDigitsAfterPoints=firstNumberDigitsAfterPoints;
        }
        else{
            maximumDigitsAfterPoints=secondNumberDigitsAfterPoints;
        }
        for(i=firstNumberPointPosition+maximumDigitsAfterPoints,j=secondNumberPointPosition+maximumDigitsAfterPoints;i>=0||j>=0;i--,j--){
            if((i>=0&&i<firstNumberLength)&&(j>=0&&j<secondNumberLength)){
                if(firstNumber.charAt(i)=='.'){
                    answer=answer+'.';
                }
                else{
                    int digit1=firstNumber.charAt(i)-48;
                    int digit2=secondNumber.charAt(j)-48;
                    digit2+=carry;
                    if(digit2<=digit1){
                        carry=0;
                        answer=answer+ (char) (digit1-digit2+48);
                    }else{
                        digit1+=10;
                        carry=1;
                        answer=answer+ (char) (digit1-digit2+48);
                    }
                }
            }
            else if(i>=0&&i<firstNumberLength){
                if(firstNumber.charAt(i)=='.'){
                    answer=answer+'.';
                }
                else{
                    int digit1=firstNumber.charAt(i)-48;
                    int digit2=0;
                    digit2+=carry;
                    if(digit2<=digit1){
                        carry=0;
                        answer=answer+ (char) (digit1-digit2+48);
                    }else{
                        digit1+=10;
                        carry=1;
                        answer=answer+ (char) (digit1-digit2+48);
                    }
                }
            }
            else if(j>=0&&j<secondNumberLength){
                if(secondNumber.charAt(j)=='.'){
                    answer=answer+'.';
                }
                else{
                    int digit1=0;
                    int digit2=secondNumber.charAt(j)-48;
                    digit2+=carry;
                    if(digit2<=digit1){
                        carry=0;
                        answer=answer+ (char) (digit1-digit2+48);
                    }else{
                        digit1+=10;
                        carry=1;
                        answer=answer+ (char) (digit1-digit2+48);
                    }
                }
            }
        }
        if(carry>=1){
            finalAnswer=finalAnswer+'-';
            String s5=sub(secondNumber,firstNumber);
            finalAnswer=finalAnswer+s5;
        }
        else{
            int answerLength=answer.length();
            for(i=answerLength-1;i>=0;i--){
                finalAnswer=finalAnswer+answer.charAt(i);
            }
        }
        finalAnswer=discardZero(finalAnswer);
        finalAnswer=discardLastZero(finalAnswer);
        String st="";
        int l=finalAnswer.length();
        for(int k=0;k<1000&&k<l;k++){
            st=st+finalAnswer.charAt(k);
        }
        if(pointIndex(finalAnswer)>1000){
            return "Infinity";
        }
        st=addZeroIfPoint(st);
        return st;
    }
    public static String multiply(String firstNumber,String secondNumber){
        if(firstNumber.equals("NaN")||secondNumber.equals("NaN")){
            return "NaN";
        }
        if(firstNumber.equals("Infinity")||secondNumber.equals("Infinity")){
            return "Infinity";
        }
        String firstNumberWithoutPoint="";
        String secondNumberWithoutPoint="";
        String finalAnswer="";
        String temporaryAnswer="";
        int firstNumberLength=firstNumber.length();
        int secondNumberLength=secondNumber.length();
        String absoluteFirstNumber="",absoluteSecondNumber="";
        if(firstNumber.charAt(0)=='-'){
            for(int i=1;i<firstNumberLength;i++){
                absoluteFirstNumber=absoluteFirstNumber+firstNumber.charAt(i);
            }
        }
        else{
            absoluteFirstNumber=firstNumber;
        }
        if(secondNumber.charAt(0)=='-'){
            for(int i=1;i<secondNumberLength;i++){
                absoluteSecondNumber=absoluteSecondNumber+secondNumber.charAt(i);
            }
        }
        else{
            absoluteSecondNumber=secondNumber;
        }
        if(firstNumber.charAt(0)=='-'&&secondNumber.charAt(0)=='-'){
            return multiply(absoluteSecondNumber,absoluteFirstNumber);
        }
        else if(firstNumber.charAt(0)=='-'||secondNumber.charAt(0)=='-'){
            temporaryAnswer=multiply(absoluteSecondNumber,absoluteFirstNumber);
            finalAnswer=finalAnswer+'-'+temporaryAnswer;
            return finalAnswer;
        }
        int firstNumberPointPosition=-1,secondNumberPointPosition=-1,i;
        for(i=0;i<firstNumberLength;i++){
            if(firstNumber.charAt(i)=='.'){
                firstNumberPointPosition=i;
            }
        }
        if(firstNumberPointPosition==-1){
            firstNumberPointPosition=firstNumberLength;
            firstNumber=firstNumber+'.';
            firstNumberLength++;
        }
        for(i=0;i<secondNumberLength;i++){
            if(secondNumber.charAt(i)=='.'){
                secondNumberPointPosition=i;
            }
        }
        if(secondNumberPointPosition==-1){
            secondNumberPointPosition=secondNumberLength;
            secondNumber=secondNumber+'.';
            secondNumberLength++;
        }
        int rest1=firstNumberLength-firstNumberPointPosition-1;
        int rest2=secondNumberLength-secondNumberPointPosition-1;
        for(i=0;i<firstNumberPointPosition;i++){
            firstNumberWithoutPoint=firstNumberWithoutPoint+firstNumber.charAt(i);
        }
        for(i=firstNumberPointPosition+1;i<firstNumberLength;i++){
            firstNumberWithoutPoint=firstNumberWithoutPoint+firstNumber.charAt(i);
        }
        for(i=0;i<secondNumberPointPosition;i++){
            secondNumberWithoutPoint=secondNumberWithoutPoint+secondNumber.charAt(i);
        }
        for(i=secondNumberPointPosition+1;i<secondNumberLength;i++){
            secondNumberWithoutPoint=secondNumberWithoutPoint+secondNumber.charAt(i);
        }
        if(firstNumberWithoutPoint.equals("0")||secondNumberWithoutPoint.equals("0")) {
            return "0";
        }
        StringBuilder num1=new StringBuilder(firstNumberWithoutPoint);
        StringBuilder num2=new StringBuilder(secondNumberWithoutPoint);
        num1.reverse();
        num2.reverse();
        int n=num1.length()+num2.length();
        StringBuilder answer=new StringBuilder();
        for(i=0;i<n;i++) {
            answer.append("0");
        }
        int digit1,digit2;
        for(int place2=0;place2<num2.length();place2++) {
            digit2 =num2.charAt(place2)-48;
            for(int place1=0;place1<num1.length();place1++) {
                digit1=num1.charAt(place1)-48;
                int currentPos = place1 + place2;
                int carry = answer.charAt(currentPos)-48;
                int product=digit1*digit2+carry;
                answer.setCharAt(currentPos,(char) (product%10+48));
                int value=(answer.charAt(currentPos+1)-48)+product/10;
                answer.setCharAt(currentPos+1,(char) (value+48));
            }
        }
        if(answer.charAt(answer.length()-1)==48) {
            answer.deleteCharAt(answer.length()-1);
        }
        answer.insert(rest1+rest2, '.');
        answer.reverse();
        finalAnswer=answer.toString();
        finalAnswer=discardZero(finalAnswer);
        finalAnswer=discardLastZero(finalAnswer);
        int l=finalAnswer.length();
        String st="";
        for(int k=0;k<1000&&k<l;k++){
            st=st+finalAnswer.charAt(k);
        }
        if(pointIndex(finalAnswer)>1000){
            return "Infinity";
        }
        st=addZeroIfPoint(st);
        return st;
    }
    public static String divide(String firstNumber,String secondNumber){
        if(firstNumber.equals("NaN")||secondNumber.equals("NaN")){
            return "NaN";
        }
        else if(firstNumber.equals("Infinity")&&secondNumber.equals("Infinity")){
            return "NaN";
        }
        else if(checkZero(secondNumber)&&checkZero(firstNumber)){
            return "NaN";
        }
        else if(checkZero(secondNumber)){
            return "Infinity";
        }
        else if(checkZero(firstNumber)){
            return "0";
        }
        else if(firstNumber.equals("Infinity")){
            return "Infinity";
        }
        else if(secondNumber.equals("Infinity")){
            return "0";
        }
        String finalAnswer="";
        int firstNumberLength=firstNumber.length();
        int secondNumberLength=secondNumber.length();
        String absoluteFirstNumber,absoluteSecondNumber;
        absoluteFirstNumber=absolute(firstNumber);
        absoluteSecondNumber=absolute(secondNumber);
        if(firstNumber.charAt(0)=='-'&&secondNumber.charAt(0)=='-'){
            return divide(absoluteFirstNumber,absoluteSecondNumber);
        }
        else if(firstNumber.charAt(0)=='-'||secondNumber.charAt(0)=='-'){
            String temporaryAnswer=divide(absoluteFirstNumber,absoluteSecondNumber);
            finalAnswer=finalAnswer+'-'+temporaryAnswer;
            return finalAnswer;
        }
        int firstNumberPointPosition=-1,secondNumberPointPosition=-1;
        String firstNumberWithoutPoint="",secondNumberWithoutPoint="";
        firstNumberPointPosition=pointIndex(firstNumber);
        secondNumberPointPosition=pointIndex(secondNumber);
        int firstNumberDigitsAfterPoint,secondNumberDigitsAfterPoint;
        if(firstNumberPointPosition==firstNumberLength){
            firstNumberDigitsAfterPoint=firstNumberLength-firstNumberPointPosition;
        }
        else{
            firstNumberDigitsAfterPoint=firstNumberLength-firstNumberPointPosition-1;
        }
        if(secondNumberPointPosition==secondNumberLength){
            secondNumberDigitsAfterPoint=secondNumberLength-secondNumberPointPosition;
        }
        else{
            secondNumberDigitsAfterPoint=secondNumberLength-secondNumberPointPosition-1;
        }
        int maximumDigitsAfterPoint;
        if(firstNumberDigitsAfterPoint>secondNumberDigitsAfterPoint){
            maximumDigitsAfterPoint=firstNumberDigitsAfterPoint;
        }
        else{
            maximumDigitsAfterPoint=secondNumberDigitsAfterPoint;
        }
        for(int i=0;i<firstNumberPointPosition;i++){
            firstNumberWithoutPoint=firstNumberWithoutPoint+firstNumber.charAt(i);
        }
        for(int i=0;i<secondNumberPointPosition;i++){
            secondNumberWithoutPoint=secondNumberWithoutPoint+secondNumber.charAt(i);
        }
        int i,j;
        for(i=firstNumberPointPosition+1,j=secondNumberPointPosition+1;(i<firstNumberLength)||(j<secondNumberLength);i++,j++){
            if(i<firstNumberLength&&j<secondNumberLength){
                firstNumberWithoutPoint=firstNumberWithoutPoint+firstNumber.charAt(i);
                secondNumberWithoutPoint=secondNumberWithoutPoint+secondNumber.charAt(j);
            }
            else if(i<firstNumberLength){
                firstNumberWithoutPoint=firstNumberWithoutPoint+firstNumber.charAt(i);
                secondNumberWithoutPoint=secondNumberWithoutPoint+'0';
            }
            else if(j<secondNumberLength){
                firstNumberWithoutPoint=firstNumberWithoutPoint+'0';
                secondNumberWithoutPoint=secondNumberWithoutPoint+secondNumber.charAt(j);
            }
        }
        String tempstring="";
        int index=0;
        secondNumberWithoutPoint=discardZero(secondNumberWithoutPoint);
        firstNumberWithoutPoint=discardZero(firstNumberWithoutPoint);
        int l=firstNumberWithoutPoint.length();
        for( i=0;i<2000;i++){
            if(index==l){
                finalAnswer=finalAnswer+".";
            }
            if(index>=l){
                tempstring=tempstring+"0";
            }
            else{
                tempstring=tempstring+firstNumberWithoutPoint.charAt(index);
            }
            int div=0;
            tempstring=discardZero(tempstring);
            tempstring=discardLastZero(tempstring);
            String s=tempstring;
            while(true){
                
                s=sub(s,secondNumberWithoutPoint);
                if(s.charAt(0)=='-')
                    break;
                else
                    div++;
            }
            finalAnswer=finalAnswer+(char)(div+48);
            String divr=""+(char) (div+48);
            String mul=multiply(secondNumberWithoutPoint,divr);
            tempstring=sub(tempstring,mul);
            index++;
        }
        finalAnswer=discardZero(finalAnswer);
        finalAnswer=discardLastZero(finalAnswer);
        int l1=finalAnswer.length();
        String st="";
        for(int k=0;k<1000&&k<l1;k++){
            st=st+finalAnswer.charAt(k);
        }
        if(pointIndex(finalAnswer)>1000){
            return "Infinity";
        }
        st=addZeroIfPoint(st);
        return st;
    }
    public static boolean equal(String s1,String s2){
        int p1,p2;
        p1=pointIndex(s1);
        p2=pointIndex(s2);
        int x;
        if(p1>p2){
            x=p1;
        }
        else{
            x=p2;
        }
        boolean y=true;
        int i=p1-x,j=p2-x;
        for( ;i<p1;i++,j++){
            if(i>=0&&j>=0){
                if(s1.charAt(i)!=s2.charAt(j)){
                    y=false;
                }
            }
            else if(i>=0){
                if(s1.charAt(i)!='0'){
                    y=false;
                }
            }
            else{
                if(s2.charAt(j)!='0'){
                    y=false;
                }
            }
        }
        i=p1+1;
        j=p2+1;
        for( ;i<s1.length()||j<s2.length();i++,j++){
            if(i<=s1.length()&&j<=s2.length()){
                if(s1.charAt(i)!=s2.charAt(j)){
                    y=false;
                }
            }
            else if(i<=s1.length()){
                if(s1.charAt(i)!='0'){
                    y=false;
                }
            }
            else{
                if(s2.charAt(j)!='0'){
                    y=false;
                }
            }
        }
        return y;
    }
    public static boolean greater(String s1,String s2){
        int p1,p2;
        discardZero(s1);
        discardLastZero(s1);
        discardZero(s2);
        discardLastZero(s2);
        p1=pointIndex(s1);
        p2=pointIndex(s2);
        int x;
        if(p1>p2){
            x=p1;
        }
        else{
            x=p2;
        }
        boolean y=false;
        int i=p1-x,j=p2-x;
        for( ;i<p1;i++,j++){
            if(i>=0&&j>=0){
                if(s1.charAt(i)<s2.charAt(j)){
                    return false;
                }
                if(s1.charAt(i)>s2.charAt(j)){
                    return true;
                }
            }
            else if(i>=0){
                if(s1.charAt(i)!='0'){
                    return true;
                }
            }
            else{
                return false;
            }
        }
        i=p1+1;
        j=p2+1;
        for( ;i<s1.length()||j<s2.length();i++,j++){
            if(i<=s1.length()&&j<=s2.length()){
                if(s1.charAt(i)<s2.charAt(j)){
                    return false;
                }
                else if(s1.charAt(i)>s2.charAt(j)){
                    return true;
                }
            }
            else if(i<=s1.length()){
                return true;
            }
            else{
                return false;
            }
        }
        return y;
    }
    public static String makeInt(String s){
        String ans="";
        for(int i=0;i<pointIndex(s);i++){
            ans=ans+s.charAt(i);
        }
        return ans;
    }
}