package entity;

/**
 * Created by gao on 15/10/16.
 */
public class User {
    private int uid;
    private String name;
    private String phone;
    private String email;
    private String sfz;

    public User(String name, String phone, String email, String sfz) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sfz = sfz;
    }

    public User() {

    }

    public int getUid() {

        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }
}
