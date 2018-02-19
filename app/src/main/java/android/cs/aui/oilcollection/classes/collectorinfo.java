package android.cs.aui.oilcollection.classes;

/**
 * Created by abderrahmanedarhmaoui on 2018-01-03.
 */

public class collectorinfo {
    private String id;
    private String name;
    private String phonenum;
    private String address;

    public collectorinfo (){

    }
    public collectorinfo(String id, String name, String phonenum, String address) {
        this.id = id;
        this.name = name;
        this.phonenum = phonenum;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}