package de.comparus.opensource.longmap.models;

//TODO: use lombok instead of boilerplate code
public class Entry<V>
{
    private int hashValue;
    private long key;
    private V value;
    private Entry<V> nextEntry;

    public Entry(int hashValue, long key, V value)
    {
        this.hashValue = hashValue;
        this.key = key;
        this.value = value;
    }

    public Entry(int hashValue, long key, V value, Entry<V> nextEntry)
    {
        this.hashValue = hashValue;
        this.key = key;
        this.value = value;
        this.nextEntry = nextEntry;
    }

    public int getHashValue()
    {
        return hashValue;
    }

    public long getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }

    public Entry<V> getNextEntry()
    {
        return nextEntry;
    }
}
