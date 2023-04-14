
package calculator;
import java.lang.*;
public class ComplexNumber {
    double real;
    double imazinary;
    public static ComplexNumber add(ComplexNumber a, ComplexNumber b){
        ComplexNumber c= new ComplexNumber();
        c.real=a.real+b.real;
        c.imazinary=a.imazinary+b.imazinary;
        return c;
    }
    public static ComplexNumber sub(ComplexNumber a, ComplexNumber b){
        ComplexNumber c= new ComplexNumber();
        c.real=a.real-b.real;
        c.imazinary=a.imazinary-b.imazinary;
        return c;
    }
    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b){
        ComplexNumber c= new ComplexNumber();
        c.real=a.real*b.real-a.imazinary*b.imazinary;
        c.imazinary=a.imazinary*b.real+b.imazinary*a.real;
        return c;
    }
    public static ComplexNumber divide(ComplexNumber a, ComplexNumber b){
        ComplexNumber c= new ComplexNumber();
        c.real=(a.real*b.real+a.imazinary*b.imazinary)/(b.real*b.real+b.imazinary*b.imazinary);
        c.imazinary=(b.real*a.imazinary-a.real*b.imazinary)/(b.real*b.real+b.imazinary*b.imazinary);
        return c;
    }
    public static ComplexNumber mod(ComplexNumber a){
        ComplexNumber c=new ComplexNumber();
        c.imazinary=0;
        c.real=Math.sqrt(a.real*a.real+a.imazinary*a.imazinary);
        return c;
    }
    public static ComplexNumber argr(ComplexNumber a){
        ComplexNumber c=new ComplexNumber();
        c.imazinary=0;
        double x=a.real;
        double y=a.imazinary;
        if(x<0&&y<0){
            c.real=-3.141592654+Generator.arctanr(y/x);
        }
        else if(x<0){
            c.real=3.141592654-Generator.arctanr(-y/x);
        }
        else if(y<0){
            c.real=-Generator.arctanr(-y/x);
        }
        else{
            c.real=Generator.arctanr(y/x);
        }
        return c;
    }
    public static ComplexNumber argd(ComplexNumber a){
        ComplexNumber c;
        c=argr(a);
        c.real=c.real*180/3.141592654;
        return c;
    }
}
