package dev.idion.hackingspringboot.reactive.domain.item;

import java.time.LocalDate;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

public class Item {

  private @Id String id;
  private String name;
  private String description;
  private double price;
  private String distributorRegion;
  private LocalDate releaseDate;
  private int availableUnits;
  private Point location;

  private Item() {}

  public Item(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDistributorRegion() {
    return distributorRegion;
  }

  public void setDistributorRegion(String distributorRegion) {
    this.distributorRegion = distributorRegion;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getAvailableUnits() {
    return availableUnits;
  }

  public void setAvailableUnits(int availableUnits) {
    this.availableUnits = availableUnits;
  }

  public Point getLocation() {
    return location;
  }

  public void setLocation(Point location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Double.compare(item.getPrice(), getPrice()) == 0 && Objects
        .equals(getName(), item.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPrice());
  }
}
