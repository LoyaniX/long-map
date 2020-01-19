/*
 * This module is part of the CSF experimental system
 * Copyright (c) Soft Computer Consultants, Inc.
 * All Rights Reserved
 * This document contains unpublished, confidential and proprietary
 * information of Soft Computer Consultants, Inc. No disclosure or use of
 * any portion of the contents of these materials may be made without the
 * express written consent of Soft Computer Consultants, Inc.
 */
package de.comparus.opensource.longmap;

import de.comparus.opensource.longmap.impl.LongMapImpl;
import org.junit.Assert;
import org.junit.Test;

public class LongMapTest
{
    @Test
    public void putMapElementTest()
    {
        //Given
        String expectValue = "Test String";
        LongMap<String> testMap = new LongMapImpl<>();
        String actualValue = testMap.put(3L, expectValue);
        //When
        long actualSize = testMap.size();
        //Than
        Assert.assertEquals(1, actualSize);
        Assert.assertEquals(expectValue, actualValue);
    }

    @Test
    public void replaceExistsMapElementTest()
    {
        //Given
        String expectValue = "Test String";
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(3L, expectValue);
        String actualValue = testMap.get(3L);
        Assert.assertEquals(expectValue, actualValue);
        //When
        testMap.put(3L, "New value");

        long actualSize = testMap.size();
        actualValue = testMap.get(3L);
        //Than
        Assert.assertEquals(1, actualSize);
        Assert.assertNotEquals(expectValue, actualValue);
        Assert.assertEquals("New value", actualValue);
    }

    @Test
   public void put1000MapElementsTest()
   {
       //Given
       String expectValue = "Test String";
       LongMap<String> testMap = new LongMapImpl<>();
       for (long i = 0L; i < 1000L; i++)
       {
           testMap.put(i, expectValue);
       }
       //When
       long actualSize = testMap.size();
       boolean isValueEqual = testMap.containsValue(expectValue);
       //Than
       Assert.assertEquals(1000, actualSize);
       Assert.assertTrue(isValueEqual);
   }


    @Test
    public void getMapElementTest()
    {
        //Given
        String expectValue = "TEST STRING3";
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(0L, "TEST STRING1");
        testMap.put(1L, "TEST STRING2");
        testMap.put(2L, "TEST STRING3");
        testMap.put(3L, "TEST STRING4");
        testMap.put(10L, "TEST STRING1");
        testMap.put(11L, "TEST STRING2");
        testMap.put(12L, "TEST STRING3");
        testMap.put(13L, "TEST STRING4");
        testMap.put(20L, "TEST STRING1");
        testMap.put(21L, "TEST STRING2");
        testMap.put(22L, "TEST STRING3");
        testMap.put(23L, "TEST STRING4");
        testMap.put(30L, "TEST STRING1");
        testMap.put(31L, "TEST STRING2");
        testMap.put(32L, "TEST STRING3");
        testMap.put(33L, "TEST STRING4");
        //When
        String actualValue = testMap.get(2L);
        //Than
        Assert.assertEquals(expectValue, actualValue);
    }

