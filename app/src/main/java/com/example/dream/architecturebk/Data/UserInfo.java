package com.example.dream.architecturebk.Data;

/**
 * Created by dream on 2016/12/31.
 */

public class UserInfo {
    private int UserID;
    private int DocumentNum;

    public int getUserID(){
        return this.UserID;
    }
    public int getDocumentNum(){
        return this.DocumentNum;
    }
    void setUserID(int userID){
        this.UserID=userID;
    }
    void setDocumentNum(int documentNum){
        this.DocumentNum=documentNum;
    }
}
