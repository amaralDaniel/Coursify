import java.util.Scanner;

public class MailingListAdministration {
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        String course;
        String email;

        System.out.print("" +
                "1-Subscribe\n" +
                "2-Unsubscribe\n" +
                "3-List subscribers\n" +
                "0-Exit\n" +
                "Option: ");
        option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println("Course:");
                course = sc.next();
                System.out.println("Subscriber e-mail: ");
                email = sc.next();
                break;
            case 2:
                System.out.println("Course:");
                course = sc.next();
                System.out.println("Subscriber e-mail: ");
                email = sc.next();
            case 3:
                System.out.println("Course:" +
                        //"" + courseName
                        "");
                //for(int i; i < courses.)
                break;
            case 0:
                System.out.println("Exiting...\n");
                return;
        }
    }
}
