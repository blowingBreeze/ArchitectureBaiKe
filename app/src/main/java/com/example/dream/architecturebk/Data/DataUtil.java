package com.example.dream.architecturebk.Data;

import android.annotation.SuppressLint;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by dream on 2016/12/31.
 */

public class DataUtil
{

    private HttpConnSoap httpConnSoap;
    private ArrayList<String> Parameters;
    private ArrayList<String> ParamValues;
    private InputStream inputStream;
    private String MethodName;

    //调用HttpConnSoap进行网络通信
    private void run()
    {
        inputStream = httpConnSoap.GetWebServer(MethodName, Parameters, ParamValues);
    }

    //通过用户ID获取用户信息
    @SuppressLint("NewApi")
    public UserInfo getUserInfo(int UserID)
    {
        httpConnSoap = new HttpConnSoap();
        Parameters = new ArrayList<String>();
        ParamValues = new ArrayList<String>();

        Parameters.add("UserID");
        ParamValues.add(UserID + "");

        MethodName = Function.SelectUserInfo;
        this.run();

        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseUserInfo(inputStream);
    }

    //通过文章ID获取文章信息
    public TextInfo getTextInfo(int DocumentID)
    {
        httpConnSoap = new HttpConnSoap();
        Parameters = new ArrayList<String>();
        ParamValues = new ArrayList<String>();

        Parameters.add("DocumentID");
        ParamValues.add(DocumentID + "");

        MethodName = Function.SelectDocInfo;
        this.run();

        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseTextInfo(inputStream);
    }

    //通过用户ID获取文章列表
    public ArrayList<TextInfo> getTextInfoByUserID(int UserID)
    {
        httpConnSoap = new HttpConnSoap();
        Parameters = new ArrayList<>();
        ParamValues = new ArrayList<>();

        Parameters.add("UserID");
        ParamValues.add(UserID + "");

        MethodName = Function.SelectTextList;
        this.run();

        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseTextInfoList(inputStream);
    }

    //添加用户
    public boolean addUser(int UserID, String Password)
    {
        httpConnSoap = new HttpConnSoap();
        Parameters = new ArrayList<>();
        ParamValues = new ArrayList<>();

        Parameters.add("UserID");
        Parameters.add("Password");
        ParamValues.add(UserID + "");
        ParamValues.add(Password);

        MethodName = Function.AddUser;
        this.run();

        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseAddUserInfo(inputStream);
    }

    //登录验证
    public boolean login(int UserID, String Password)
    {
        httpConnSoap = new HttpConnSoap();
        Parameters = new ArrayList<>();
        Parameters.add("UserID");
        Parameters.add("Password");
        ParamValues = new ArrayList<>();
        ParamValues.add(UserID + "");
        ParamValues.add(Password);

        MethodName = Function.Login;
        this.run();

        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseLogin(inputStream);
    }

    //添加文章---返回文章ID
    @SuppressLint("NewApi")
    public int addText(int UserID, String Title, String Text)
    {
        httpConnSoap = new HttpConnSoap();

        Parameters = new ArrayList<>();
        Parameters.add("UserID");
        Parameters.add("Title");
        Parameters.add("Text");

        ParamValues = new ArrayList<>();
        ParamValues.add(UserID + "");
        ParamValues.add(Title);
        ParamValues.add(Text);

        MethodName = Function.AddText;
        this.run();


        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseAddText(this.inputStream);
    }

    //删除文章
    public boolean deleteText(int DocumentID)
    {
        httpConnSoap = new HttpConnSoap();
        Parameters = new ArrayList<>();
        Parameters.add("DocumentID");
        ParamValues = new ArrayList<>();
        ParamValues.add(DocumentID + "");

        MethodName = Function.DeleteDocument;
        this.run();

        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseDeleteText(inputStream);
    }

    //修改文章
    public boolean updateText(int DocumentID, String Title, String Text)
    {
        httpConnSoap = new HttpConnSoap();

        Parameters = new ArrayList<>();
        Parameters.add("DocumentID");
        Parameters.add("Title");
        Parameters.add("Text");

        ParamValues = new ArrayList<>();
        ParamValues.add(DocumentID + "");
        ParamValues.add(Title);
        ParamValues.add(Text);

        MethodName = Function.UpdateDocument;
        this.run();

        Parameters.clear();
        ParamValues.clear();

        return httpConnSoap.paraseUpdateText(inputStream);
    }

    //获取文章总数
    public int GetTotalDocNum()
    {
        httpConnSoap = new HttpConnSoap();
        Parameters = new ArrayList<>();
        ParamValues = new ArrayList<>();

        MethodName=Function.GetDocNum;
        this.run();

        Parameters.clear();
        ParamValues.clear();
        return httpConnSoap.paraseGetDocNum(inputStream);
    }
}
