import apsd.classes.containers.deqs.WStack;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.containers.collections.abstractcollections.WOrderedSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TEST MANUALE LIBRERIA APSD ===");

        // 1. Test Pila (LIFO) su VList
        WStack<String> stack = new WStack<>(new VList<>());
        stack.Push("Primo");
        stack.Push("Secondo");
        stack.Push("Terzo");
        System.out.println("Top della pila: " + stack.Top()); // "Terzo"
        System.out.println("Pop: " + stack.TopNPop()); // Estrae "Terzo"

        // 2. Test Insieme Ordinato con Ricerca Predecessore/Successore
        WOrderedSet<Integer> set = new WOrderedSet<>();
        set.Insert(10);
        set.Insert(50);
        set.Insert(25);
        set.Insert(5);

        System.out.println("Minimo insieme: " + set.Min()); // 5
        System.out.println("Massimo insieme: " + set.Max()); // 50
        System.out.println("Predecessore di 25: " + set.Predecessor(25)); // 10
        System.out.println("Successore di 25: " + set.Successor(25)); // 50
    }
}