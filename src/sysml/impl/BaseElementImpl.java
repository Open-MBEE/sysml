/**
 *
 */
package sysml.impl;

import gov.nasa.jpl.mbee.util.InterpolatedMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

import sysml.BaseElement;
import sysml.Property;
import sysml.Version;
import sysml.Workspace;

/**
 *
 */
public abstract class BaseElementImpl implements BaseElement< String, String, Date > {

    Workspace< String, String, Date > workspace;
    String id;
    String name;

    Version< String, Date, BaseElement< String, String, Date > > version = null;

    String qualifiedName = null;
    String qualifiedId = null;
    
    public BaseElementImpl( Workspace< String, String, Date > workspace,
          String id,
          String name,
          Version< String, Date, BaseElement< String, String, Date >> version) {
       
        this.workspace = workspace;
        this.id = id;
        this.name = name;
        this.version = version;
    }    

    public BaseElementImpl( BaseElementImpl elem) {
       this(elem.getWorkspace(), elem.getId(), elem.getName(), elem.getVersion());
    }    
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Workspace< String, String, Date > getWorkspace() {
        return workspace;
    }

    @Override
    public BaseElement<String, String, Date> getOwner() {
        return null;
    }
    
    @Override
    public List< Version< String, Date, BaseElement< String, String, Date > > > getVersions() {
        return getWorkspace().getVersions( getId() );
    }

    @Override
    public Map< Date, Version< String, Date, BaseElement< String, String, Date > > > getVersionMap() {
        return getWorkspace().getVersionMap( getId() );
    }

    @Override
    public Version< String, Date, BaseElement< String, String, Date > > getLatestVersion() {
        List< Version< String, Date, BaseElement< String, String, Date > > > versions =
                getVersions();
        if ( versions.isEmpty() ) {
            if ( version != null ) {
                // TODO -- REVIEW -- should this case never happen?
                return version;
            }
            return null;
        }
        return versions.get( versions.size() - 1 );
    }

    @Override
    public Version< String, Date, BaseElement< String, String, Date > > getVersion() {
        return this.version;
    }

    @Override
    public Version< String, Date, BaseElement< String, String, Date > > getVersion( Date dateTime ) {
        Map< Date, Version< String, Date, BaseElement< String, String, Date > > > map =
                getVersionMap();
        InterpolatedMap< Date, Version< String, Date, BaseElement< String, String, Date > > > imap;
        if ( map instanceof InterpolatedMap ) {
            imap = (InterpolatedMap< Date, Version< String, Date, BaseElement< String, String, Date > > >)map;
        } else {
            imap = new InterpolatedMap< Date, Version< String, Date, BaseElement< String, String, Date > > >( map );
        }
        return imap.get( dateTime );
    }

    @Override
    public Date getCreationTime() {
        version = getVersion();
        if ( version == null ) {
            return null;
        } else {
            return version.getTimestamp();
        }
    }

    @Override
    public Date getModifiedTime() {
        version = getLatestVersion();
        if ( version == null ) {
            return null;
        } else {
            return version.getTimestamp();
        }
    }

    @Override
    public void setVersion( Version< String, Date, BaseElement< String, String, Date > > version ) {
        this.version = version;
    }
    
    @Override
    public abstract BaseElement<String, String, Date> clone() throws CloneNotSupportedException;
    
}
