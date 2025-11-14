public class Test{
    public static void main(String str[]){
        System.out.println("Greetings! Welcome to Java");
        try {
            System.out.println(12/0);
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: Division by zero.");
        }
    }
}
