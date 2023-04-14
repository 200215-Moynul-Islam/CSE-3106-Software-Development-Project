package calculator;

import static java.lang.Double.NaN;

public class Generator {
    final static double PI = 3.14159265359;
    public static long factorial(int x){
        long fact=1;
        for(int i=1;i<=x;i++){
            fact*=i;
        }
        return fact;
    }
    public static long combination(int x,int y){
        long comb=1;
        int i,j;
        for(i=x,j=1;j<=y;i--,j++){
            comb*=i;
            comb/=j;
        }
        return comb;
    }
    public static long permutation(int x,int y){
        int perm=1;
        int i,j;
        for(i=x,j=1;j<=y;j++,i--){
            perm*=i;
        }
        return perm;
    }
    public static double power(int x, int y){
        if(y==0){
            return 1;
        }
        if(y<0){
            return ((double) 1/power(x,-y));
        }
        double pow;
        pow=power(x,y/2);
        pow*=pow;
        if(y%2==0){
            return pow;
        }
        pow*=x;
        return pow;
    }
    public static double power(double x, int y){
        if(y==0){
            return 1;
        }
        if(y<0){
            return (1/power(x,-y));
        }
        double pow;
        pow=power(x,y/2)*power(x,y/2);
        if(y%2==0){
            return pow;
        }
        pow*=x;
        return pow;
    }
    public static double exponent(double x){
        if(x<0)
            return (1/exponent(-x));
        double exp=1;
        int i=1000*(int)x+1000;
        for(int j=i;j>=1;j--){
            exp=1+x*exp/j;
        }
        return exp;
    }
    public static double ln(double x){
        double ans=0;
        if(x>0.5){
            int y=(int)(x-1)*1000+2000;
            double num=(x-1)/x;
            double multiple=(x-1)/x;
            for(int i=1;i<y;i++){
                ans+=(multiple/(double)i);
                multiple*=num;
            }
        }
        else{
            double num=x-1;
            double multiple=1-x;
            for(int i=1;i<1000;i++){
                if(i%2==1)
                    ans-=(multiple/(double)i);
                else
                    ans+=(multiple/(double)i);
                multiple*=num;
            }
        }
        return ans;
    }
    public static double power(double m,double n){
        if(m<0&&(long)n!=n){
            return NaN;
        }
        else if((int)n==n){
            return power(m,(int) n);
        }
        double ex=ln(m);
        return exponent(ex*n);
    }
    public static double sinr(double x){
        if(x<0){
            return -sinr(-x);
        }
        int ra=(int) (x/PI*2);
        double angle=x-ra*PI/2;
        double sign=1;
        if(ra%4==1){
            angle=PI/2-angle;
        }
        else if(ra%4==2){
            sign=-1;
        }
        else if(ra%4==3){
            sign=-1;
            angle=PI/2-angle;
        }
        double ans=1;
        for(int i=1000;i>=2;i-=2){
            ans=1-angle*angle*ans/(i*(i+1));
        }
        ans*=angle;
        ans*=sign;
        return ans;
    }
    public static double sind(double x){
        return sinr(x*PI/180.0);
    }
    public static double cosr(double x){
        return sinr(PI/2-x);
    }
    public static double cosd(double x){
        return cosr(x*PI/180);
    }
    public static double tanr(double x){
        return (sinr(x)/cosr(x));
    }
    public static double tand(double x){
        return (sind(x)/cosd(x));
    }
    public static double cotr(double x){
        return (cosr(x)/sinr(x));
    }
    public static double cotd(double x){
        return (cosd(x)/sind(x));
    }
    public static double secr(double x){
        return (1/cosr(x));
    }
    public static double secd(double x){
        return (1/cosd(x));
    }
    public static double cosecr(double x){
        return (1/sinr(x));
    }
    public static double cosecd(double x){
        return (1/sind(x));
    }
    public static double sinh(double x){
        return ((exponent(x)-exponent(-x))/2);
    }
    public static double cosh(double x){
        return ((exponent(x)+exponent(-x))/2);
    }
    public static double tanh(double x){
        return (sinh(x)/cosh(x));
    }
    public static double coth(double x){
        return (1/tanh(x));
    }
    public static double sech(double x){
        return (1/cosh(x));
    }
    public static double cosech(double x){
        return (1/sinh(x));
    }
    public static double log(double base,double x){
        return (ln(x)/ln(base));
    }
    public static double abs(double x){
        if(x>=0){
            return x;
        }
        else{
            return ((-1)*x);
        }
    }
    public static double arcsinr(double x){
        if(x<-1||x>1){
            return NaN;
        }
        double high=1.570796327;
        double low=-1.570796327;
        double mid;
        for(int i=0;i<100;i++){
            mid=low+high;
            mid/=2;
            if(sinr(mid)<x){
                low=mid;
            }
            else{
                high=mid;
            }
        }
        return low;
    }
    public static double arcsind(double x){
        double result=arcsinr(x);
        return result*180/PI;
    }
    public static double arccosr(double x){
        return (3.141592654/2-arcsinr(x));
    }
    public static double arccosd(double x){
        double result=arccosr(x);
        return result*180/PI;
    }
    public static double arctanr(double x){
        double y=Math.sqrt((x*x/(1+x*x)));
        double result=arcsinr(y);
        return result;
    }
    public static double arctand(double x){
        double result=arctanr(x);
        return result*180/PI;
    }
    public static double arccotr(double x){
        return (3.141592654/2-arctanr(x));
    }
    public static double arccotd(double x){
        double result=arccotr(x);
        return result*180/PI;
    }
    public static double arcsecr(double x){
        double result=arccosr(1/x);
        return result;
    }
    public static double arcsecd(double x){
        double result=arccosr(1/x);
        return result*180/PI;
    }
    public static double arccosecr(double x){
        double result=arcsinr(1/x);
        return result;
    }
    public static double arccosecd(double x){
        double result=arcsinr(1/x);
        return result*180/PI;
    }
    public static long fibonacci(int x){
        if(x==0)
            return 0;
        if(x==1)
            return 1;
        long f=1,s=0,fibo=0;
        for(int i=2;i<=x;i++){
            fibo=f+s;
            s=f;
            f=fibo;
        }
        return fibo;
    }
}