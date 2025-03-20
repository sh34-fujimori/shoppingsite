package model;

public class Users {
    private String usersId;
    private String password;
    private String usersName;
    private String usersPostcode;
    private String usersAddress;
    private String usersTel;
    private String usersMail;
    private String usersPay;
    private int usersKey;

    public Users() {
    }
    
    public Users(int usersKey) {
    	this.usersKey = usersKey;
    }

    public Users(String usersId, String password) {
        this.usersId = usersId;
        this.password = password;
    }
    
    public Users(String usersId, String password, String usersName, String usersPostcode, String usersAddress, String usersTel, String usersMail, String usersPay) {
        this.usersId = usersId;
        this.password = password;
        this.usersName = usersName;
        this.usersPostcode = usersPostcode;
        this.usersAddress = usersAddress;
        this.usersTel = usersTel;
        this.usersMail = usersMail;
        this.usersPay = usersPay;
    }

    public String getUsersId() {
        return usersId;
    }
    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsersName() {
        return usersName;
    }
    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }
    public String getUsersPostcode() {
        return usersPostcode;
    }
    public void setUsersPostcode(String usersPostcode) {
        this.usersPostcode = usersPostcode;
    }
    public String getUsersAddress() {
        return usersAddress;
    }
    public void setUsersAddress(String usersAddress) {
        this.usersAddress = usersAddress;
    }
    public String getUsersTel() {
        return usersTel;
    }
    public void setUsersTel(String usersTel) {
        this.usersTel = usersTel;
    }
    public String getUsersMail() {
        return usersMail;
    }
    public void setUsersMail(String usersMail) {
        this.usersMail = usersMail;
    }
    public String getUsersPay() {
        return usersPay;
    }
    public void setUsersPay(String usersPay) {
        this.usersPay = usersPay;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersId='" + usersId + '\'' +
                ", password='" + password + '\'' +
                ", usersName='" + usersName + '\'' +
                ", usersPostcode='" + usersPostcode + '\'' +
                ", usersAddress='" + usersAddress + '\'' +
                ", usersTel='" + usersTel + '\'' +
                ", usersMail='" + usersMail + '\'' +
                ", usersPay='" + usersPay + '\'' +
                '}';
    }
    public int getUsersKey() {
        return usersKey;
    }
}
