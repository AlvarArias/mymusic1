package com.audiomapp.mymusic;

/**
 * Created by alvararias on 13/03/16.
 */
public class ArtistTemasDeezer {

    public int id;
    public boolean readable;
    public String title;
    public String title_short;
    public String title_version;
    public String link;
    public int duration;
    public long rank;
    public boolean explicit_lyrics;
    public String preview;


    public ArtistTemasDeezer(int id, boolean readable, String title, String title_short, String title_version, String link, int duration, long rank, boolean explicit_lyrics, String preview) {
        this.id = id;
        this.readable = readable;
        this.link = link;
        this.title = title;
        this.title_short = title_short;
        this.title_version = title_version;
        this.duration = duration;
        this.rank = rank;
        this.explicit_lyrics = explicit_lyrics;
        this.preview = preview;
    }

 /*
    data": [ {
            "id": 962165,
            "readable": true,
            "title": "Here with Me",
            "title_short": "Here with Me",
            "title_version": "",
            "isrc": "USAR19900244",
            "link": "https://www.deezer.com/track/962165",
            "duration": 253,
            "track_position": 1,
            "disk_number": 1,
            "rank": 788494,
            "explicit_lyrics": false,
            "preview": "http://e-cdn-preview-6.deezer.com/stream/6511d4d0dcc45cc7f2b87578e4d9bd47-5.mp3",

  */


}
