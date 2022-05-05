package P3;

import java.util.*;

public class FriendshipGraph{
    public List<Person> personList;
    public FriendshipGraph(){
        personList = new ArrayList<>();
    }

    /**
     *
     * @param person 要加入的personVertex
     */
    public void addVertex(Person person) {
        for(Person p:personList) {
            if(p.getName().equals(person.getName())) {
                System.out.println("已经添加过该person");
                System.exit(0);
            }
        }//检查是否以及添加过
        personList.add(person);
    }

    /**
     * 加入Edge边:person1->person2
     * @param person1 起点
     * @param person2   终点
     */
    public void addEdge(Person person1, Person person2) {
        person1.addFriend(person2);
    }

    /**
     * 通过BFS计算person1到person2的最短距离
     * @param person1
     * @param person2
     * @return person1到person2的距离
     */
    public int getDistance(Person person1, Person person2) {
        int distance = 0;
        if(person1 == person2) //对于到自己的距离为0
            return distance;
        for(int i=0; i<personList.size(); i++)
        {
            personList.get(i).setVisited(false);
        }
        //进行BFS搜索前初始化
        //找到最短距离p1->p2
        Queue<Person> queue = new LinkedList<>();
        queue.offer(person1);
        while(!queue.isEmpty()) {
            //扫描这一圈
            //将外围一圈加入
            int size = queue.size();//当前这一圈size
            distance++;
            for (int i = 0; i < size; i++) {
                Person currentPerson = queue.poll();
                if (!currentPerson.getVisited()) {
                    currentPerson.setVisited(true);
                    if (currentPerson.getFriend().contains(person2))
                        return distance;
                    //对应的部分外围一圈加上
                    for (Person p : currentPerson.getFriend()) {
                        if (!p.getVisited())
                            queue.add(p);
                    }
                }
            }
        }
        return -1;
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
        graph.addEdge(ross, rachel);//因为底层是有向图
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross)); // should print 1
        System.out.println(graph.getDistance(rachel, ben)); // should print 2
        System.out.println(graph.getDistance(rachel, rachel)); // should print 0
        System.out.println(graph.getDistance(rachel, kramer)); // should print -1
    }
}