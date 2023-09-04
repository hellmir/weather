package zerobase.weather.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerobase.weather.domain.Diary;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static zerobase.weather.testutil.TestObjectFactory.createDiary;

@SpringBootTest
class DiaryRepositoryTest {

    @Autowired
    private DiaryRepository diaryRepository;

    @AfterEach
    public void cleanUp() {
        diaryRepository.deleteAll();
    }

    @DisplayName("입력한 날짜의 모든 일기를 조회할 수 있다.")
    @Test
    void findAllByDate() {

        // given
        Diary diary1 = createDiary("clear", "01d", 20.0,
                "가나다", LocalDate.of(2020, 2, 2));

        Diary diary2 = createDiary("clear", "01d", 30.0,
                "가나다라", LocalDate.of(2021, 3, 3));

        Diary diary3 = createDiary("rain", "01n", 40.0,
                "가나다라마", LocalDate.of(2020, 2, 2));

        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        diaryRepository.save(diary3);

        // when
        List<Diary> diaries = diaryRepository.findAllByDate(LocalDate.of(2020, 2, 2));

        // then
        assertThat(diaries).hasSize(2)
                .extracting("weather", "icon", "temperature", "text")
                .containsExactlyInAnyOrder(
                        tuple("clear", "01d", 20.0, "가나다"),
                        tuple("rain", "01n", 40.0, "가나다라마")
                );

    }

    @DisplayName("특정 기간의 모든 일기를 조회할 수 있다.")
    @Test
    void findAllByDateBetween() {

        // given
        Diary diary1 = createDiary("clear", "01d", 20.0,
                "가나다", LocalDate.of(2020, 2, 2));

        Diary diary2 = createDiary("rain", "01n", 30.0,
                "가나다라", LocalDate.of(2021, 3, 3));

        Diary diary3 = createDiary("clear", "01d", 40.0,
                "가나다라마", LocalDate.of(2022, 4, 4));

        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        diaryRepository.save(diary3);

        // when
        List<Diary> diaries = diaryRepository.findAllByDateBetween
                (LocalDate.of(2020, 2, 2), LocalDate.of(2021, 4, 4));

        // then
        assertThat(diaries).hasSize(2)
                .extracting("weather", "icon", "temperature", "text")
                .containsExactlyInAnyOrder(
                        tuple("clear", "01d", 20.0, "가나다"),
                        tuple("rain", "01n", 30.0, "가나다라")
                );

    }

}