package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class HashMapBehaviourTest {

    /** Ключ, у которого ВСЕ объекты имеют одинаковый hashCode, но equals различает по id. */
    private static final class CollidingKey {
        private final int id;
        CollidingKey(int id) { this.id = id; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return id == ((CollidingKey) o).id;
        }

        @Override
        public int hashCode() { return 42; }
    }

    /** Намеренно плохой изменяемый ключ: hashCode зависит от изменяемого поля. */
    private static final class MutableKey {
        private int value;
        MutableKey(int value) { this.value = value; }
        void setValue(int value) { this.value = value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return value == ((MutableKey) o).value;
        }

        @Override
        public int hashCode() { return value; }
    }

    @Test
    void collisionDoesNotMeanEquality() {
        Map<CollidingKey, String> map = new HashMap<>();
        map.put(new CollidingKey(1), "one");
        map.put(new CollidingKey(2), "two");
        // хеши одинаковые (корзина одна), но equals различает ключи -> два разных значения
        assertEquals(2, map.size());
        assertEquals("one", map.get(new CollidingKey(1)));
        assertEquals("two", map.get(new CollidingKey(2)));
    }

    @Test
    void mutatingKeyAfterInsertionBreaksLookup() {
        Map<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey(1);
        map.put(key, "data");

        key.setValue(2); // hashCode изменился, а запись лежит в старой корзине

        assertNull(map.get(key));                // ищем в корзине для 2 - там пусто
        assertFalse(map.containsKey(key));
        assertNull(map.get(new MutableKey(1)));  // в корзине для 1 лежит ключ со value=2, equals не совпадает
        assertEquals(1, map.size());             // запись не потеряна, но недостижима
    }
}