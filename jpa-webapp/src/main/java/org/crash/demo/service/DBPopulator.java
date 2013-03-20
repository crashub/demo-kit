package org.crash.demo.service;

import org.crash.demo.entities.Category;
import org.crash.demo.entities.Customer;
import org.crash.demo.entities.Item;
import org.crash.demo.entities.Product;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 */
@Singleton
@Startup
@DataSourceDefinition(
        className = "org.hsqldb.jdbc.JDBCDataSource",
        name = "java:global/jdbc/crashJpaDemoDS",
        user = "app",
        password = "app",
        databaseName = "crashJpaDemoDB",
        properties = {"connectionAttributes=;create=true"}
)
public class DBPopulator {

  Category fish;
  Category dogs;
  Category reptiles;
  Category cats;
  Category birds;

  Customer marc;
  Customer bill;
  Customer steve;
  Customer user;
  Customer admin;

  Product angelfish;
  Product tigerShark;
  Product koi;
  Product goldfish;
  Product bulldog;
  Product poodle;
  Product dalmation;
  Product goldenRetriever;
  Product labradorRetriever;
  Product chihuahua;
  Product rattlesnake;
  Product iguana;
  Product manx;
  Product persian;
  Product amazonParrot;
  Product finch;

  @PersistenceContext(name = "crashJpaDemoPU")
  private EntityManager em;

  @PostConstruct
  private void populateDB() {

    fish = createCategory("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");
    dogs = createCategory("Dogs", "A domesticated carnivorous mammal related to the foxes and wolves and raised in a wide variety of breeds");
    reptiles = createCategory("Reptiles", "Any of various cold-blooded, usually egg-laying vertebrates, such as a snake, lizard, crocodile, turtle");
    cats = createCategory("Cats", "Small carnivorous mammal domesticated since early times as a catcher of rats and mice and as a pet and existing in several distinctive breeds and varieties");
    birds = createCategory("Birds", "Any of the class Aves of warm-blooded, egg-laying, feathered vertebrates with forelimbs modified to form wings");

    marc = createCustomer("Marc", "Fleury");
    bill = createCustomer("Bill", "Gates");
    steve = createCustomer("Steve", "Jobs");
    user = createCustomer("User", "User");
    admin = createCustomer("Admin", "Admin");

    angelfish = createProduct("Angelfish", "Saltwater fish from Australia", fish);
    tigerShark = createProduct("Tiger Shark", "Saltwater fish from Australia", fish);
    koi = createProduct("Koi", "Freshwater fish from Japan", fish);
    goldfish = createProduct("Goldfish", "Freshwater fish from China", fish);
    bulldog = createProduct("Bulldog", "Friendly dog from England", dogs);
    poodle = createProduct("Poodle", "Cute dog from France", dogs);
    dalmation = createProduct("Dalmation", "Great dog for a fire station", dogs);
    goldenRetriever = createProduct("Golden Retriever", "Great family dog", dogs);
    labradorRetriever = createProduct("Labrador Retriever", "Great hunting dog", dogs);
    chihuahua = createProduct("Chihuahua", "Great companion dog", dogs);
    rattlesnake = createProduct("Rattlesnake", "Doubles as a watch dog", reptiles);
    iguana = createProduct("Iguana", "Friendly green friend", reptiles);
    manx = createProduct("Manx", "Great for reducing mouse populations", cats);
    persian = createProduct("Persian", "Friendly house cat, doubles as a princess", cats);
    amazonParrot = createProduct("Amazon Parrot", "Great companion for up to 75 years", birds);
    finch = createProduct("Finch", "Great stress reliever", birds);

    createItem("Large", 10.00f, angelfish);
    createItem("Thootless", 10.00f, angelfish);
    createItem("Spotted", 12.00f, tigerShark);
    createItem("Spotless", 12.00f, tigerShark);
    createItem("Male Adult", 12.00f, koi);
    createItem("Female Adult", 12.00f, koi);
    createItem("Male Puppy", 12.00f, goldfish);
    createItem("Female Puppy", 12.00f, goldfish);
    createItem("Spotless Male Puppy", 22.00f, bulldog);
    createItem("Spotless Female Puppy", 22.00f, bulldog);
    createItem("Spotted Male Puppy", 32.00f, poodle);
    createItem("Spotted Female Puppy", 32.00f, poodle);
    createItem("Tailed", 62.00f, dalmation);
    createItem("Tailless", 62.00f, dalmation);
    createItem("Tailed", 82.00f, goldenRetriever);
    createItem("Tailless", 82.00f, goldenRetriever);
    createItem("Tailed", 100.00f, labradorRetriever);
    createItem("Tailless", 100.00f, labradorRetriever);
    createItem("Tailed", 100.00f, chihuahua);
    createItem("Tailless", 100.00f, chihuahua);
    createItem("Female Adult", 20.00f, rattlesnake);
    createItem("Male Adult", 20.00f, rattlesnake);
    createItem("Female Adult", 150.00f, iguana);
    createItem("Male Adult", 150.00f, iguana);
    createItem("Female Adult", 120.00f, manx);
    createItem("Male Adult", 120.00f, manx);
    createItem("Female Adult", 70.00f, persian);
    createItem("Male Adult", 70.00f, persian);
    createItem("Female Adult", 120.00f, amazonParrot);
    createItem("Male Adult", 120.00f, amazonParrot);
    createItem("Female Adult", 75.00f, finch);
    createItem("Male Adult", 80.00f, finch);

  }

  @PreDestroy
  private void clearDB() {

    removeCategory(fish);
    removeCategory(dogs);
    removeCategory(reptiles);
    removeCategory(cats);
    removeCategory(birds);

    removeCustomer(marc);
    removeCustomer(bill);
    removeCustomer(steve);
    removeCustomer(user);
    removeCustomer(admin);

  }

  public Customer createCustomer(String firstname, String lastName) {
    Customer customer = new Customer(firstname, lastName);
    em.persist(customer);
    return customer;
  }

  public Category createCategory(String name, String description) {
    Category category = new Category(name, description);
    em.persist(category);
    return category;
  }

  public Product createProduct(String name, String description, Category category) {
    Product product = new Product(name, description, category);
    em.persist(product);
    return product;
  }

  public Item createItem(String name, Float unitCost, Product product) {
    String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit ante, malesuada porta condimentum eget, tristique id magna. Donec ac justo velit. Suspendisse potenti. Donec vulputate vulputate molestie. Quisque vitae arcu massa, dictum sodales leo. Sed feugiat elit vitae ante auctor ultrices. Duis auctor consectetur arcu id faucibus. Curabitur gravida.";
    Item item = new Item(name, lorem, unitCost, product);
    em.persist(item);
    return item;
  }

  public void removeCategory(Category category) {
    em.remove(category);
  }

  public void removeCustomer(Customer customer) {
    em.remove(customer);
  }

}
