package com.official.noowenz.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetNewsResponse {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("date_gmt")
    @Expose
    public String dateGmt;
    @SerializedName("guid")
    @Expose
    public Guid guid;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("modified_gmt")
    @Expose
    public String modifiedGmt;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("title")
    @Expose
    public Title title;
    @SerializedName("content")
    @Expose
    public Content content;
    @SerializedName("excerpt")
    @Expose
    public Excerpt excerpt;
    @SerializedName("author")
    @Expose
    public Integer author;
    @SerializedName("featured_media")
    @Expose
    public Integer featuredMedia;
    @SerializedName("comment_status")
    @Expose
    public String commentStatus;
    @SerializedName("ping_status")
    @Expose
    public String pingStatus;
    @SerializedName("sticky")
    @Expose
    public Boolean sticky;
    @SerializedName("template")
    @Expose
    public String template;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("meta")
    @Expose
    public List<Object> meta = null;
    @SerializedName("categories")
    @Expose
    public List<Integer> categories = null;
    @SerializedName("tags")
    @Expose
    public List<Object> tags = null;
    @SerializedName("_links")
    @Expose
    public Links links;

    public class About {

        @SerializedName("href")
        @Expose
        public String href;

    }

    public class Author {

        @SerializedName("embeddable")
        @Expose
        public Boolean embeddable;
        @SerializedName("href")
        @Expose
        public String href;

    }

    public class Collection {

        @SerializedName("href")
        @Expose
        public String href;

    }

    public class Content {

        @SerializedName("rendered")
        @Expose
        public String rendered;
        @SerializedName("protected")
        @Expose
        public Boolean _protected;

    }

    public class Cury {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("href")
        @Expose
        public String href;
        @SerializedName("templated")
        @Expose
        public Boolean templated;

    }

    public class Excerpt {

        @SerializedName("rendered")
        @Expose
        public String rendered;
        @SerializedName("protected")
        @Expose
        public Boolean _protected;

    }

    public class Guid {

        @SerializedName("rendered")
        @Expose
        public String rendered;

    }

    public class Title {

        @SerializedName("rendered")
        @Expose
        public String rendered;

    }

    public class Links {

        @SerializedName("self")
        @Expose
        public List<Self> self = null;
        @SerializedName("collection")
        @Expose
        public List<Collection> collection = null;
        @SerializedName("about")
        @Expose
        public List<About> about = null;
        @SerializedName("author")
        @Expose
        public List<Author> author = null;
        @SerializedName("replies")
        @Expose
        public List<Reply> replies = null;
        @SerializedName("version-history")
        @Expose
        public List<VersionHistory> versionHistory = null;
        @SerializedName("wp:featuredmedia")
        @Expose
        public List<WpFeaturedmedium> wpFeaturedmedia = null;
        @SerializedName("wp:attachment")
        @Expose
        public List<WpAttachment> wpAttachment = null;
        @SerializedName("wp:term")
        @Expose
        public List<WpTerm> wpTerm = null;
        @SerializedName("curies")
        @Expose
        public List<Cury> curies = null;

        public class Reply {

            @SerializedName("embeddable")
            @Expose
            public Boolean embeddable;
            @SerializedName("href")
            @Expose
            public String href;

        }

        public class Self {

            @SerializedName("href")
            @Expose
            public String href;

        }

        public class VersionHistory {

            @SerializedName("href")
            @Expose
            public String href;

        }

        public class WpAttachment {

            @SerializedName("href")
            @Expose
            public String href;

        }

        public class WpFeaturedmedium {

            @SerializedName("embeddable")
            @Expose
            public Boolean embeddable;
            @SerializedName("href")
            @Expose
            public String href;

        }

        public class WpTerm {

            @SerializedName("taxonomy")
            @Expose
            public String taxonomy;
            @SerializedName("embeddable")
            @Expose
            public Boolean embeddable;
            @SerializedName("href")
            @Expose
            public String href;

        }

    }
}