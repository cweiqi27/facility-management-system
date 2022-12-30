package facilitymanagementsystem.entity;


public class User {
    
    private int userId;
    private String name;
    private String gender;
    private String phone;
    
    public User() {
    }

    public User(int userId, String name, String gender, String phone) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        //return userId + "\t\t" + name + "\t" + gender + "\t" + phone;
        return String.format("%-10d %-30s %-10s %-20s", userId, name, gender, phone);
    }
}
