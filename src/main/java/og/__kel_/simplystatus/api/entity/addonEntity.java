package og.__kel_.simplystatus.api.entity;

public class addonEntity {
    private String name;
    private String description = "";
    private executeEntity execute;
    public addonEntity(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public String getName(){
        return this.name;
    }
    public void setExecute(executeEntity execute){
        this.execute = execute;
    }

    public executeEntity getExecute() {
        return execute;
    }
}
