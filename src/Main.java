import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Car> cars = load("src/cars.csv");

        List<Car> suvCars = cars.stream()
                .filter(car -> car.bodyType.equals("SUV"))
                .collect(Collectors.toList());

        List<Car> dieselCars = cars.stream()
                .filter(car -> car.engineType.equals("diesel"))
                .collect(Collectors.toList());

        List<Car> intersection = dieselCars.stream()
                .distinct()
                .filter(suvCars::contains)
                .collect(Collectors.toList());

        HashSet<Car> carsWithDiscount = cars.stream()
                .filter(car -> car.discount)
                .collect(Collectors.toCollection(HashSet::new));

        HashSet<Car> carsToyota = cars.stream()
                .filter(car -> car.manufacturer.equals("Toyota"))
                .collect(Collectors.toCollection(HashSet::new));

        carsWithDiscount.removeAll(carsToyota);

        List<Car> diff = new ArrayList<>(carsWithDiscount);

        List<Car> result = new ArrayList<>();
        result.addAll(intersection);
        result.addAll(diff);

        print(result);

        try {
            saveToCsv(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Car> load(String filePath){
        List<Car> cars = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null){
               String[] split = line.split(",");
               String[] optsSplit = split[6].split(";");
               List<String> options = new ArrayList<>();
               boolean discount;
               for(String option : optsSplit) options.add(option);
               if(split[5].equals("tak")) discount = true; else discount = false;
               Car newCar = new Car(split[0], split[1], split[2], split[3], Integer.parseInt(split[4]), discount, options);
               cars.add(newCar);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public static void saveToCsv(List<Car> cars) throws IOException {
        File out = new File("src/out.csv");
        FileOutputStream fos = new FileOutputStream(out);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        for(Car car : cars){
            String optionsString = "";
            for(int i = 0 ; i < car.options.size() ; i++){
               if(i == 0) optionsString += ","+car.options.get(i);
               else
                optionsString += ";"+car.options.get(i);
            }
            String line = car.manufacturer+","+car.model+","+car.bodyType+","+
                    car.engineType+","+car.doors+","+car.discount+optionsString;

            writer.write(line);
            writer.newLine();
        }
        writer.close();

    }

    public static void print(List<Car> cars){
        for(Car car : cars) System.out.println(car);
    }
}
