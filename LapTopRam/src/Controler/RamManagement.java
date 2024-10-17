/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import File.FileManager;
import static Controler.Inputter.confirm;
import static Controler.RamManagement.listRam;
import Model.RAMItem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class RamManagement implements I_RamManagement {
    
    public static List<RAMItem> listRam = new ArrayList<>();
    
    public RamManagement() {
        listRam = FileManager.loadFromFile("RAModules.dat");
        if (listRam == null) {
            listRam = new ArrayList<>();
        }
    }
    
    public String generateCode(String type) {
        boolean isExist;
        String prefix = "RAM";
        String code;
        int index = 1;
        do {
            code = prefix + type + "_" + index; // code ram 
            isExist = false;
            for (RAMItem item : listRam) {
                if (item.getCode().equalsIgnoreCase(code)) {
                    index++; //tạo mã code khác 
                    isExist = true;
                    break;
                }
            }
        } while (isExist);
        return code;
    }
    
    @Override
    public void addItem() {
        do {
            
            System.out.println("Enter RAM type: ");
            String type = Inputter.inputNonEmptyString();
            type = type.toUpperCase();
            
            System.out.println("Enter Ram Bus");
            int bus = Inputter.inputRange(1, 10000);
            System.out.println("Enter RAM Brand: ");
            String brand = Inputter.inputNonEmptyString();
            System.out.println("Enter RAM quantity: ");
            int quantity = Inputter.inputRange(1, 10000);
            
            System.out.println("Enter Year Month: ");
            YearMonth yearMonth = Inputter.inputDate();
            
            System.out.println("Do you want add RAM item ???");
            if (confirm()) {
                RAMItem ram = new RAMItem(generateCode(type), type, bus, brand, quantity, yearMonth, true);
                listRam.add(ram);
                System.out.println("Create sussess");
            } else {
                System.out.println("Action discarded.");
            }
            System.out.println("Do you want add another product ???");
            
        } while (confirm());
        
    }
    
    @Override
    public void searchRAMItem() {
        if (listRam.isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        
        String keyword;
        int value;
        int choice;
        
        boolean continueSearch = true;
        while (continueSearch) {
            System.out.println("1.Search by Type");
            System.out.println("2.Search by Bus");
            System.out.println("3.Search by Brand");
            System.out.println("4.Return");
            System.out.println("Enter your choice: ");
            choice = Inputter.inputInt();
            switch (choice) {
                case 1: {
                    System.out.println("Enter RamType to search: ");
                    keyword = Inputter.inputNonEmptyString().trim();
                    
                    List<RAMItem> searchResults = new ArrayList<>();
                    for (RAMItem x : listRam) {
                        if (x.getType().equalsIgnoreCase(keyword)) {
                            searchResults.add(x);
                        }
                    }
                    if (searchResults.isEmpty()) {
                        System.out.println("Have no any RAM Type");
                    } else {
                        System.out.println("| Code       | Type   | Bus      | Brand      | Quantity | Production |");
                        System.out.println("-----------------------------------------------------------------------");
                        for (RAMItem x : searchResults) {
                            System.out.println(x);
                        }
                    }
                    break;
                    
                }
                case 2: {
                    System.out.println("Enter Bus to search:");
                    value = Inputter.inputInt();
                    List<RAMItem> searchResults = new ArrayList<>();
                    for (RAMItem x : listRam) {
                        if (x.getBus() == value) {
                            searchResults.add(x);
                        }
                    }
                    if (searchResults.isEmpty()) {
                        System.out.println("Have no any RAM bus");
                    } else {
                        System.out.println("| Code       | Type   | Bus      | Brand      | Quantity | Production|");
                        System.out.println("----------------------------------------------------------------------");
                        for (RAMItem x : searchResults) {
                            System.out.println(x);
                        }
                    }
                    break;
                }
                
                case 3: {
                    System.out.println("Enter Brand to search:");
                    keyword = Inputter.inputNonEmptyString().trim();
                    List<RAMItem> searchResults = new ArrayList<>();
                    for (RAMItem x : listRam) {
                        if (x.getBrand().equalsIgnoreCase(keyword)) {
                            searchResults.add(x);
                        }
                    }
                    if (searchResults.isEmpty()) {
                        System.out.println("Have no any RAM brand");
                    } else {
                        System.out.println("| Code       | Type   | Bus      | Brand      | Quantity | Production| ");
                        System.out.println("-----------------------------------------------------------------------");
                        for (RAMItem x : searchResults) {
                            System.out.println(x);
                        }
                    }
                    break;
                }
                
                case 4: {
                    System.out.println("Return the menu");
                    continueSearch = false;
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please try again");
                    break;
                }
            }
            if (continueSearch) {
                System.out.println("Do you want to search again? (Yes/No): ");
                continueSearch = confirm();
            }
        }
    }
    
    @Override
    public void updateItem() {
        if (listRam.isEmpty()) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
            return;
        }
        
        boolean confirm;
        String code, type, brand;
        int bus, quantity;
        YearMonth production;
        String a = " (leave blank to retain old data): ";
        
        do {
            RAMItem ramToUpdate = null;

            // Nhập mã RAM
            System.out.println("Enter Ramcode to update");
            code = Inputter.inputString();
            for (RAMItem ram : listRam) {
                if (ram.getCode().equalsIgnoreCase(code) && ram.Active) {
                    ramToUpdate = ram;
                    break;
                }
            }
            
            if (ramToUpdate == null) {
                System.out.println("Item code does not exist, return to menu ");
                return;
            }
            
            System.out.println("Enter type " + a);
            type = Inputter.inputString();
            if (!type.isEmpty()) {
                ramToUpdate.setType(type.toUpperCase());
                String newCode = generateCode(type);
                ramToUpdate.setCode(newCode);
            }
            
            System.out.println("Enter brand " + a);
            brand = Inputter.inputString();
            if (!brand.isEmpty()) {
                ramToUpdate.setBrand(brand.toUpperCase());
            }
            
            System.out.println("Enter RAM bus speed " + a);
            bus = Inputter.inputRangeOrBlank(1, 100000, ramToUpdate.getBus());
            if (bus != -1) {
                ramToUpdate.setBus(bus);
            }
            
            System.out.println("Enter new RAM's quantity " + a);
            quantity = Inputter.inputRangeOrBlank(1, 10000, ramToUpdate.getQuantity());
            if (quantity != -1) {
                ramToUpdate.setQuantity(quantity);
            }
            
            System.out.println("Enter new RAM's production date: " + a);
            production = Inputter.inputDate();
            if (production != null) {
                ramToUpdate.setProduction(production);
            }
            
            System.out.println("Do you want to update this item?");
            confirm = Inputter.confirm();
            if (confirm) {
                
                System.out.println("Item updated successfully.");
            } else {
                System.out.println("Action discarded.");
            }
            
            System.out.println("Do you want to update another item?");
            confirm = Inputter.confirm();
            
        } while (confirm);
    }
    
    @Override
    public void deleteRAMItem() {
        if (listRam.isEmpty()) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
            return;
        }
        boolean confirm, isExist;
        String code;
        do {
            isExist = false;
            for (RAMItem ram : listRam) {
                if (ram.Active) {
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("RAM list is currently empty. Returning to main menu...");
                return;
            }
            System.out.println("Enter Ram code to delete");
            code = Inputter.inputNonEmptyString().trim();
            isExist = false;
            for (RAMItem item : listRam) {
                if (item.getCode().equalsIgnoreCase(code) && item.isActive()) {
                    isExist = true;
                    System.out.println("Do you want to delete this item?");
                    confirm = Inputter.confirm();
                    if (confirm) {
                        item.setActive(false);
                        System.out.println("Item deleted successfully");
                        break;
                    } else {
                        System.out.println("Action discarded.");
                        break;
                    }
                }
            }
            if (!isExist) {
                System.out.println("This item code does not exist.");
            }
            System.out.println("Do you want to delete another item?");
            confirm = Inputter.confirm();
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }
    
    public void printList() {
        if (listRam.isEmpty()) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
            return;
        }
        // In tiêu đề bảng
        System.out.println("| Code       | Type   | Bus      | Brand      | Quantity   | Production |");
        System.out.println("---------------------------------------------------------------------");
        
        int count = 0;
        boolean isFound = false;
        for (RAMItem item : listRam) {
            if (item.Active) {
                isFound = true;
                count++;
                System.out.println(item);
            }
            
        }
        if (!isFound) {
            System.out.println("RAM list is currently empty. Returning to main menu...");
        } else {
            System.out.println("Total: " + count + " item(s).");
        }
    }
    
    public void sort() {
        Comparator<RAMItem> Ramsort = new Comparator<RAMItem>() {
            @Override
            public int compare(RAMItem a, RAMItem b) {
                int sortbytype = a.getType().compareToIgnoreCase(b.getType());
                if (sortbytype != 0) {
                    return sortbytype;
                }
                int sortByBus = Integer.compare(a.getBus(), b.getBus());
                if (sortByBus != 0) {
                    return sortByBus;
                }
                int sortByBrand = a.getBrand().compareToIgnoreCase(b.getBrand());
                return sortByBrand;
            }
        };
        Collections.sort(listRam, Ramsort);
    }
    
}
