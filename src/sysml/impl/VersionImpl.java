package sysml.impl;

import java.util.Date;

import sysml.Element;
import sysml.Version;

public class VersionImpl implements Version< String, Date, Element< String, String, Date > > {

    protected String label;
    protected Date timestamp;
    protected Element< String, String, Date > element;

    public VersionImpl( String label, Date timestamp,
                        Element< String, String, Date > element ) {
        super();
        this.label = label;
        this.timestamp = timestamp;
        this.element = element;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public Element< String, String, Date > getData() {
        return element;
    }

}
