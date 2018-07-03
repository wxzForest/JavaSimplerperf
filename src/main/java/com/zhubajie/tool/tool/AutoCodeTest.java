package com.zhubajie.tool.tool;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/3/27.
 */
public class AutoCodeTest {

    public void autoCode(Class clazz){
        String allname = clazz.getName();
        String[] classnames = allname.split("\\.");
        String classname = classnames[classnames.length-1].substring(0,1).toLowerCase()+classnames[classnames.length-1].substring(1);
        Field[] field = clazz.getDeclaredFields();
        for (int i=0 ;i<field.length;i++){
            System.out.println(field[i].getName());
        }
        Method[] method = clazz.getDeclaredMethods();
        for (Method m: method){
            if (m.getName().startsWith("set")){
                Class[] classparam = m.getParameterTypes();
                String types = classparam[0].getName().split("\\.")[classparam[0].getName().split("\\.").length-1];
                String methodname = m.getName();
                String fieldname = methodname.substring(3,4).toLowerCase()+methodname.substring(4);
                System.out.println("if (dataCheck.checkisnull(param.get(\""+fieldname+"\"))){");
                if (types.equals("BigDecimal")){
                    System.out.println("\t"+classname+"."+methodname+"(" + types + ".valueOf(Double.valueOf(param.get(\""+fieldname+"\"))));");
                }else if(types.equals("String")){
                    System.out.println("\t"+classname+"."+methodname+"(param.get(\""+fieldname+"\"));");
                }else {
                    System.out.println("\t"+classname+"."+methodname+"(" + types + ".valueOf(param.get(\""+fieldname+"\")));");
                }
                System.out.println("}");
            }
        }
    }


    public void manualCode(Class clazz){
        String classnameb = clazz.getName().split("\\.")[clazz.getName().split("\\.").length-1];
        String classnamel = classnameb.substring(0,1).toLowerCase()+classnameb.substring(1);
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println(classnameb + " " + classnamel +" = new " + classnameb + "();");
        for (Method method : methods){
            String methodname = method.getName();
//            Class[] classparam = method.getParameterTypes();
//            for (Class clp:classparam){
//                System.out.println(methodname + "=====" + clp.getName());
//            }
            if (methodname.startsWith("set")){
                Class[] classparam = method.getParameterTypes();
                String types = classparam[0].getName().split("\\.")[classparam[0].getName().split("\\.").length-1];
                System.out.println(classnamel+"."+methodname+"(" + types + ".valueOf(0));");
            }
        }
        System.out.println("request.setData(" + classnamel + ");");
    }
}
