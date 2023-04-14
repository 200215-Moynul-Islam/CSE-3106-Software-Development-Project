package calculator;
import java.lang.Math;

public class Vector {
    double x=0;
    double y=0;
    double z=0;
    boolean vec=true;
    boolean scalar=false;
    double value=0;
    public static Vector add(Vector a, Vector b){
        Vector c=new Vector();
        if(a.scalar&&b.scalar){
            c.scalar=true;
            c.vec=false;
            c.value=a.value+b.value;
            return c;
        }
        else if(a.vec&&b.vec){
            c.x=a.x+b.x;
            c.y=a.y+b.y;
            c.z=a.z+b.z;
            return c;
        }
        return null;
    }
    public static Vector sub(Vector a, Vector b){
        Vector c=new Vector();
        if(a.scalar&&b.scalar){
            c.scalar=true;
            c.vec=false;
            c.value=a.value-b.value;
            return c;
        }
        else if(a.vec&&b.vec){
            c.x=a.x-b.x;
            c.y=a.y-b.y;
            c.z=a.z-b.z;
            return c;
        }
        return null;
    }
    public static Vector dot(Vector a, Vector b){
        System.out.println(a.x+" "+a.y+" "+a.z+" "+b.x+" "+b.y+" "+b.z);
        Vector c=new Vector();
        c.scalar=true;
        c.vec=false;
        c.value=(a.x*b.x)+(a.y*b.y)+(a.z*b.z);
        return c;
    }
    public static Vector cross(Vector a, Vector b){
        Vector c=new Vector();
        c.x=(a.y*b.z-b.y*a.z);
        c.y=(b.x*a.z-a.x*b.z);
        c.z=(a.x*b.y-a.y*b.x);
        return c;
    }
    public static Vector abs(Vector a){
        Vector c=new Vector();
        c.scalar=true;
        c.vec=false;
        c.value=Math.sqrt(a.x*a.x+a.y*a.y+a.z*a.z);
        return c;
    }
    public static Vector multiply(Vector a, Vector b){
        Vector c=new Vector();
        if(a.scalar&&b.scalar){
            c.scalar=true;
            c.vec=false;
            c.value=a.value*b.value;
        }
        else if(a.scalar){
            c.x=b.x*a.value;
            c.y=b.y*a.value;
            c.z=b.z*a.value;
        }
        else{
            c.x=a.x*b.value;
            c.y=a.y*b.value;
            c.z=a.z*b.value;
        }
        return c;  
    }
    public static Vector divide(Vector a, Vector b){
        Vector c=new Vector();
        if(a.scalar&&b.scalar){
            c.scalar=true;
            c.vec=false;
            c.value=a.value/b.value;
        }
        else if(b.scalar){
            c.x=a.x*b.value;
            c.y=a.y*b.value;
            c.z=a.z*b.value;
        }
        return c;  
    }
    public static Vector unit(Vector a){
        Vector c=new Vector();
        Vector b=abs(a);
        System.out.println(b.value);
        c.x=a.x/b.value;
        c.y=a.y/b.value;
        c.z=a.z/b.value;
        return c;
    }
    public static Vector angler(Vector a, Vector b){
        Vector c = new Vector();
        c.vec=false;
        c.scalar=true;
        Vector d=dot(a,b);
        double value=d.value;
        d=abs(a);
        value/=d.value;
        d=abs(b);
        value/=d.value;
        c.value=Generator.arccosr(value);
        return c;
    }
    public static Vector angled(Vector a, Vector b){
        Vector c=angler(a,b);
        c.value=c.value*180/3.14159265359;
        return c;
    }
}