    @Test
    public void getFromEmptyMapTest()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        //When
        long actualSize = testMap.size();
        String actualValue = testMap.get(-1L);
        //Than
        Assert.assertEquals(0, actualSize);
        Assert.assertNull(actualValue);
    }

    @Test
    public void putAndGetElementsWithNotPositiveKeys()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(-3L, "Test String-3");
        testMap.put(0L, "Test String0");
        testMap.put(-321L, "Test String-321");
        testMap.put(-1L, "Test String-1");
        //When
        long actualSize = testMap.size();
        String actualValue1 = testMap.get(-3L);
        String actualValue2 = testMap.get(0L);
        String actualValue3 = testMap.get(-321L);
        String actualValue4 = testMap.get(-1L);
        //Than
        Assert.assertEquals("Test String-3", actualValue1);
        Assert.assertEquals("Test String0", actualValue2);
        Assert.assertEquals("Test String-321", actualValue3);
        Assert.assertEquals("Test String-1", actualValue4);
        Assert.assertEquals(4, actualSize);
    }

    @Test
    public void removeTest()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(0L, "TEST STRING1");
        testMap.put(1L, "TEST STRING2");
        testMap.put(2L, "TEST STRING3");
        testMap.put(3L, "TEST STRING4");
        //When
        testMap.remove(2L);
        boolean isKeyContains = testMap.containsKey(2L);
        //Than
        Assert.assertFalse(isKeyContains);
    }

    @Test
    public void isEmptyWithNotEmptyMapTest()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(0L, "TEST STRING1");
        testMap.put(1L, "TEST STRING2");
        testMap.put(2L, "TEST STRING3");
        testMap.put(3L, "TEST STRING4");
        //When
        boolean isEmpty = testMap.isEmpty();
        //Than
        Assert.assertFalse(isEmpty);
    }

    @Test
    public void isEmptyWithEmptyMapTest()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        //When
        boolean isEmpty = testMap.isEmpty();
        //Than
        Assert.assertTrue(isEmpty);
    }

    @Test
    public void containsKeyTest()
    {
        //Given
        String expectValue = "Test String";
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(3L, expectValue);
        //When
        long actualSize = testMap.size();
        boolean isKeyExist = testMap.containsKey(3L);
        //Than
        Assert.assertEquals(1, actualSize);
        Assert.assertTrue(isKeyExist);
    }

    @Test
    public void containsValueTest()
    {
        //Given
        String expectValue = "Test String";
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(3L, expectValue);
        //When
        long actualSize = testMap.size();
        boolean isValueExist = testMap.containsValue("Test String");
        //Than
        Assert.assertEquals(1, actualSize);
        Assert.assertTrue(isValueExist);
    }

    @Test
    public void getAllKeysTest()
    {
        //Given
        LongMap<Long> testMap = new LongMapImpl<>();
        long[] expectedKeys = {1L, 2L, 3L, 5L, 6L};
        for (long key : expectedKeys)
        {
            testMap.put(key, key);
        }
        //When
        long actualSize = testMap.size();
        long[] actualKeys = testMap.keys();
        //Than
        Assert.assertEquals(5, actualSize);
        Assert.assertArrayEquals(expectedKeys, actualKeys);
    }

    @Test
    public void getAllValuesTest()
    {
        //Given
        LongMap<Long> testMap = new LongMapImpl<>();
        Long[] expectedValue = {1L, 2L, 3L, 5L, 6L};
        for (long key : expectedValue)
        {
            testMap.put(key, key);
        }
        //When
        long actualSize = testMap.size();
        Long[] actualValue = testMap.values();
        //Than
        Assert.assertEquals(5, actualSize);
        Assert.assertArrayEquals(expectedValue, actualValue);
    }

    @Test
    public void emptyMapSizeTest()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        //When
        long size = testMap.size();
        //Than
        Assert.assertEquals(0L, size);
    }

    @Test
    public void NotEmptryMapSizeTest()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(0L, "TEST STRING1");
        testMap.put(1L, "TEST STRING2");
        testMap.put(2L, "TEST STRING3");
        testMap.put(3L, "TEST STRING4");
        //When
        long size = testMap.size();
        //Than
        Assert.assertEquals(4L, size);
    }

    @Test
    public void clearTest()
    {
        //Given
        LongMap<String> testMap = new LongMapImpl<>();
        testMap.put(0L, "TEST STRING1");
        testMap.put(1L, "TEST STRING2");
        testMap.put(2L, "TEST STRING3");
        testMap.put(3L, "TEST STRING4");
        long size = testMap.size();
        Assert.assertEquals(4, size);
        //When
        testMap.clear();
        //Than
        size = testMap.size();
        Assert.assertEquals(0, size);
    }
}
