import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
//import file handling stuff
import javax.swing.*;
import java.nio.Buffer;

public class ListMaker {

    public static Path getPath(boolean isSave){
        JFileChooser chooser = new JFileChooser();
        File selectedFile = null;
        File workingDir = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDir);
            int userSelection;
            if (isSave) {
                userSelection = chooser.showSaveDialog(null);
            } else {
                userSelection = chooser.showOpenDialog(null);
            }
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                return selectedFile.toPath();
            } else {return null;}
    }

    public static void fileSaver(Path path, Scanner pipe, ArrayList<String> list){
        boolean done = false;
        File selectedFile = null;
        while(!done) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
                    for (int i = 0; i < list.size(); i++) {
                        writer.write(list.get(i));
                        writer.newLine();
                    }
                    done = true;
                    System.out.println("Data saved to " + path);
                } catch (IOException e) {
                    System.out.println("An error occurred while saving the file: " + e.getMessage());
                    done = !SafeInput.getYNConfirm(pipe, "Do you want to try saving again?");
                } catch (NullPointerException e) {
                    System.out.println("No file selected. Nothing to save.");
                    done = true;
                }
        }
    }

    public static ArrayList<String> fileReader(Path path, Scanner pipe, ArrayList<String> list){
        boolean done = false;
        String line = "";
        while(!done) {
            File selectedFile;
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile = path.toFile()))) {
                System.out.println("Reading data from " + path);
                for (int i = 0; i < selectedFile.length(); i++) {
                    line = reader.readLine();
                    if(line != null) {
                        list.add(line);
                    } else {break;}
                    System.out.println("Read line: " + list.get(i));
                }
                System.out.println("Data successfully loaded from " + path);
                done = true;
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
                done = !SafeInput.getYNConfirm(pipe, "Do you want to try loading again?");
            } catch (NullPointerException e) {
                System.out.println("No file selected. Nothing to load.");
                done = true;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner in = new  Scanner(System.in);
        String input = "";
        int num = 0;
        int num2 = 0;
        boolean needsToBeSaved = false;
        ArrayList<String> list = new ArrayList<String>();
        Path path = null;
        boolean done = false;
        do {
            input = SafeInput.getRegExString(in,"Choose from the following options : [A]dd,[D]elete,[I]nsert,[M]ove,[O]pen,[S]ave,[C]lear,[V]iew,[Q]uit","^[AaDdIiMmOoSsCcVvQq]$");
            switch (input.toUpperCase()) {
                case "A":
                    input = SafeInput.getNonZeroLenString(in,"Enter string to insert");
                    list.add(input);
                    needsToBeSaved = true;
                    break;
                case "D":
                    num = getListPos(in,"Enter the item to remove", list);
                    list.remove(num);
                    needsToBeSaved = true;
                    break;
                case "I":
                    if(!list.isEmpty()) {
                        num = getListPos(in, "Enter the item to insert", list);
                        input = SafeInput.getNonZeroLenString(in, "Enter string to insert");
                        list.remove(num);
                        list.add(num, input);
                        needsToBeSaved = true;
                    } break;
                case "M":
                    if (!list.isEmpty()) {
                        num = getListPos(in, "Enter the item to move", list);
                        num2 = getListPos(in, "Enter the new position", list);
                        if (num < num2) {
                            list.add(num2, list.get(num));

                            list.remove(num);
                        } else {
                            list.add(num2, list.get(num));
                            list.remove(num + 1);
                        }
                        needsToBeSaved = true;
                    }
                case "O":
                    if(needsToBeSaved) {
                       if (SafeInput.getYNConfirm(in, "You have unsaved changes. Do you want to save before opening a new file?")) {
                           path = getPath(true);
                           fileSaver(path, in, list);
                       }
                    }
                    path = getPath(false);
                    list.clear();
                    list = fileReader(path, in, list);
                    break;
                case "S":
                    path = getPath(true);
                    fileSaver(path,in,list);
                    needsToBeSaved = false;
                    break;
                case "C":
                    if(SafeInput.getYNConfirm(in,"Are you sure you want to clear the list?")) {
                        list.clear();
                        needsToBeSaved = true;
                    }
                    break;
                case "V":
                    System.out.println("List:");
                    printList(list);
                    break;
                case "Q":
                    if(needsToBeSaved) {
                        if (SafeInput.getYNConfirm(in, "You have unsaved changes. Do you want to save before quitting?")) {
                            path = getPath(true);
                            fileSaver(path, in, list);
                        }
                    }
                    done = SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
                    break;
            }
        }while(!done);
    }
    public static int getListPos(Scanner pipe, String prompt, ArrayList<String> list) {
        printList(list);
        return SafeInput.getRangedInt(pipe, prompt, 1, list.size()) - 1;
    }
    public static void printList(ArrayList<String> list) {
        for(int i=0;i<list.size();i++) {
            System.out.println("#" + (i + 1) + " " + list.get(i));
        }
    }
}
