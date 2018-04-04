public class User {
    private String name, ID, pass;

    public User(){}

    public User(String name, String ID, String pass){
        this.name = name;
        this.ID = ID;
        this.pass = pass;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setID (String ID){
        this.ID = ID;
    }
    public String getName(){
        return name;
    }
    public String getID(){
        return ID;
    }
    public String getPass() {
        return pass;
    }

}
