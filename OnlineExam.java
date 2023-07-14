import java.util.Scanner;

public class OnlineExam {
    public static void main(String[] args) {
        //Sample questions-->NAG
        Question[] questions = {
            new Question("What does NAG stand for ?", new String[]{"Never Allow Girls", "No Attitude Guys", "Numerical Algorithms", "Not Always Good"}, 0),
            new Question("What principle is used in Keyboard Setup ?", new String[]{"TWUSGU", "QWERTY", "ASDFGH", "KNAGDO"}, 1),
            new Question("Which Solar Project Is India's no.4 ?", new String[]{"NP Kunta, Andhra Pradesh", "Bhadla Solar Park, Rajasthan", "Kurnool Ultra Mega Solar Park, Andhra Pradesh", "Pavagada Solar Park, Karnataka"}, 0)
        };

        // User profile
        UserProfile userProfile = new UserProfile("Nag@468k", "Nag@5268");

        // Login
        boolean isLoggedIn = login(userProfile);

        if (isLoggedIn) {
            // Update Profile and Password
            updateProfile(userProfile);

            // Selecting answers for MCQs
            selectAnswers(questions);

            // Timer and auto submit
            timer(questions);

            // Closing session and Logout
            logout();
        }
    }

    private static boolean login(UserProfile userProfile) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals(userProfile.getUsername()) && password.equals(userProfile.getPassword())) {
            System.out.println("Login Successful!");
            return true;
        } else {
            System.out.println("Login Failed!");
            return false;
        }
    }

    private static void updateProfile(UserProfile userProfile) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update Profile and Password");
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        userProfile.setName(name);
        userProfile.setPassword(password);

        System.out.println("Profile and Password Updated Successfully!");
    }

    private static void selectAnswers(Question[] questions) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecting answers for MCQs");

        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());

            for (int j = 0; j < question.getOptions().length; j++) {
                System.out.println((j + 1) + ". " + question.getOptions()[j]);
            }

            System.out.print("Enter your answer (1-" + question.getOptions().length + "): ");
            int answer = scanner.nextInt();

            question.setUserAnswer(answer - 1);
        }

        System.out.println("Answers Selected Successfully!");
    }

    private static void timer(Question[] questions) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Timer and auto submit");
        System.out.print("Enter the time in seconds: ");
        int time = scanner.nextInt();

        System.out.println("The exam starts now!");
        System.out.println("Timer started...");

        // Sleep for the given time
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Time's up!");
        System.out.println("Submitting the exam...");

        // Calculate the score
        int score = 0;

        for (Question question : questions) {
            if (question.getUserAnswer() == question.getCorrectAnswer()) {
                score++;
            }
        }

        System.out.println("Exam submitted!");
        System.out.println("Score: " + score + "/" + questions.length);
    }

    private static void logout() {
        System.out.println("Closing session and Logout");
        System.out.println("Logged out successfully!");
    }

    // User profile class
    static class UserProfile {
        private String username;
        private String password;

        public UserProfile(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setName(String name) {
            this.username = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // Question class
    static class Question {
        private String question;
        private String[] options;
        private int correctAnswer;
        private int userAnswer;

        public Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }

        public int getUserAnswer() {
            return userAnswer;
        }

        public void setUserAnswer(int userAnswer) {
            this.userAnswer = userAnswer;
        }
    }
}
