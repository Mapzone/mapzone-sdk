/*
 * polymap.org 
 * Copyright (C) 2015-2017 individual contributors as indicated by the
 * @authors tag. All rights reserved.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 */
package org.polymap.tutorial.osm.importer.taginfo;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.commons.io.IOUtils;

/**
 * Gets tag info from tags.json file in classpath.
 * <p/>
 * <b>Not yet ported to new TagInfo API!</b>
 * 
 * @author Joerg Reichert <joerg@mapzone.io>
 * @author Falko Bräutigam
 */
public class TagInfoStatic
        extends TagInfo {

    private static List<String>                        keys = null;

    private static SortedMap<String,SortedSet<String>> tags = null;


    @Override
    public ResultSet<String> keys( String query, Sort sort, int maxResults ) throws Exception {
        throw new RuntimeException( "not yet ported to new TagInfo API" );
    }


    @Override
    public ResultSet<String> values( String key, String query, Sort sort, int maxResults ) {
        throw new RuntimeException( "not yet ported to new TagInfo API" );
    }


    public static List<String> getStaticKeys() throws IOException {
        if (keys == null) {
            getStaticTags();
        }
        return new ArrayList<String>( keys );
    }


    public static SortedMap<String,SortedSet<String>> getStaticTags() throws IOException {
        if (tags == null) {
            TreeSet<String> keySet = new TreeSet<String>();
            tags = new TreeMap<String,SortedSet<String>>();
            String jsonTxt = IOUtils.readLines( TagInfoStatic.class.getResourceAsStream( "tags.json" ) ).stream()
                    .collect( Collectors.joining( "\n" ) );
            JSONObject json = new JSONObject( jsonTxt );
            JSONArray root = json.getJSONArray( "tags" );
            String key;
            SortedSet<String> set;
            JSONArray values;
            for (int i = 0; i < root.length(); i++) {
                key = root.getJSONObject( i ).getString( "key" );
                keySet.add( key );
                values = root.getJSONObject( i ).getJSONArray( "values" );
                set = new TreeSet<String>();
                if (values != null) {
                    for (int j = 0; j < values.length(); j++) {
                        set.add( values.getString( j ) );
                    }
                }
                tags.put( key, set );
            }
            keys = new ArrayList<String>( keySet );
        }
        return tags;
    }
    
}
