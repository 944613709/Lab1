package test.P3;

import P3.FriendshipGraph;
import org.junit.jupiter.api.Test;
import P3.Person;
import P3.FriendshipGraph;
import static org.junit.jupiter.api.Assertions.*;

class FriendshipGraphTest{

    @Test
    void addVertexTest() {
        FriendshipGraph graph = new FriendshipGraph();
        Person person1 = new Person("jack");
        Person person2 = new Person("tom");
        Person person3 = new Person("Lily");
        Person person4 = new Person("LiNa");
        Person person5 = new Person("LiMing");
        Person person6 = new Person("LiHong");
        graph.addVertex(person1);
        graph.addVertex(person2);
        graph.addVertex(person3);
        graph.addVertex(person4);
        graph.addVertex(person5);
        graph.addVertex(person6);
        assertEquals(person1,graph.personList.get(0));
        assertEquals(person2,graph.personList.get(1));
        assertEquals(person3,graph.personList.get(2));
        assertEquals(person4,graph.personList.get(3));
        assertEquals(person5,graph.personList.get(4));
        assertEquals(person6,graph.personList.get(5));
    }

    @Test
    void addEdgeTest() {
        FriendshipGraph graph = new FriendshipGraph();
        Person person1 = new Person("jack");
        Person person2 = new Person("tom");
        Person person3 = new Person("Lily");
        Person person4 = new Person("LiNa");
        Person person5 = new Person("LiMing");
        Person person6 = new Person("LiHong");
        graph.addVertex(person1);
        graph.addVertex(person2);
        graph.addVertex(person3);
        graph.addVertex(person4);
        graph.addVertex(person5);
        graph.addVertex(person6);
        graph.addEdge(person1,person2);
        graph.addEdge(person1,person3);
        graph.addEdge(person2,person4);
        graph.addEdge(person2,person5);
        graph.addEdge(person3,person4);
        graph.addEdge(person4,person5);
        //有向图底层，我需要设计双向
        graph.addEdge(person2,person1);
        graph.addEdge(person3,person1);
        graph.addEdge(person4,person2);
        graph.addEdge(person5,person2);
        graph.addEdge(person4,person3);
        graph.addEdge(person6,person4);
        assertEquals(true,person1.getFriend().contains(person2)&&person1.getFriend().contains(person3));
        assertEquals(false,person1.getFriend().contains(person6));
        assertEquals(true,person2.getFriend().contains(person1)&&person2.getFriend().contains(person4)&&person2.getFriend().contains(person5));
    }

    @Test
    void getDistanceTest() {
        FriendshipGraph graph = new FriendshipGraph();
        Person person1 = new Person("jack");
        Person person2 = new Person("tom");
        Person person3 = new Person("Lily");
        Person person4 = new Person("LiNa");
        Person person5 = new Person("LiMing");
        Person person6 = new Person("LiHong");
        graph.addVertex(person1);
        graph.addVertex(person2);
        graph.addVertex(person3);
        graph.addVertex(person4);
        graph.addVertex(person5);
        graph.addVertex(person6);
        graph.addEdge(person1,person2);
        graph.addEdge(person1,person3);
        graph.addEdge(person2,person4);
        graph.addEdge(person2,person5);
        graph.addEdge(person3,person4);
        graph.addEdge(person4,person5);

        graph.addEdge(person2,person1);
        graph.addEdge(person3,person1);
        graph.addEdge(person4,person2);
        graph.addEdge(person5,person2);
        graph.addEdge(person4,person3);
        graph.addEdge(person6,person4);
        assertEquals(1,graph.getDistance(person1,person2));
        assertEquals(1,graph.getDistance(person2,person1));
        assertEquals(2,graph.getDistance(person1,person5));
        assertEquals(0,graph.getDistance(person1,person1));
        assertEquals(-1,graph.getDistance(person5,person6));
    }

}