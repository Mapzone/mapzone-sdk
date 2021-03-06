/* 
 * polymap.org
 * Copyright (C) 2018, the @authors. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package io.mapzone.buildserver;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;

import org.polymap.model2.CollectionProperty;
import org.polymap.model2.Composite;
import org.polymap.model2.Computed;
import org.polymap.model2.ComputedBidiManyAssocation;
import org.polymap.model2.Defaults;
import org.polymap.model2.ManyAssociation;
import org.polymap.model2.Nullable;
import org.polymap.model2.Property;
import org.polymap.model2.runtime.ValueInitializer;

/**
 * 
 *
 * @author Falko Bräutigam
 */
public class BuildConfig
        extends BuildObject {

    public static BuildConfig    TYPE;
    
    public static final ValueInitializer<BuildConfig> defaults( String user ) {
        return (BuildConfig proto) -> {
            proto.type.set( Type.PRODUCT );
            proto.userId.set( user );
            proto.downloadPath.set( RandomStringUtils.random( 6, true, true ) );
            return proto;
        };
    }
    
    public enum Type {
        PRODUCT, PLUGIN
    }

    @Nullable
    public Property<String>             name;
    
    /** The bundle-id of the product to build. */
    @Nullable
    public Property<String>             productName;
    
    public Property<Type>               type;
    
    public Property<String>             userId;
    
    /** The servlet path where the latest successfull build can be accessed. */
    public Property<String>             downloadPath;
    
    @Defaults
    public CollectionProperty<TargetPlatformConfig> targetPlatform;
    
    @Defaults
    public CollectionProperty<ScmConfig> scm;
    
    @Computed( ComputedBidiManyAssocation.class )
    public ManyAssociation<BuildResult> buildResults;
    
    
    public Optional<BuildResult> latestSuccessfullResult() {
        return buildResults.stream()
            .filter( r -> r.status.get() == BuildResult.Status.OK )
            .sorted( (r1,r2) -> r2.started.get().compareTo( r1.started.get() ) )
            .findAny();
    }


    /**
     * Configuration of a SCM system.
     */
    public static class ScmConfig
            extends Composite {

        public static final ValueInitializer<ScmConfig> defaults() {
            return (ScmConfig proto) -> {
                proto.type.set( Type.GIT );
                proto.url.set( "" );
                proto.branch.set( "master" );
                return proto;
            };
        }
        
        public enum Type {
            DIRECTORY, GIT
        }

        public Property<Type>       type;
        
        public Property<String>     url;
        
        @Nullable
        public Property<String>     branch;

        @Override
        public boolean equals( Object obj ) {
            if (obj == this) {
                return true;
            }
            else if (obj instanceof TargetPlatformConfig) {
                ScmConfig other = (ScmConfig)obj;
                return url.get().equals( other.url.get() ) && type.get().equals( other.type.get() );
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash( url.get(), type.get() );
        }
    }

    
    /**
     * Configuration of a target platform location. 
     */
    public static class TargetPlatformConfig
            extends Composite {

        public static final ValueInitializer<TargetPlatformConfig> defaults() {
            return (TargetPlatformConfig proto) -> {
                proto.type.set( Type.ZIP_DOWNLOAD );
                proto.url.set( "" );
                return proto;
            };
        }
        
        public enum Type {
            /*DIRECTORY,*/ ZIP_DOWNLOAD
        }
        
        public Property<String>     url;
        
        public Property<Type>       type;

        @Override
        public boolean equals( Object obj ) {
            if (obj == this) {
                return true;
            }
            else if (obj instanceof TargetPlatformConfig) {
                TargetPlatformConfig other = (TargetPlatformConfig)obj;
                return url.get().equals( other.url.get() ) && type.get().equals( other.type.get() );
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash( url.get(), type.get() );
        }
    }

}
