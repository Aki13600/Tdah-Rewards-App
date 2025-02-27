import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Lesson {
    String text;
    boolean completed;

    Lesson(String text) {
        this.text = text;
        this.completed = false;
    }
}

public class TdahRewardsApp {
    private static ArrayList<Lesson> lessons = new ArrayList<>();
    private static int coins = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nApplication TDAH");
            System.out.println("Pièces gagnées : " + coins + " 🪙");
            displayLessons();
            System.out.println("1. Ajouter une leçon");
            System.out.println("2. Terminer une leçon");
            System.out.println("3. Supprimer les leçons terminées");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addLesson();
                    break;
                case 2:
                    completeLesson();
                    break;
                case 3:
                    removeCompletedLessons();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
    }

    private static void addLesson() {
        System.out.print("Entrez le nom de la leçon : ");
        String lessonText = scanner.nextLine().trim();
        if (!lessonText.isEmpty()) {
            lessons.add(new Lesson(lessonText));
            System.out.println("Leçon ajoutée avec succès !");
        } else {
            System.out.println("Leçon invalide, veuillez réessayer.");
        }
    }

    private static void completeLesson() {
        if (lessons.isEmpty()) {
            System.out.println("Aucune leçon disponible.");
            return;
        }
        System.out.print("Entrez le numéro de la leçon à terminer : ");
        int index = getIntInput() - 1;
        if (index >= 0 && index < lessons.size() && !lessons.get(index).completed) {
            lessons.get(index).completed = true;
            coins += 10;
            System.out.println("Leçon complétée ! Vous avez gagné 10 pièces 🪙");
        } else {
            System.out.println("Numéro invalide ou déjà complété.");
        }
    }

    private static void removeCompletedLessons() {
        lessons.removeIf(lesson -> lesson.completed);
        System.out.println("Les leçons complétées ont été supprimées.");
    }

    private static void displayLessons() {
        if (lessons.isEmpty()) {
            System.out.println("Aucune leçon pour le moment.");
        } else {
            for (int i = 0; i < lessons.size(); i++) {
                System.out.println((i + 1) + ". " + lessons.get(i).text + (lessons.get(i).completed ? " ✅" : ""));
            }
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide, veuillez entrer un nombre.");
                scanner.nextLine();
            }
        }
    }
}
