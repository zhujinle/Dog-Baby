package commits;

public class commits {
    private String cid;
    private String fromuser;
    private String tonewid;
    private String commit;
    private String time;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTonewid() {
        return tonewid;
    }

    public void setTonewid(String tonewid) {
        this.tonewid = tonewid;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return  "\"commits\":{" +
                "\"cid\":\"" + cid + "\"," +
                "\"fromuser\":\"" + fromuser + "\"," +
                "\"tonewid\":\"" + tonewid + "\"," +
                "\"commit='\":\"" + commit + "\"," +
                "\"time\":\"" + time + '\"' +
                '}';
    }
}
