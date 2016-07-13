package entity;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name="category")
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    public Category(){}

    public Category(String title){
        this.setTitle(title);
    }

    public Category(int id, String title){
        this.setId(id);
        this.setTitle(title);
    }

    public static boolean titleMatches(String title){
        Pattern patternTitle = Pattern.compile("^[A-Za-zА-Яа-я ()-]{3,50}$");
        Matcher matcherTitle = patternTitle.matcher(title);
        return matcherTitle.matches();
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Category){
            Category category = (Category) obj;
            if (category.getTitle().equals(this.getTitle()) && category.getId() == this.id){
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
