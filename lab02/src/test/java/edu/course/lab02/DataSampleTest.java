package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataSampleTest {

    // Корректный объект 

    @Test
    void validSampleKeepsValues() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0, 2.0, 3.0});
        assertEquals("s1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.RAW, sample.getStatus());
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, sample.getFeatures());
    }

    // Проверка id 

    @Test
    void nullIdIsRejected() {
        // id == null не должен проходить: конструктор обязан бросить исключение
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(null, "cat", SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void blankIdIsRejected() {
        // строка из одних пробелов тоже недопустима
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("   ", "cat", SampleStatus.RAW, new double[]{1.0}));
    }

    // Проверка label 

    @Test
    void nullLabelIsRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", null, SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void emptyLabelIsRejected() {
        // пустая строка "" тоже недопустима (isBlank() возвращает true)
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "", SampleStatus.RAW, new double[]{1.0}));
    }

    // проверка status

    @Test
    void nullStatusIsRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", null, new double[]{1.0}));
    }

    // проверка features 

    @Test
    void nullFeaturesAreRejected() {
        // сам массив равен null: проверка должна сработать до обращения к length
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.RAW, null));
    }

    @Test
    void emptyFeaturesAreRejected() {
        // пустой массив запрещён, иначе среднее значение делилось бы на ноль
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.RAW, new double[]{}));
    }

    @Test
    void nanFeatureIsRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0, Double.NaN}));
    }

    @Test
    void infiniteFeatureIsRejected() {
        // бесконечность - не конечное число, Double.isFinite вернёт false
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.RAW,
                new double[]{1.0, Double.POSITIVE_INFINITY}));
    }

    // защитное копирование массива 

    @Test
    void changingSourceArrayDoesNotAffectSample() {
        double[] source = {1.0, 2.0};
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, source);
        source[0] = 99.0; // меняем исходный массив после создания объекта
        assertEquals(1.0, sample.getFeatures()[0]);
    }

    @Test
    void changingReturnedArrayDoesNotAffectSample() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0, 2.0});
        double[] returned = sample.getFeatures();
        returned[0] = 99.0; // меняем массив, полученный из getFeatures()
        assertEquals(1.0, sample.getFeatures()[0]);
    }

    // поведение: среднее 

    @Test
    void meanFeatureIsCorrect() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0, 2.0, 6.0});
        // сравнение double с допуском, а не точное равенство
        assertEquals(3.0, sample.meanFeature(), 1e-9);
    }

    // Поведение: changeStatus и isReady 

    @Test
    void changeStatusToReadyMakesSampleReady() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0});
        sample.changeStatus(SampleStatus.READY);
        assertTrue(sample.isReady());
    }

    @Test
    void changeStatusToNullIsRejectedAndKeepsOldStatus() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0});

        // 1. Недопустимая операция должна бросить исключение
        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));

        // 2. И не должна испортить состояние объекта: статус остался прежним
        assertEquals(SampleStatus.RAW, sample.getStatus());
    }

    @Test
    void newRawSampleIsNotReady() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0});
        assertFalse(sample.isReady());
    }

    @Test
    void labeledSampleIsNotReady() {
        // готовым считается только READY, статус LABELED — ещё не готов
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0});
        sample.changeStatus(SampleStatus.LABELED);
        assertFalse(sample.isReady());
    }
}