package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository implements RepositoryInterface<Car> {
    private List<Car> carData = new ArrayList<>();

    @Override
    public Car create(Car car){
        if(car.getCarId() == null){
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll(){
        return carData.iterator();
    }
    @Override
    public Car findById(String id){
        for (Car car : carData) {
            if(car.getCarId().equals(id)){
                return car;
            }
        }
        throw new RuntimeException(
                String.format("Car with ID %s not found.", id)
        );
    }

    @Override
    public void update(Car updatedCar, String id){
        if (carData.isEmpty()) {
            throw new RuntimeException(
                    "Cars are empty"
            );
        }

        Car targetCar = findById(id);
        targetCar.setCarName(updatedCar.getCarName());
        targetCar.setCarQuantity(updatedCar.getCarQuantity());
        targetCar.setCarColor(updatedCar.getCarColor());
    }

    @Override
    public void delete(String id){
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
