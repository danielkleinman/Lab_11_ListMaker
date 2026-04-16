import java.util.Scanner;
import java.util.ArrayList;
public class ListMaker {
    public static void main(String[] args) {
        Scanner in = new  Scanner(System.in);
        String input = "";
        int num = 0;
        int num2 = 0;
        boolean needsToBeSaved = false;
        ArrayList<String> list = new ArrayList<String>();
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

                case "S":

                case "C":

                case "V":
                    System.out.println("List:");
                    printList(list);
                    break;
                case "Q":
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
