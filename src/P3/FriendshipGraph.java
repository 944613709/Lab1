package P3;
import java.util.*;
public class FriendshipGraph {
    public List<Person> personList;

    public FriendshipGraph() {
        personList = new ArrayList<>();
    }

    public void addVertex(Person person)
    {
        for(Person p :personList)
        {
            if(p.getName().equals(person.getName()))
            {
                System.out.println("Name repetition");
                System.exit(0);
            }
        }
        personList.add(person);
    }
    public void addEdge(Person p1,Person p2)
    {
        p1.addFriend(p2);
    }


    public static void main(String[] args) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross)); // should print 1
        System.out.println(graph.getDistance(rachel, ben)); // should print 2
        System.out.println(graph.getDistance(rachel, rachel)); // should print 0
        System.out.println(graph.getDistance(rachel, kramer)); // should print -1
    }

    private int getDistance(Person p1, Person p2) {
    }
}
