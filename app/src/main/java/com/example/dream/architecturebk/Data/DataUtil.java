package com.example.dream.architecturebk.Data;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by dream on 2016/12/31.
 */

public class DataUtil {

    //通过用户ID获取用户信息
    public UserInfo getUserInfo(int UserID){
        HttpConnSoap httpConnSoap=new HttpConnSoap();
        ArrayList<String> Parameters=new ArrayList<>();
        Parameters.add("UserID");
        ArrayList<String> ParamValues=new ArrayList<>();
        ParamValues.add(UserID+"");
        InputStream inputStream=httpConnSoap.GetWebServer(Function.SelectUserInfo,Parameters,ParamValues);
        return httpConnSoap.paraseUserInfo(inputStream).get(0);
    }

    //通过文章ID获取文章信息
    public TextInfo getTextInfo(int DocumentNum){
        HttpConnSoap httpConnSoap=new HttpConnSoap();
        ArrayList<String> Parameters=new ArrayList<>();
        Parameters.add("DocumentNum");
        ArrayList<String> ParamValues=new ArrayList<>();
        ParamValues.add(DocumentNum+"");
        InputStream inputStream=httpConnSoap.GetWebServer(Function.SelectDocInfo,Parameters,ParamValues);
        return httpConnSoap.paraseTextInfo(inputStream).get(0);
    }

    //通过用户ID获取文章列表
    public ArrayList<TextInfo> getTextInfoByUserID(int UserID){
        HttpConnSoap httpConnSoap=new HttpConnSoap();
        ArrayList<String> Parameters=new ArrayList<>();
        Parameters.add("UserID");
        ArrayList<String> ParamValues=new ArrayList<>();
        ParamValues.add(UserID+"");
        InputStream inputStream=httpConnSoap.GetWebServer(Function.SelectTextList,Parameters,ParamValues);
        return httpConnSoap.paraseTextInfo(inputStream);
    }

    //添加用户
    public boolean addUser(int UserID){

        return true;
    }

    //修改用户文章数量
//    public boolean updateUserDocNum(int UserID,int Document)

    //添加文章
    public int addText(int UserID,String Title,String Text){
        int DocumentID=0;
        return DocumentID;
    }

    //删除文章
    public boolean deleteText(int DocumentID){
        return true;
    }

    //修改文章
    public boolean updateText(int DocumentID,String Title,String Text){
        return true;
    }

    //
}
