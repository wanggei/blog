package com.example.blog.unitl;

import com.example.blog.model.Blog;
import com.example.blog.model.Tages;

import java.util.ArrayList;
import java.util.List;

public class TageUntis {

    public static String division(List<Tages> tages){
        String [] s=new String[tages.size()];
        for (int i=0;i<tages.size();i++ ){
           s[i]=String.valueOf(tages.get(i).getId());
        }
        StringBuffer tagesStr=new StringBuffer();
        for (int i=0;i<s.length;i++){
            if (i==s.length-1){
                tagesStr.append(s[i]);
            }else {
                tagesStr.append(s[i]+",");
            }

        }
        return tagesStr.toString();
    }
    public static List<Tages> StringTurnList(String TagesId){

        String [] tages=TagesId.split(",");
        List<Tages> tagesList=new ArrayList<>();
        for (String s:tages){
            Tages tages1=new Tages();
            tages1.setId(Integer.valueOf(s));
            tagesList.add(tages1);
        }
        return tagesList;
    }


}
