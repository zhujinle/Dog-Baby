package news;

public class news {
    private String nid;
    private String title;
    private String author;
    private String content;
    private String date;
    private String type;
    private String updateDate;
    private String img;
    private String authorUID;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorUID() {
        return authorUID;
    }

    public void setAuthorUID(String authorUID) {
        this.authorUID = authorUID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return  "{\"nid\":\"" + nid + "\"," +
                "\"title\":\"" + title + "\"," +
                "\"author\":\"" + author + "\"," +
                "\"content\":\"" + content + "\"," +
                "\"date\":\"" + date + "\"," +
                "\"type\":\"" + type + "\"," +
                "\"updateDate\":\"" + updateDate + "\"," +
                "\"img\":\"" + img + "\"," +
                "\"authorUID\":\"" + authorUID + "\"}";
    }
}
