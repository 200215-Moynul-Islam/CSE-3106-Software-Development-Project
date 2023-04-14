package calculator;

public class Boolean {
    boolean bool=true;
    boolean value=false;
    boolean bvalue;
    double vvalue;
    public static Boolean greater(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.bool||y.bool){
            return null;
        }
        else{
            c.bvalue=(x.vvalue>y.vvalue);
            return c;
        }
    }
    public static Boolean greaterOrEqual(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.bool||y.bool){
            return null;
        }
        else{
            c.bvalue=(x.vvalue>=y.vvalue);
            return c;
        }
    }
    public static Boolean smaller(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.bool||y.bool){
            return null;
        }
        else{
            c.bvalue=x.vvalue<y.vvalue;
            return c;
        }
    }
    public static Boolean smallerOrEqual(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.bool||y.bool){
            return null;
        }
        else{
            c.bvalue=(x.vvalue<=y.vvalue);
            return c;
        }
    }
    public static Boolean equal(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.bool||y.bool){
            return null;
        }
        else{
            c.bvalue=(x.vvalue==y.vvalue);
            return c;
        }
    }
    public static Boolean notEqual(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.bool||y.bool){
            return null;
        }
        else{
            c.bvalue=(x.vvalue!=y.vvalue);
            return c;
        }
    }
    public static Boolean or(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.value||y.value){
            return null;
        }
        else{
            c.bvalue=(x.bvalue||y.bvalue);
            return c;
        }
    }
    public static Boolean and(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.value||y.value){
            return null;
        }
        else{
            c.bvalue=(x.bvalue&&y.bvalue);
            return c;
        }
    }
    public static Boolean not(Boolean x){
        Boolean c=new Boolean();
        if(x.value){
            return null;
        }
        else{
            c.bvalue=(!x.bvalue);
            return c;
        }
    }
    public static Boolean xor(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.value||y.value){
            return null;
        }
        else{
            c.bvalue=(x.bvalue^y.bvalue);
            return c;
        }
    }
    public static Boolean xnor(Boolean x,Boolean y){
        Boolean c=new Boolean();
        if(x.value||y.value){
            return null;
        }
        else{
            c.bvalue=!(x.bvalue||y.bvalue);
            return c;
        }
    }
    public static Boolean add(Boolean x, Boolean y){
        if(x.bool||y.bool){
            return null;
        }
        else{
            Boolean c=new Boolean();
            c.bool=false;
            c.value=true;
            c.vvalue=x.vvalue+y.vvalue;
            return c;
        }
    }
    public static Boolean sub(Boolean x, Boolean y){
        if(x.bool||y.bool){
            return null;
        }
        else{
            Boolean c=new Boolean();
            c.bool=false;
            c.value=true;
            c.vvalue=x.vvalue-y.vvalue;
            return c;
        }
    }
    public static Boolean div(Boolean x, Boolean y){
        if(x.bool||y.bool){
            return null;
        }
        else{
            Boolean c=new Boolean();
            c.bool=false;
            c.value=true;
            c.vvalue=x.vvalue/y.vvalue;
            return c;
        }
    }
    public static Boolean mul(Boolean x, Boolean y){
        if(x.bool||y.bool){
            return null;
        }
        else{
            Boolean c=new Boolean();
            c.bool=false;
            c.value=true;
            c.vvalue=x.vvalue*y.vvalue;
            return c;
        }
    }
}
