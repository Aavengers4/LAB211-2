/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class RAMItem implements Serializable{
    private String code, type, brand;
    private int bus, quantity;
    private YearMonth  production;
    public boolean Active;

    public RAMItem(String code, String type, int bus, String brand, int quantity, YearMonth production, boolean Active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production = production;
        this.Active = Active;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public YearMonth getProduction() {
        return production;
    }

    public void setProduction(YearMonth production) {
        this.production = production;
    }
@Override    
public String toString() {
    return String.format(
        "| %-10s | %-6s | %-8d | %-10s | %-10d | %-10s |",
        code, type, bus, brand, quantity, production
    );
}
    
    
}
