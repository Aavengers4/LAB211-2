/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import File.FileManager;
import Controler.Inputter;
import Controler.RamManagement;

/**
 *
 * @author ASUS
 */
public class Menu {

    RamManagement rm = new RamManagement();
    FileManager fileManager = new FileManager(); // Tạo đối tượng FileManager
    public void showMenu() {
        System.out.println("====RAM MANAGEMRNT SYSTEM");
        System.out.println("1.Add RAM item");
        System.out.println("2.Search RAM item");
        System.out.println("3.Update RAM Information");
        System.out.println("4.Delete RAM item");
        System.out.println("5.Show all RAM item");
        System.out.println("6.Save to file");
        System.out.println("7.Exit");
    }

    public void menuloop() {
        int choice;
        boolean confirm = false;
        do {
            showMenu();
            System.out.println("Enter your choice : ");
            choice = Inputter.inputInt();
            switch (choice) {
                case 1: {
                    rm.addItem();
            
                    break;
                }
                case 2: {
                    rm.searchRAMItem();
                    break;
                }
                case 3: {
                    rm.updateItem();
                    
                    break;
                }
                case 4: {
                    rm.deleteRAMItem();
                    break;
                }
                case 5: {
                    rm.printList();
                    break;
                }
                case 6: {
                    fileManager.saveToFile("RAModules.dat");
                    break;        
                }
                case 7: {
                    System.out.println("Do you want to exit this system?");
                    confirm = Inputter.confirm();
                    break;
                }
                default:
                    System.out.println("Invalid option, try again.");
            }
        }while(choice !=7);

    }
}
