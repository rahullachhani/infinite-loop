package com.infiniteloop.hackathon.merchant;

/**
 * Created by parthparekh on 29/11/15.
 */
public class TransactionData {
    public static final String TABLE_NAME="transaction";
    private int Tid;
    private int Uid;
    private int Mid;
    private String Uname;
    private String partnerName;
    private long amount;
    private String status;
    private String date;

    public int getTid(){return Tid;}
    public String getUname(){return Uname;}
    public int getUid(){return Uid;}
    public int getMid(){return Mid;}
    public String getPartnerName(){return partnerName;}
    public long getAmount(){return amount;}
    public String getStatus(){return status;}
    public String getDate(){return date;}

    public void setTid(int Tid){
        this.Tid=Tid;
    }
    public void setUid(int Uid){
        this.Uid=Uid;
    }
    public void setMid(int Mid){
        this.Mid=Mid;
    }
    public void setPartnerName(String partnerName){
        this.partnerName=partnerName;
    }
    public void setAmount(long amount){
        this.amount=amount;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setUname(String Uname){
        this.Uname=Uname;
    }
}
