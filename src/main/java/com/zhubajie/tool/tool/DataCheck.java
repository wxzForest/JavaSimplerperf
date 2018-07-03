package com.zhubajie.tool.tool;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/10.
 */
public class DataCheck {
    public boolean checkisnull(String datastring){
        if(datastring == null){
            return Boolean.FALSE;
        }else {
            datastring = datastring.trim();
            if(datastring.length() == 0){
                return Boolean.FALSE;
            }else if (datastring =="") {
                return Boolean.FALSE;
            }else {
                return Boolean.TRUE;
            }
        }
    }
    public boolean checkisnull(List<Map<String, Object>> lists){
        if(lists == null||lists.size()==0){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
    public String keyuppertodatakey(String key){
        String sqlkey = "";
        for(int i=0;i<key.length();i++){
            char c=key.charAt(i);
            if( Character.isUpperCase(c)){
                sqlkey = sqlkey+"_"+String.valueOf(c).toLowerCase();
            }else {
                sqlkey = sqlkey+String.valueOf(c);
            }
        }
        return sqlkey;
    }
    public Boolean checkequals(Object A ,Object B){
        if(A == null&&B==null){
            return Boolean.TRUE;
        }else if(A!=null&&B!=null){
            if(A.toString().equals(B.toString())){
                return Boolean.TRUE;
            }else {
                return Boolean.FALSE;
            }
        }else {
            return Boolean.FALSE;
        }

    }
    public String getRandomJianHan(int len)
    {
        String ret="";
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try
            {
                str = new String(b, "GBK"); //转成中文
            }
            catch (UnsupportedEncodingException ex)
            {
                ex.printStackTrace();
            }
            ret+=str;
        }
        ret = "Mars"+ret;
        return ret;
    }

    public static void main(String args[]){
        DataCheck dataCheck = new DataCheck();
        String s = " ";
        String key = "pubCategoryId";
        System.out.println(dataCheck.getRandomJianHan(20));
//        System.out.println(dataCheck.keyuppertodatakey(key));
    }
}
