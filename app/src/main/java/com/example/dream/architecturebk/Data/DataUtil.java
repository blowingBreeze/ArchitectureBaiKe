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
        HttpConnSoap httpConnSoap=new HttpConnSoap();
        ArrayList<String> Parameters=new ArrayList<>();
        Parameters.add("UserID");
        ArrayList<String> ParamValues=new ArrayList<>();
        ParamValues.add(UserID+"");
        InputStream inputStream=httpConnSoap.GetWebServer(Function.AddUser,Parameters,ParamValues);
//// TODO: 2017/1/3  添加用户的XML解析函数
        return true;
    }

    //修改用户文章数量
//    public boolean updateUserDocNum(int UserID,int Document)

    //添加文章
    public int addText(int UserID,String Title,String Text){
        int DocumentID=0;
        HttpConnSoap httpConnSoap=new HttpConnSoap();

        ArrayList<String> Parameters=new ArrayList<>();
        Parameters.add("UserID");
        Parameters.add("Title");
        Parameters.add("Text");

        ArrayList<String> ParamValues=new ArrayList<>();
        ParamValues.add(UserID+"");
        ParamValues.add(Title);
        ParamValues.add(Text);

        InputStream inputStream=httpConnSoap.GetWebServer(Function.AddText,Parameters,ParamValues);
        //// TODO: 2017/1/3  添加添加文章xml的解析函数
        return DocumentID;
    }

    //删除文章
    public boolean deleteText(int DocumentID){
        HttpConnSoap httpConnSoap=new HttpConnSoap();
        ArrayList<String> Parameters=new ArrayList<>();
        Parameters.add("DocumentID");
        ArrayList<String> ParamValues=new ArrayList<>();
        ParamValues.add(DocumentID+"");
        InputStream inputStream=httpConnSoap.GetWebServer(Function.DeleteDocument,Parameters,ParamValues);
        //// TODO: 2017/1/3 删除文章的XML解析函数
        return true;
    }

    //修改文章
    public boolean updateText(int DocumentID,String Title,String Text){
        HttpConnSoap httpConnSoap=new HttpConnSoap();
        
        ArrayList<String> Parameters=new ArrayList<>();
        Parameters.add("DocumentID");
        Parameters.add("Title");
        Parameters.add("Text");
        
        ArrayList<String> ParamValues=new ArrayList<>();
        ParamValues.add(DocumentID+"");
        ParamValues.add(Title);
        ParamValues.add(Text);
        InputStream inputStream=httpConnSoap.GetWebServer(Function.AddText,Parameters,ParamValues);
        //// TODO: 2017/1/3 修改文章的XML解析函数 
        return true;
    }

    //
}
