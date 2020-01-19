package de.comparus.opensource.longmap.models;

import java.util.Objects;

//TODO: use lombok instead of boilerplate code
public class Entry<V>
{
    private int hashValue;
    private long key;
    private V value;
    private Entry<V> nextEntry;

    public Entry()
    {
    }

    public Entry(long key, V value, Entry<V> nextEntry)
    {
        this.key = key;
        this.value = value;
        this.nextEntry = nextEntry;
    }

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

    public void setHashValue(int hashValue)
    {
        this.hashValue = hashValue;
    }

    public long getKey()
    {
        return key;
    }

    public void setKey(long key)
    {
        this.key = key;
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

    public void setNextEntry(Entry<V> nextEntry)
    {
        this.nextEntry = nextEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry<?> entry = (Entry<?>) o;

        if (hashValue != entry.hashValue) return false;
        if (key != entry.key) return false;
        if (!Objects.equals(value, entry.value)) return false;
        return Objects.equals(nextEntry, entry.nextEntry);
    }

    @Override
    public int hashCode()
    {
        int result = hashValue;
        result = 31 * result + (int) (key ^ (key >>> 32));
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Entry{" +
                "hashValue=" + hashValue +
                ", key=" + key +
                ", value=" + value +
                ", nextEntry=" + nextEntry +
                '}';
    }
}
