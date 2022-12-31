
package facilitymanagementsystem.entity;


public class Admin extends Facility{
    private String maintainence;
    private int adminID;
    private String role;
    private String adminName;
    private String gender;
    
    public Admin(){
        
    }
    
    public Admin(int adminID, String adminName, String gender, String role){
        this.adminID = adminID;
        this.adminName = adminName;
        this.gender = gender;
        this.role = role;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Admin(String maintainence, int facID, String facName, String facType, String facVenue){
        super(facID, facName, facType, facVenue);
        this.maintainence = maintainence;
       
    }
    
    public String getMaintainence(){
        return maintainence;
    }
    
    public void setMaintainence(String maintainence){
        this.maintainence = maintainence;
    }

    @Override
    public String toString() {
        return "Admin{" + "maintainence=" + maintainence + '}';
    }
    
    public String maintainVenue(){
        return super.getfacVenue() + this.maintainence;
    }
}
