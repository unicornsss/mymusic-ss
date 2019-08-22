package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 羊 on 2018/11/13.
 */
public class test1 {
//    List<Integer> list = new ArrayList<Integer>();
//    ArrayList arrayList = {1,2,3,4};
public static void main(String[] args) {
    List<Integer> xxx = new ArrayList<Integer>();
    xxx.add(1);
    xxx.add(2);
    xxx.add(24);
    xxx.add(21);
    xxx.add(25);
//    int[] list = {1,2,3,4};
    System.out.println(xxx);
//    System.out.println(xxx.toString());
    String aa = xxx.toString();
    System.out.println(aa);
    System.out.println(aa.getClass());
//    aa2 = aa.replaceAll("\\]", "");
//    String[] s = str.split("\\,");
    String aa2 =aa.replaceAll("\\[", "").replaceAll("\\]", "");
    System.out.println(aa2);
    String[] s = aa2.split(", ");
    List<Integer> aa4 = new ArrayList<Integer>();
    for (String s1 : s) {
        System.out.println(s1);
        aa4.add(Integer.parseInt(s1));
    }
    System.out.println(aa4);
    }
}
