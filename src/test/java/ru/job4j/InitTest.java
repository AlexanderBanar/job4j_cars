package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InitTest {
    @Test
    public void justReturnOne() {
        assertThat(new Init().justReturnOne(), is(1));
    }

    @Test
    public void justGetOneAgain() {
        assertNotEquals(2, new Init().justReturnOne());
    }
}