package com.audiomapp.mymusic;

/**
 * Created by alvararias on 10/03/16.
 */
public class ArtistDeezer {

    /*
    "data": [
                {
              "id": "143",
              "name": "Dido",
              "link": "https://www.deezer.com/artist/143",
              "picture": "https://api.deezer.com/artist/143/image",
              "picture_small": "https://cdns-images.dzcdn.net/images/artist/8e2787c53c81683d4617b509eeebce55/56x56-000000-80-0-0.jpg",
              "picture_medium": "https://cdns-images.dzcdn.net/images/artist/8e2787c53c81683d4617b509eeebce55/250x250-000000-80-0-0.jpg",
              "picture_big": "https://cdns-images.dzcdn.net/images/artist/8e2787c53c81683d4617b509eeebce55/500x500-000000-80-0-0.jpg",
              "nb_album": 25,
              "nb_fan": 263051,
              "radio": true,
              "tracklist": "https://api.deezer.com/artist/143/top?limit=50",
              "type": "artist"
            }

*/

    public int id;
    public String name;
    public String link;
    public String picture;
    public String picture_small;
    public String picture_medium;
    public String picture_big;
    public int nb_album;
    public long nb_fan;
    public boolean radio;
    public String tracklist;
    public String type;

    public ArtistDeezer(int id, String name, String link, String picture, String picture_small, String picture_medium,String picture_big,int nb_album, long nb_fan, boolean radio, String tracklist, String type) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.picture = picture;
        this.picture_small= picture_small;
        this.picture_medium = picture_medium;
        this.picture_big = picture_big;
        this.nb_album = nb_album;
        this.nb_fan = nb_fan;
        this.radio = radio;
        this.tracklist = tracklist;
        this.type = type;

    }
}

 /*


class album {
        public int id;
        public String title;
        public String cover;
        public String cover_small;
        public String cover_medium;
        public String cover_big;
        public String tracklist;
        public String type;


        public album(int id, String title, String cover, String cover_small, String cover_medium, String cover_big, String tracklist, String type) {
        //public album() {
            //this.id = id;
            //this.title = title;
            //this.cover = cover;
            //this.cover_small = cover_small;
            //this.cover_medium = cover_medium;
            //this.cover_big = cover_big;
            //this.tracklist = tracklist;
            //this.type = type;

        }
}

*/