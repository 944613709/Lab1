package P3;
import java.util.*;
public class Person {
    private String name;
    private Boolean visited;
    private Set<Person> friend;
    private int distance;

    public Person(String name) {
        this.name = name;
        this.visited = false;
        this.friend = new HashSet<>();
        this.distance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Set<Person> getFriend() {
        return friend;
    }

    public void setFriend(Set<Person> friend) {
        this.friend = friend;
    }
    public void addFriend(Person person){
        friend.add(person);
    }
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
