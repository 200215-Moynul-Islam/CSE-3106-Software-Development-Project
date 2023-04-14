
package calculator;


public class UnitConverter {
    public static String converter(String s){
        String a="";
        int i;
        for(i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                break;
            }
            a=a+s.charAt(i);
        }
        i++;
        String inp="";
        for( ;i<s.length();i++){
            if(s.charAt(i)==')')
                break;
            inp=inp+s.charAt(i);
        }
        int point=ArithmeticOperation.pointIndex(inp);
        double x=0;
        for(i=0;i<point;i++){
            x=x*10+(double) inp.charAt(i)-48;
        }
        double fl=0;
        for(i=point+1;i<inp.length();i++){
            fl+=(Generator.power(10,(point-i))*((double) inp.charAt(i)-48));
        }
        x+=fl;
        int funcIndex=0;
        String conv[]={"MeterToInch","InchToMeter","MeterToFeet","FeetToMeter","KmToMile","MileToKm"};
        for(int j=0;j<conv.length;j++){
            if(conv[i].equals(a)){
                funcIndex=i;
            }
        }
        String result="";
        if(funcIndex==0){
            double y=x*39.37;
            result=""+y;
        }
        else if(funcIndex==1){
            double y=x*0.025400508;
            result=""+y;
        }
        else if(funcIndex==2){
            double y=x*3.280833333333333;
            result=""+y;
        }
        else if(funcIndex==3){
            double y=x*0.3048006096;
            result=""+y;
        }
        else if(funcIndex==4){
            double y=x*0.62;
            result=""+y;
        }
        else if(funcIndex==5){
            double y=x*1.61;
            result=""+y;
        }
        return result;
    }
}
