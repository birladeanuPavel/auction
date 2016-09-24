package com.birladeanu;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pavel on 8/28/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
@Transactional
public abstract class AbstractTest {
}
