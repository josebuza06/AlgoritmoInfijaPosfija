import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static boolean Operacion(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int Precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }


    public static String convertirAExpresionPosfija(String expresionInfija) {
        StringBuilder expresionPosfija = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (char token : expresionInfija.toCharArray()) {
            if (token == '(') {
                pila.push(token);
            } else if (token == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    expresionPosfija.append(pila.pop());
                }
                if (!pila.isEmpty() && pila.peek() == '(') {
                    pila.pop();
                }
            } else if (Character.isLetterOrDigit(token)) {
                expresionPosfija.append(token);
            } else if (Operacion(token)) {
                while (!pila.isEmpty() && Precedencia(token) <= Precedencia(pila.peek())) {
                    expresionPosfija.append(pila.pop());
                }
                pila.push(token);
            }
        }

        while (!pila.isEmpty()) {
            if (pila.peek() == '(' || pila.peek() == ')') {
                return "La expresion que pusiste es incorrerca";
            }
            expresionPosfija.append(pila.pop());
        }

        return expresionPosfija.toString();
    }

    public static void main(String[] args) {
        System.out.println("Escribe una expresion: ");
        Scanner sc = new Scanner(System.in);
        String expresion = sc.nextLine();
        String expresionInfija = expresion;
        String expresionPosfija = convertirAExpresionPosfija(expresionInfija);
        System.out.println("Expresión infija: " + expresionInfija);
        System.out.println("Expresión posfija: " + expresionPosfija);
    }
}
