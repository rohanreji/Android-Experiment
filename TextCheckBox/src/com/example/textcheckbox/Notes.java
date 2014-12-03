



package com.example.textcheckbox;

public class Notes {
    
    
    int _uuid;
    String _note;
   
     
    
    public Notes(){
         
    }
   
    public Notes(int id, String name){
        this._uuid = id;
        this._note = name;
       
    }
     
    
    public Notes(String name){
        this._note = name;
       
    }
   
    public int getID(){
        return this._uuid;
    }
     
  
    public void setID(int id){
        this._uuid = id;
    }
     
    
    public String getNote(){
        return this._note;
    }
     
    
    public void setName(String name){
        this._note = name;
    }
     
   
}