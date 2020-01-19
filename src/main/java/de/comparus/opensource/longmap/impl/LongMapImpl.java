package de.comparus.opensource.longmap.impl;

import de.comparus.opensource.longmap.LongMap;
import de.comparus.opensource.longmap.models.Entry;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LongMapImpl<V> implements LongMap<V>
{
    private int size = 0;
    private final int DEFAULT_MAP_SIZE = 16;
    private float LOAD_FACTOR = 0.75f;
    private int sizeToDouble;
    private Entry[] entryTable;

    public LongMapImpl()
    {
        entryTable = new Entry[DEFAULT_MAP_SIZE];
        sizeToDouble = (int) (DEFAULT_MAP_SIZE * LOAD_FACTOR);
    }

    public LongMapImpl(int mapSize)
    {
        entryTable = new Entry[mapSize];
        sizeToDouble = (int) (mapSize * LOAD_FACTOR);
    }

    public V put(long key, V value)
{
    int keyHash = getHash(key);
    Entry<V> entry = new Entry<>(keyHash, key, value);
    int index = getIndex(entry);
    if (entryTable[index] == null)
    {
        entryTable[index] = entry;
    }
    else
    {
        Entry previousEntry = entryTable[index];
        if (previousEntry.getKey() == entry.getKey())
        {
            previousEntry.setValue(entry.getValue());
            return (V) previousEntry.getValue();
        }
        else
        {
            entryTable[index] = new Entry(keyHash, key, value, previousEntry);
        }
    }

    size ++;

    if(size == sizeToDouble) changeMapSize();
    return entry.getValue();
}

    public V get(long key) {
        for (Entry entry : entryTable)
        {
            if(entry != null)
            {
                if(entry.getKey() == key) return (V) entry.getValue();
                while (entry.getNextEntry() != null)
                {
                    entry = entry.getNextEntry();
                    if(entry.getKey() == key) return (V) entry.getValue();
                }
            }
        }
        return null;
    }

    public V remove(long key)
    {
        if (!containsKey(key)) return null;
        V removalValue = null;
        for (int i = 0; i < entryTable.length; i++)
        {
            if (entryTable[i] != null && (entryTable[i].getKey() == key))
            {
                removalValue = (V) entryTable[i].getValue();
                entryTable[i] = null;
                size--;
            }
        }
        return removalValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        return Arrays.stream(keys()).
                anyMatch(findKey -> findKey == key);
    }

    public boolean containsValue(V value) {
        return Arrays.stream(values()).
                anyMatch(findValue -> findValue == value);
    }

    public long[] keys() {
        long[] keys = new long[size];
        int index = 0;
        for (int i = 0; i < entryTable.length; i++)
        {
            if (entryTable[i] != null)
            {
                keys[index] = entryTable[i].getKey();
                index++;
            }
        }
        return keys;
    }

    public V[] values() {
        V[] values = (V[]) new Object[size];
        int index = 0;
        for (int i = 0; i < entryTable.length; i++)
        {
            if (entryTable[i] != null)
            {
                values[index] = (V) entryTable[i].getValue();
                index++;
            }
        }
        V[] result = (V[]) Array.newInstance(values[0].getClass(), size);
        System.arraycopy(values, 0, result, 0, size);
        return result;
    }

    public long size()
    {
        return size;
    }

    public void clear()
    {
        int oldLength = entryTable.length;
        entryTable = new Entry[oldLength];
        size = 0;
    }

    private void changeMapSize()
    {
        Entry[] entries = new Entry[entryTable.length * 2];
        size = 0;
        for (Entry entry : entryTable)
        {
            if (entry == null) continue;
            int index = getIndex(entry);
            entries[index] = entry;
            size++;
        }
        entryTable = entries;
        sizeToDouble = (int) (entryTable.length * LOAD_FACTOR);
    }

    private int getIndex(Entry<V> entry)
    {
        return entry.getHashValue() % (entryTable.length - 1);
    }

    private int getHash(long key)
    {
        return (int)(key ^ (key >>> 32));
    }
}
