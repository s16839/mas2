import java.util.List;
import java.util.Objects;

public class Car {
    public String manufacturer;
    public String model;
    public String bodyType;
    public String engineType;
    public int doors;
    public boolean discount;
    public List<String> options;

    public Car(String manufacturer, String model, String bodyType, String engineType, int doors, boolean discount, List<String> options) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.doors = doors;
        this.discount = discount;
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Car)) return false;

        Car car = (Car) o;
        return Objects.equals(manufacturer, car.manufacturer) &&
                Objects.equals(model, car.model) &&
                Objects.equals(bodyType, car.bodyType) &&
                Objects.equals(engineType, car.engineType) &&
                doors == car.doors && discount == car.discount &&
                Objects.equals(options, car.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, bodyType, engineType, doors, discount, options);
    }


    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", engineType='" + engineType + '\'' +
                ", doors=" + doors +
                ", discount=" + discount +
                ", options=" + options +
                '}';
    }
}
