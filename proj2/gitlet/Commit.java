package gitlet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import static gitlet.Gitlet.*;
import static gitlet.Utils.*;

public class Commit implements Serializable {

    private String Info;
    private Date Time;
    private String Parent;
    private HashMap<String, String> Blobs;
    private String MP1 = null;
    private String MP2 = null;


    /** make a new commit.*/
    public Commit(String i, Date t, String p, HashMap<String, String> b) {
        this.Info = i;
        this.Time = t;
        this.Parent = p;
        this.Blobs = new HashMap<String, String>();
        for (String key : b.keySet()) {
            this.Blobs.put(key, b.get(key));
        }
    }

    /** get the distance between this commit and the init commit.*/
    public int distance() {
        if (Parent == null) {
            return 0;
        } else {
            return Gitlet.getCommit(Parent).distance() + 1;
        }
    }

    /** get the LENth previous commit of this.
     * @param len the distance.
     * @return that previous commit.*/
    public Commit shorten(int len) {
        Commit result = this;
        for (int i = 0; i < len; i++) {
            result = Gitlet.getCommit(result.get_P());
        }
        return result;
    }

    /** get the sha of this commit.
     * @return sha.*/
    public String getSHA() {
        return Utils.sha1(Utils.serialize(this));
    }

    /** check if the sha of given commit is same as itself.*/
    public boolean equals(Commit c) {
        return this.getSHA().equals(c.getSHA());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /** @return commit message.*/
    public String get_I() {
        return Info;
    }

    /** @return the sha of parent commit.*/
    public String get_P() {
        return Parent;
    }

    /** @return the sha of merge parent1 commit.*/
    public String get_MP1() {
        return MP1;
    }

    /** @return the sha of merge parent2 commit.*/
    public String get_MP2() {
        return MP2;
    }

    /** set the sha of merge parent1 commit.*/
    public void SHA_MP1(String MP1) {
        this.MP1 = MP1;
    }

    /** set the sha of merge parent2 commit.*/
    public void SHA_MP2(String MP2) {
        this.MP2 = MP2;
    }

    /** @return all blobs.*/
    public HashMap<String, String> getBlobs() {
        return Blobs;
    }

    /** @return commit time.*/
    public Date getTime() {
        return Time;
    }

    /** get the sha of the given file.*/
    public String SHA_F(String fileName) {
        return Blobs.get(fileName);
    }
}
