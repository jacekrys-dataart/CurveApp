package pl.myosolutions.curveapp.java.pl.myosolutions.curveapp.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import pl.myosolutions.curveapp.model.Total;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TotalModelUnitTest {

    @Mock
    Total total;


    @Before
    public void setUp(){
        total = new Total(0);
    }

    @Test
    public void totalCalculationTest_notNull(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("00", 10);
        map.put("01", 11);
        map.put("10", 12);
        map.put("11", 13);
        map.put("20", 14);
        map.put("21", 10);


        Total calculatedTotal = total.calculateTotal(map);
        assertNotNull(calculatedTotal);
        assertThat(map.size(), is(6));
    }

    @Test
    public void totalCalculationTest_CalculatesPositive(){
        HashMap<String, Integer> values = new HashMap<>();
        values.put("00", 10);
        values.put("01", 11);
        values.put("10", 12);
        values.put("11", 13);
        values.put("20", 14);
        values.put("21", 10);

        Total calculatedTotal = total.calculateTotal(values);
        assertEquals("Total mismatch!",70, calculatedTotal.getTotal());
    }



}
