
package calculator;


public class constants {
    public static String evaluate(String s){
        String name[]={"pi","e","g"};
        String value[]={"3.141592654","2.718281828","9.81"};
        for(int i=0;i<name.length;i++){
            if(name[i].equals(s)){
                return value[i];
            }
        }
        return "Not Added";
    }
}
