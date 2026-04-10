import java.util.Scanner;
import java.util.ArrayList;
public class ListMaker {
    public static void main(String[] args) {
        Scanner in = new  Scanner(System.in);
        String input = "";
        int num = 0;
        ArrayList<String> list = new ArrayList<String>();
        boolean done = false;
        do {
            input = SafeInput.getRegExString(in,"Choose from the following options : [A]dd,[D]elete,[I]nsert,[P]rint,[Q]uit","^[AaDdIiPpQq]$");
            switch (input.toUpperCase()) {
                case "A":
                    input = SafeInput.getNonZeroLenString(in,"Enter string to insert");
                    list.add(input);
                    break;
                case "D":
                    num = getListPos(in,"Enter the item to remove", list);
                    list.remove(num);
                    break;
                case "I":
                    num = getListPos(in,"Enter the item to insert", list);
                    input = SafeInput.getNonZeroLenString(in,"Enter string to insert");
                    list.remove(num);
                    list.add(num, input);
                    break;
                case "P":
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
