package de.hmayer.solarsystemserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class RepositoryTests {

    private Repository<TestIdentifiable> repository;

    @BeforeEach
    void setUp() {
        Function<List<String>, TestIdentifiable> recordHandler = data -> new TestIdentifiable(
                Integer.parseInt(data.get(0)), data.get(1));
        repository = new Repository<>("test.csv", recordHandler);
    }

    @Test
    void SOL_T111_testFindAll() {
        List<TestIdentifiable> elements = repository.findAll();
        assertNotNull(elements, "Elements list should not be null");
    }

    @Test
    void SOL_T112_testFindById() {
        TestIdentifiable item = new TestIdentifiable(1, "Object 1");
        repository.add(item);
        Optional<TestIdentifiable> found = repository.findById(1);
        assertTrue(found.isPresent(), "Item with ID 1 should be found");
        assertEquals(item, found.get(), "Found item should match the added item");
    }

    @Test
    void SOL_T113_testAdd() {
        TestIdentifiable item = new TestIdentifiable(2, "Object 2");
        repository.add(item);
        assertTrue(repository.findById(2).isPresent(), "Item with ID 2 should be added and found");
    }


    @Test
    void SOL_T114_testAddSameObjectTwice() {
        TestIdentifiable item = new TestIdentifiable(2, "Object 2");
        repository.add(item);
        repository.add(item);
        assertTrue(repository.findById(2).isPresent(), "Item with ID 2 should be added and found");
        assertEquals(1,repository.findAll().size());
    }

    @Test
    void SOL_T115_testDelete() {
        TestIdentifiable item = new TestIdentifiable(3, "Object 3");
        repository.add(item);
        repository.delete(3);
        assertFalse(repository.findById(3).isPresent(), "Item with ID 3 should be deleted");
    }

    @Test
    void SOL_T116_testDeleteAll() {
        repository.add(new TestIdentifiable(4, "Object 4"));
        repository.add(new TestIdentifiable(5, "Object 5"));
        repository.deleteAll();
        assertTrue(repository.findAll().isEmpty(), "All items should be deleted");
    }

    @Test
    void SOL_T117_testLoadCSVDataFileNotFound(){
        Function<List<String>, TestIdentifiable> recordHandler = data -> new TestIdentifiable(
                Integer.parseInt(data.get(0)), data.get(1));

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.loadCSVData("test.csv", recordHandler),
                "Expected load to throw, but it didn't");

    }
}