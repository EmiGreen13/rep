package entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name="drug")
@Table(name="drug")
public class Drug {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "categoryId", nullable = false)
    private int categoryId;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "manufacture", length = 50, nullable = false)
    private String manufacture;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", length = 512, nullable = false)
    private String description;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="categoryId", insertable = false, updatable = false)
    private
    Category category;

    public static Drug createDrug(HttpServletRequest request) throws NullPointerException, NumberFormatException{
        String number = request.getParameter("number");
        Integer categoryId = new Integer(request.getParameter("categoryId"));
        String title = request.getParameter("title");
        String manufacture = request.getParameter("manufacture");
        String country = request.getParameter("country");
        Double price = new Double(request.getParameter("price"));
        String description = request.getParameter("description");
        if (matches(title, manufacture, country, description)){
            Drug drug = new Drug(categoryId, title, manufacture, country, price, description);
            if (number != null){
                drug.setId(new Integer(number));
            }
            return  drug;
        }
        throw new NumberFormatException();
    }

    public Drug(){}

    public Drug(int categoryId, String title, String manufacture, String country, Double price, String description){
        this.setCategoryId(categoryId);
        this.setTitle(title);
        this.setManufacture(manufacture);
        this.setCountry(country);
        this.setPrice(price);
        this.setDescription(description);
    }

    public static boolean matches(String title, String manufacture, String country, String description){
        return  mainMatches(title) & mainMatches(manufacture) & mainMatches(country) & descriptionMatches(description);
    }

    public static boolean mainMatches(String title){
        Pattern patternTitle = Pattern.compile("^[A-Za-zА-Яа-яЁё0-9 ()-]{3,50}$");
        Matcher matcherTitle = patternTitle.matcher(title);
        return matcherTitle.matches();
    }

    public static boolean descriptionMatches(String description){
        Pattern patternDescription = Pattern.compile("^[A-Za-zА-Яа-яЁё0-9 ()-,.:!]{0,512}$");
        Matcher matcherDescription = patternDescription.matcher(description);
        return matcherDescription.matches();
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Drug){
            Drug drug = (Drug) obj;
            if (drug.getTitle().equals(this.getTitle()) && drug.getId() == this.id && drug.getCountry().equals(this.getCountry())
                    && drug.getManufacture().equals(this.getManufacture()) && drug.getPrice().equals(this.getPrice())
                    && drug.getCategoryId() == this.getCategoryId()){
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

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
