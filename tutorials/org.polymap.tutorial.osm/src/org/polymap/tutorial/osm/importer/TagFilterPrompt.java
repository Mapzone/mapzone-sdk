/*
 * polymap.org 
 * Copyright (C) 2015-2017, Falko Bräutigam. All rights reserved.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3.0 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 */
package org.polymap.tutorial.osm.importer;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Lists;

import org.polymap.p4.data.importer.ImporterPrompt;
import org.polymap.p4.data.importer.ImporterPrompt.Severity;
import org.polymap.p4.data.importer.ImporterSite;
import org.polymap.tutorial.osm.importer.taginfo.TagInfo;

/**
 * 
 * @author Joerg Reichert <joerg@mapzone.io>
 * @author <a href="mailto:falko@mapzone.io">Falko Bräutigam</a>
 */
public class TagFilterPrompt {

    private static final Log log = LogFactory.getLog( TagFilterPrompt.class );

    private static final List<Pair<String,String>> DEFAULT = Arrays.asList( Pair.of( "name", "*" ) );

    private ImporterSite                     site;

    private List<Pair<String,String>>        result = Lists.newArrayList( DEFAULT );

    private final ImporterPrompt             prompt;


    /**
     * 
     * 
     * @param site
     * @param severity {@link Severity#INFO} : collapsed importer on startup
     */
    public TagFilterPrompt( ImporterSite site, Severity severity, TagInfo tagInfo ) {
        this.site = site;

        prompt = site.newPrompt( "tagFilter" )
                .summary.put( "Tag filter" )
                .description.put( "Filters features by their tags" )
                .value.put( humanReadableResult() )
                .severity.put( severity )
                .extendedUI.put( new TagFilterPromptUIBuilder( tagInfo, result ) {
                    @Override
                    public void submit( ImporterPrompt ip ) {
                        prompt.severity.put( Severity.REQUIRED );
                        prompt.value.put( humanReadableResult() );
                        prompt.ok.set( true );
                    }
                });
    }


    protected String humanReadableResult() {
        StringBuilder buf = new StringBuilder( 256 );
        result.stream().forEach( f -> buf.append( buf.length() > 0 ? ", " : "" )
                .append( f.getKey() ).append( "=" ).append( f.getValue() ) );
        return StringUtils.abbreviate( buf.toString(), 80 );
    }


    /**
     * The selected tags to use as filter.
     */
    public List<Pair<String,String>> result() {
        return result;
    }


    public boolean isOk() {
        return prompt.ok.get();
    }
}
