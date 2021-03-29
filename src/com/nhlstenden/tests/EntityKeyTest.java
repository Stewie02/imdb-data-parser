package com.nhlstenden.tests;

import com.nhlstenden.entities.EntityKey;
import com.nhlstenden.entities.Movie;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EntityKeyTest {

    @Test
    public void simpleTest() {
        EntityKey a = new EntityKey("hallo");
        EntityKey b = new EntityKey("hallo");

        assertEquals(a, b);
    }

    @Test
    public void anotherSimpleTest() {
        EntityKey a = new EntityKey("hallo");
        EntityKey b = new EntityKey("doei");

        assertNotEquals(a, b);
    }

    @Test
    public void movieTest() {
        EntityKey a = Movie.getKey("hallo", 1002, "II");
        EntityKey b = Movie.getKey("hallo", 1002, "II");

        assertEquals(a, b);
    }

    @Test
    public void anotherMovieTest() {
        EntityKey a = Movie.getKey("hallo", 1002, "II");
        EntityKey b = Movie.getKey("hallo", 1002, "I");

        assertNotEquals(a, b);
    }

    @Test
    public void testWithMap() {
        Map<EntityKey, Movie> map = new HashMap<>();
        Movie m = new Movie(1, "Stefans Film", 2021, "II");

        assertEquals(m.getKey(), m.getKey());
        map.put(m.getKey(), m);
        assertEquals(m, map.get(m.getKey()));
    }
}