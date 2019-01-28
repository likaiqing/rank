package com.pandatv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandatv.service.model.InfoModel;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        int i = new DateTime().weekOfWeekyear().get();
        System.out.println(i);
        String info = "\"{\"avatar\":\"http://i7.pdim.gs/0257040380bd7d294a8017532c07e39b.jpg\",\"rid\":\"113984018\",\"roomId\":\"\",\"nickName\":\"请叫我轻狂大魔王\"}\"";
        InfoModel infoModel = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            infoModel = mapper.readValue(info, InfoModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
