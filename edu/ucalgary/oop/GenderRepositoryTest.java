package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class GenderRepositoryTest {
    private GenderRepository repository;
    private String validFilePath = "edu/ucalgary/oop/GenderOptions.txt"; // Update with actual path
    private String invalidFilePath = "nonexistent/path.txt";

    @Before
    public void setUp() {
        repository = new GenderRepository();
    }

    @Test
    public void testReadFileWithValidPath() {
        List<String> genders = repository.readFile(validFilePath);
        assertNotNull("Gender list should not be null", genders);
        assertFalse("Gender list should not be empty", genders.isEmpty());
        assertTrue("Gender list should contain expected values", genders.contains("girl") && genders.contains("woman"));
    }

    @Test
    public void testReadFileWithInvalidPath() {
        List<String> genders = repository.readFile(invalidFilePath);
        assertNull("Gender list should be null for invalid path", genders);
    }

    @Test
    public void testListFileOptionsWithValidPath() {
        List<String> distinctGenders = repository.listFileOptions(validFilePath);
        assertNotNull("Distinct gender list should not be null", distinctGenders);
        assertEquals("Distinct gender list should contain expected number of elements", 7, distinctGenders.size());
    }

    @Test
    public void testListFileOptionsWithInvalidPath() {
        List<String> distinctGenders = repository.listFileOptions(invalidFilePath);
        assertNull("Distinct gender list should be null for invalid path", distinctGenders);
    }
}
