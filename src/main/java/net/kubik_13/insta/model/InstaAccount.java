package net.kubik_13.insta.model;


import lombok.Data;
import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.model.Account;
import me.postaddict.instagram.scraper.model.Media;
import me.postaddict.instagram.scraper.model.PageObject;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;

@Data
public class InstaAccount {
    private String userName;
    private String isPrivate;
    private String profilePicUrlHd;
    private int numberOfMediaPosted;
    private PageObject<Media> medias;
    private List<Media> mediaList;

    public String getUserName() {
        return userName;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public String getProfilePicUrlHd() {
        return profilePicUrlHd;
    }

    public int getNumberOfMediaPosted() {
        return numberOfMediaPosted;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public InstaAccount(String userName) throws IOException {
        this.userName = userName;
        OkHttpClient httpClient = new OkHttpClient();
        Instagram instagram = new Instagram(httpClient);
        Account account = instagram.getAccountByUsername(userName);

        isPrivate = account.getIsPrivate().toString();
        profilePicUrlHd = account.getProfilePicUrlHd();
        numberOfMediaPosted = account.getMedia().getCount();
        medias = account.getMedia();
        mediaList = medias.getNodes();
    }
}

