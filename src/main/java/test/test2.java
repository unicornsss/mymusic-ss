package test;
/**
 * Created by 羊 on 2018/11/13.
 */
public class test2 {
        public static void main (String [] str)
        {
            final int num = 20;

            int Random[] = new int[num];
            for(int i = 0 ; i < num ; i++) {   // int ran=-1;
                while(true) {
                    int ran = (int)(num*Math.random());
                    for(int j = 0 ; j < i ; j++) {
                        if(Random[j] == ran) {
                            ran = -1;
                            break;
                        }
                    }
                    if(ran != -1) {
                        Random[i] = ran;
                        break;
                    }
                }
            }
            for(int i = 0 ; i < 4 ; i ++){
                System.out.print(Random[i]+",");
            }
        }
}
