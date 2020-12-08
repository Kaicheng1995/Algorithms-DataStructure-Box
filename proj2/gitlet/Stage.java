package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Gitlet.*;
import static gitlet.Utils.*;

public class Stage implements Serializable {

    private HashMap<String, byte[]> Staged;
    private ArrayList<String> Marked;
    private Commit Head;

    /** make an empty stage.*/
    public Stage() {
        Staged = new HashMap<String, byte[]>();
        Marked = new ArrayList<String>();
    }

    /** clear the stage. clear staged files and marked files.*/
    public void clear() {
        Staged.clear();
        Marked.clear();
    }

    /** set head to the given commit.*/
    public void setHead(Commit c) {
        Head = c;
    }

    /** execute the add command. staged the given file.*/
    public void add(String fileName) {

        File fcurrent = new File(fileName);
        if (!fcurrent.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        byte[] current = Utils.readContents(fcurrent);
        String curSHA = Utils.sha1(current);

        String preSHA = Head.SHA_F(fileName);

        if (preSHA == null) {
            Staged.put(fileName, current);
        } else {
            if (!curSHA.equals(preSHA)) {
                Staged.put(fileName, current);
            } else {
                Staged.remove(fileName);
            }
        }
        Marked.remove(fileName);
    }

    /** execute the commit command.*/
    public Commit commit(String info) {
        if (info.equals("")) {
            System.out.println("Please enter a commit message.");
            return null;
        }
        if (Staged.isEmpty() && Marked.isEmpty()) {
            System.out.println("No changes added to the commit.");
            return null;
        }
        HashMap<String, String> blobMap = new HashMap<String, String>();
        for (String key: Head.getBlobs().keySet()) {
            blobMap.put(key, Head.SHA_F(key));
        }
        for (String key : Staged.keySet()) {
            byte[] blob = Staged.get(key);
            String sha = Utils.sha1(blob);
            blobMap.put(key, sha);
            saveBlob(blob, sha);
        }
        for (String key: Marked) {
            blobMap.remove(key);
        }
        String sha = Utils.sha1(Utils.serialize(Head));
        clear();

        Commit newCommit = new Commit(info, new Date(), sha, blobMap);
        Head = newCommit;
        return newCommit;
    }

    /** get the merged version commit.*/
    public Commit mergeCommit(String info, String p1, String p2) {
        if (info.equals("")) {
            System.out.println("Please enter a commit message.");
            return null;
        }
        if (Staged.isEmpty() && Marked.isEmpty()) {
            System.out.println("No changes added to the commit.");
            return null;
        }
        HashMap<String, String> blobMap = new HashMap<String, String>();
        for (String key: Head.getBlobs().keySet()) {
            blobMap.put(key, Head.SHA_F(key));
        }
        for (String key : Staged.keySet()) {
            byte[] blob = Staged.get(key);
            String sha = Utils.sha1(blob);
            blobMap.put(key, sha);
            saveBlob(blob, sha);
        }
        for (String key: Marked) {
            blobMap.remove(key);
        }
        String sha = Utils.sha1(Utils.serialize(Head));
        clear();

        Commit newCommit = new Commit(info, new Date(), sha, blobMap);
        newCommit.SHA_MP1(p1);
        newCommit.SHA_MP2(p2);
        Head = newCommit;
        return newCommit;
    }

    /** execute the rm command.*/
    public void rm(String fileName) {
        if (Staged.remove(fileName) == null
                && Head.SHA_F(fileName) == null) {
            System.out.println("No reason to remove the file.");
            return;
        }
        if (Head.SHA_F(fileName) != null) {
            Marked.add(fileName);
            Utils.restrictedDelete(fileName);
        }
    }

    /** save the given blob.*/
    public void saveBlob(byte[] blob, String sha) {
        File f = new File(".gitlet/blobs" + sha);
        Utils.writeContents(f, blob);
    }

    /** check if the stage is clear.*/
    public boolean isClear() {
        return Marked.isEmpty() && Staged.isEmpty();
    }
    /** get staged files.*/
    public HashMap<String, byte[]> SGet() {
        return Staged;
    }

    /** get marked files.*/
    public ArrayList<String> MGet() {
        return Marked;
    }
}
