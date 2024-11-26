
package models;
//NV (
//    staff_id char(20) primary key,         
//    staff_name nvarchar(30) not null,    
//    stat tinyint(1) not null,             
//    acc char(20) not null unique,           
//    pass char(255) not null                 
//);
public class Staff {
    private String staffId;    
    private String staffName; 
    private boolean stat;      
    private String acc;       
    private String pass;   

    public Staff() {
    }

    public Staff(String staffId, String staffName, boolean stat, String acc, String pass) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.stat = stat;
        this.acc = acc;
        this.pass = pass;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public boolean isStat() {
        return stat;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", stat=" + stat +
                ", acc='" + acc + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
