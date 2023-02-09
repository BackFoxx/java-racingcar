package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.exception.AlreadyDefinedFieldException;
import vo.Name;
import vo.Trial;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ServiceTest {
    Service service;

    @BeforeEach
    void setUp() {
        service = new Service();
    }

    @Test
    @DisplayName("중복된 이름이 입력되면 예외발생")
    void setCarsFailTest() {
        assertThatThrownBy(() -> service.setCars(Name.of(List.of("fox", "fox"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("시도횟수가 null 이면 rule에 시도횟수 저장")
    void setTrial() {
        service.setTrial(Trial.of(4L));
        Trial result = service.getTrial();

        assertThat(4L).isEqualTo(result.getValue());
    }

    @Test
    @DisplayName("시도횟수가 null이 아니면, 예외발생")
    void setTrialFailTest() {
        Trial trial = Trial.of(4L);
        service.setTrial(trial);

        assertThatThrownBy(() -> service.setTrial(trial))
                .isInstanceOf(AlreadyDefinedFieldException.class);
    }
}