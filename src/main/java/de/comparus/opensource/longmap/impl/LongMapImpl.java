package de.comparus.opensource.longmap.impl;

import de.comparus.opensource.longmap.LongMap;
import de.comparus.opensource.longmap.models.Entry;

import java.util.Arrays;

public class LongMapImpl<V> implements LongMap<V>
{
    private int size = 0;
    private final int DEFAULT_MAP_SIZE = 16;
    private float LOAD_FACTOR = 0.75f;
    private int sizeToDouble;
    private Entry[] entries;

    public LongMapImpl()
    {
        entries = new Entry[DEFAULT_MAP_SIZE];
        sizeToDouble = (int) (DEFAULT_MAP_SIZE * LOAD_FACTOR);
    }

    public LongMapImpl(int mapSize)
    {
        entries = new Entry[mapSize];
        sizeToDouble = (int) (mapSize * LOAD_FACTOR);
    }

    public V put(long key, V value)
    {
        int keyHash = getHash(key);
        Entry entry = new Entry(keyHash, key, value);
        int index = entry.hashCode() & (entries.length -1);

        if (entries[index] == null)
        {
            entries[index] = entry;
        }
        else
        {
            Entry previousEntry = entries[index];
            if (previousEntry.getKey() == entry.getKey())
            {
                previousEntry.setValue(entry.getValue());
            }
            else
            {
                entries[index] = new Entry(keyHash, key, value, previousEntry);
            }
        }

        size ++;

        if(size == sizeToDouble) changeMapSize();
        return (V) entry.getValue();
    }

    public V get(long key) {
        for (Entry entry : entries)
        {
            if(entry != null)
            {
                if(entry.getNextEntry() == null)
                {
                    if(entry.getKey() == key) return (V) entry.getValue();
                }
                else
                {
                    Entry entry1 = entry.getNextEntry();
                    if (entry1.getKey() == key) return (V) entry1.getValue();
                }
            }
        } 
        return null;
    }

    public V remove(long key) {
        return null;
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
        long[] keys = new long[entries.length];
        for (int i = 0; i < entries.length; i++)
        {
            if (entries[i] != null) keys[i] = entries[i].getKey();
        }
        return keys;
    }

    public V[] values() {
        Object[] values = new Object[entries.length];
        for (int i = 0; i < entries.length; i++)
        {
            if (entries[i] != null) values[i] = entries[i].getValue();
        }
        return (V[]) values;
    }

    public long size()
    {
        return size;
    }

    public void clear()
    {
        int oldLength = entries.length;
        entries = new Entry[oldLength];
        size = 0;
    }

    private void changeMapSize()
    {
        Entry[] entries = new Entry[this.entries.length * 2];
        size = 0;
        for (Entry entry : entries)
        {
            if (entry == null) continue;
            int index = entry.hashCode() & (entries.length - 1);
            entries[index] = entry;
            size++;
        }
        this.entries = entries;
        sizeToDouble = (int) (this.entries.length * LOAD_FACTOR);
    }

    private int getHash(long key)
    {
        //TODO: Implement better realization of hashCode
        return Long.hashCode(key);
    }

}
