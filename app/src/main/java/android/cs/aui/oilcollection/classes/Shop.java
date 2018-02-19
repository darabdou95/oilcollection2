package android.cs.aui.oilcollection.classes;

import java.io.Serializable;

/**
 * Created by abderrahmanedarhmaoui on 2017-12-26.
 */

public class Shop implements Serializable{
    private String id;
    private String shopname;
    private String phone;
    private String password;
    private String Address;
    private double longitude;
    private double latitude;
    private int total;
    private boolean pending;
    private int barrels;

    public Shop() {
    }



    public Shop(String id, String shopname, String phone, String password, String address, double longitude, double latitude,int barrels) {
        this.id = id;
        this.shopname = shopname;
        this.phone = phone;
        this.password = password;
        Address = address;
        this.barrels = barrels;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pending = false;
        this.total = 0;

    }


    public int getBarrels() {
        return barrels;
    }

    public void setBarrels(int barrels) {
        this.barrels = barrels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}