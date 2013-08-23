/**
 * 
 */

package com.imaginea.titan.conf;

/**
 * @author kaushikr
 *
 */
public enum ConfKeys
{
    STORAGE_DIRECTORY("storage.directory"),
    STORAGE_BACKEND("storage.backend"),
    SEARCH_BACKEND("storage.index.search.backend"),
    INDEX_STORAGE("storage.index.search.directory");

    private final String key;

    /**
     * @param key
     */
    private ConfKeys(final String key)
    {
        this.key = key;
    }

    /**
     * @return
     */
    public String getKey()
    {
        return key;
    }
}
