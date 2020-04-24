package data;

public class Users {

    String name;
    String count;
    String id;


    public Users(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public Users(){

    }

    //getters and setters methods:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return count;
    }

    public void setJob(String job) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
