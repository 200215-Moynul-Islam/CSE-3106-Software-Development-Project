package calculator;

public class GeneratorBigNumber {
    public static String factorial(String x){
        String fact="1";
        x=ArithmeticOperation.discardLastZero(x);
        int point=ArithmeticOperation.pointIndex(x);
        if(point!=x.length()){
            return "NaN";
        }
        for(String i="1";!(ArithmeticOperation.greater(i, x));i=ArithmeticOperation.add(i,"1")){
            fact=ArithmeticOperation.multiply(fact, i);
        }
        return fact;
    }
    public static String combination(String x,String y){
        x=ArithmeticOperation.discardLastZero(x);
        y=ArithmeticOperation.discardLastZero(y);
        if(ArithmeticOperation.pointIndex(x)!=x.length()||ArithmeticOperation.pointIndex(y)!=y.length()){
            return "NaN";
        }
        String comb="1";
        String i,j;
        for(i=x,j="1";!(ArithmeticOperation.greater(j, y));i=ArithmeticOperation.sub(i, "1"),j=ArithmeticOperation.add(j, "1")){
            comb=ArithmeticOperation.multiply(i, comb);
            comb=ArithmeticOperation.divide(comb, j);
        }
        return comb;
    }
    public static String permutation(String x,String y){
        x=ArithmeticOperation.discardLastZero(x);
        y=ArithmeticOperation.discardLastZero(y);
        if(ArithmeticOperation.pointIndex(x)!=x.length()||ArithmeticOperation.pointIndex(y)!=y.length()){
            return "NaN";
        }
        String perm="1";
        String i,j;
        for(i=x,j="1";!(ArithmeticOperation.greater(j, y));i=ArithmeticOperation.sub(i, "1"),j=ArithmeticOperation.add(j, "1")){
            perm=ArithmeticOperation.multiply(i, perm);
        }
        return perm;
    }
    public static String exponent(String x){
        if(ArithmeticOperation.greater("0", x))
            return ArithmeticOperation.divide("1",exponent(ArithmeticOperation.multiply(x,"-1")));
        String exp="1";
        String i=ArithmeticOperation.add(ArithmeticOperation.multiply("1000", x), "1000");
        i=ArithmeticOperation.makeInt(i);
        for(String j=i;ArithmeticOperation.greater(j, "0");j=ArithmeticOperation.sub(j,"1")){
            exp=ArithmeticOperation.add("1", ArithmeticOperation.divide(ArithmeticOperation.multiply(x, exp), j));
            System.out.println(j);
        }
        return exp;
    }
    public static String fibonacci(String x){
        if(ArithmeticOperation.pointIndex(x)!=x.length())
            return "NaN";
        if(ArithmeticOperation.equal(x, "0"))
            return "0";
        if(ArithmeticOperation.equal(x, "1"))
            return "1";
        String f="1",s="0",fibo="0";
        for(String i="2";!(ArithmeticOperation.greater(i, x));i=ArithmeticOperation.add(i, "1")){
            fibo=ArithmeticOperation.add(f, s);
            s=f;
            f=fibo;
        }
        return fibo;
    }
}
