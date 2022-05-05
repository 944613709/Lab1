package P3;

import java.util.*;

public class Person
{
    private String name;
    private boolean visited;
    private Set<Person> friendSet;
    public Person(String name){
        this.name = name;
        this.visited = false;
        this.friendSet = new HashSet<Person>();
    }

    /**
     * 返回Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 返回Visited
     * @return  true: 已经访问过，false: 没有访问
     *
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * setter的visited
     * @param flag 就是设置visited
     */
    public void setVisited(boolean flag) {
        visited = flag;
    }

    /**
     * 返回集合friendSet
     * @return friendSet
     */
    public Set<Person> getFriend(){
        return friendSet;
    }

    /**
     * 向FridendSet里面添加元素
     * @param person 要添加的person
     */
    public void addFriend(Person person) {
        friendSet.add(person);
    }

}