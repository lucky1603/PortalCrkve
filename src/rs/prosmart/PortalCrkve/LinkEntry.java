/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

/**
 *
 * @author Sinisa
 */
public class LinkEntry {
    public String caption;
    public String url;
    public String thumbnail;
    
    public LinkEntry(String caption, String url)
    {
        this.caption = caption;
        this.url = url;
    }
    
    public LinkEntry(String caption, String url, String thumbnail)
    {
        this(caption, url);
        this.thumbnail = thumbnail;
    }
}
