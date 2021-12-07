package com.gettingstarted.sys.model;

public class User {
     
     private  String uusername;
     private  String password;
     
     public User(String uusername, String password) {
          this.uusername = uusername;
          this.password = password;
     }
     
     public String getUusername() {
          return uusername;
     }
     
     public void setUusername(String uusername) {
          this.uusername = uusername;
     }
     
     public String getPassword() {
          return password;
     }
     
     public void setPassword(String password) {
          this.password = password;
     }
}
