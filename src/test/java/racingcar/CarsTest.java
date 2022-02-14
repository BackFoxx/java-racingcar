package racingcar;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarsTest {
    private static final String DUPLICATED_NAME_ERROR = "[ERROR] 차 이름은 중복될 수 없습니다.";
    private static final String NOT_FOUND_CAR_NAME_ERROR = "[ERROR] 해당하는 차 이름은 존재하지 않습니다.";


    @Test
    public void Car_2개일때_Cars_객체_생성_테스트() {
        List<Car> carList = CarFactory.of("kun,forky");
        Cars cars = new Cars(carList);
        assertThat(cars.getNumberOfCar()).isEqualTo(2);
    }

    @Test
    public void Car_3개일때_Cars_객체_생성_테스트() {
        List<Car> carList = CarFactory.of("kun,forky,pobi");
        Cars cars = new Cars(carList);
        assertThat(cars.getNumberOfCar()).isEqualTo(3);
    }

    @Test
    public void 중복된_이름() {
        assertThatThrownBy(() -> new Cars(CarFactory.of("kun,kun")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATED_NAME_ERROR);
    }

    @Test
    public void Car_집합에서_이름이_kun_Car_반환() {
        Cars cars = new Cars(CarFactory.of("kun,forky"));
        Car car = cars.getCarByName("kun");
        assertThat(car.getName()).isEqualTo("kun");
    }

    @Test
    public void Car_집합에서_이름이_같은_Car_반환() {
        Cars cars = new Cars(CarFactory.of("kun,forky"));
        Car car = cars.getCarByName("forky");
        assertThat(car.getName()).isEqualTo("forky");
    }

    @Test
    public void Car_집합에서_찾는_이름이_존재_X() {
        Cars cars = new Cars(CarFactory.of("kun,forky"));
        assertThatThrownBy(() -> cars.getCarByName("pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_FOUND_CAR_NAME_ERROR);
    }

    @Test
    public void Car_집합에서_최대_position_값_찾기() {
        Cars cars = new Cars(CarFactory.of("kun,forky"));
        Car car = cars.getCarByName("kun");
        for (int i = 0; i < 5; i++) {
            car.move(true);
        }
        assertThat(cars.getMaxPosition()).isEqualTo(5);
    }
}