package com.nime.ezdisco.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nime.ezdisco.business.MusicSession;
import com.nime.ezdisco.business.UserSession;
import com.nime.ezdisco.data.Artist;
import com.nime.ezdisco.data.Genre;
import com.nime.ezdisco.data.Song;
import com.nime.ezdisco.data.User;
import com.nime.ezdisco.domain.PlaylistInfo;
import com.nime.ezdisco.domain.TrackInfo;

@Component
@Named(value="restService")
@Scope(value="request")
@Path("/service")
public class Service {
	
	
    public static int STREAM_BUFFER_SIZE = 1024*8;
    
    public String TRACK_PATH = "/var/ezd";
	
	
	@Inject
	UserSession session;
	
	@Inject
	MusicSession music;
	
	@PostConstruct
	public void init() {

	}
	
	
	@Path("/users")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		
		List<User> users = session.getUsers();
		
		User[] userarr = new User[users.size()];
		
		int i=0;
		
		for (User usr : users) {
			userarr[i++] = usr;
		}
		
		UsersResponse resp = new UsersResponse();
		resp.setUsers(userarr);
		
		return Response.status(200).entity(resp).build();

	}
	
	@Path("/getusers2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers2() {
		
		List<User> users = session.getUsers();
		
		return users;
		
	}
	
	@Path("/artists")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artist> getArtists() {
		
		List<Artist> res = music.getArtists();

		return res;
		
	}
	
	@Path("/genres")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Genre> getGenres() {
		
		List<Genre> res = music.getGenres();

		return res;
		
	}
	
	@Path("/songs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Song> getSongs() {
		
		List<Song> res = music.getSongs();

		return res;
		
	}
	
	@Path("/playlists")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlaylistInfo> getPlaylists() {
		
		return music.getPlaylists();
		
	}
	
	@Path("/playlist/{plid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TrackInfo> getPlaylist(@PathParam("plid") int plId) {
		
		return music.getPlaylist(plId);
		
	}
	
	@Path("/player")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response playerService(@QueryParam("action") String action, 
			@QueryParam("token") String token,
			@QueryParam("playlistId") String playlistId) {
		
		if ("load".equals(action)) {
			
			String score = music.getMixedScore(Integer.parseInt(token)).toString();
			return Response.status(200).entity(score).build();
		}
		
		
		return null;
		
	}
	
	@Path("/tracks")
	@GET
	public Response trackService(
			@QueryParam("track") String track, 
			@QueryParam("token") String token,
			@QueryParam("key") String key) {
		
		if (track!=null) {
			try {
				return processTrack(track,token, key, null);
			} catch (IOException e) {
				e.printStackTrace();
				return Response.status(500).build();
				
			} 
		}
		
		
		return null;
		
	}
	
	
	Response processTrack(String  trackName, String token, String key, String startAddr) throws IOException  {
			
        int startAddress = 0;
        
        if ( (trackName==null) )  {
            return Response.status(403).build();
        }
        
        if (startAddr!=null) {
            try  {
                startAddress = Integer.parseInt(startAddr);
            } catch (Exception e) {
                startAddress = 0;
            }
        }
        
	     String fileName = TRACK_PATH + "/" + trackName + ".mp3";

	     java.io.File f = new File(fileName);
	     long fSize = f.length();

         
        if (startAddress==0) {
            //log.info("Getting track: " + trackName + " for token:" + token);
            //response.setContentLength((int)fSize);
            
        }  else {
             if (startAddress>=fSize)  {
                 //log.info("Invalid start address = " + startAddress);
                 return Response.status(403).build();    
             }
            //log.info("Resuming download from address: " + startAddress);
            //response.setContentLength((int)(fSize-startAddress));
             //bistream.skip(startAddress);
        }

        java.nio.file.Path path = Paths.get(fileName);
        final byte[] data = Files.readAllBytes(path);
        

        
        StreamingOutput streamingOutput = new StreamingOutput() {
			
			@Override
			public void write(OutputStream op) throws IOException, WebApplicationException {
				
				op.write(data);	
				op.flush();
		        
			}				

		};
		      
			return Response.ok(streamingOutput).type(MediaType.APPLICATION_OCTET_STREAM).build();

     
	}
	
	


}
